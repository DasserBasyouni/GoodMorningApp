package com.tech.futureteric.backend.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tech.futureteric.backend.Constants;
import com.tech.futureteric.sharedcomponents.DataType;
import com.tech.futureteric.sharedcomponents.dialog.AppProgressDialog;
import com.tech.futureteric.sharedcomponents.model.IdlingDataModel;

import timber.log.Timber;

import static com.tech.futureteric.backend.Constants.URL_SEARCH_PARAM_MORNING;

public class MorningImageUtils {

    private static String savedScreenDimensionsParam;

    // imp ref: http://www.zoftino.com/android-picasso-image-downloading-and-caching-library-tutorial
    public static void setDailyMorningImageInto(Activity activity, ImageView imageView, AppProgressDialog progressDialog){
        String url = getDailyMorningImageUrl(activity);

        Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressDialog.dismissDialog();
            }

            @Override
            public void onError(Exception e) {
                Picasso.get()
                        .load(url)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                progressDialog.dismissDialog();
                            }

                            @Override
                            public void onError(Exception e) {
                                // TODO handle onError  methods more professional
                                progressDialog.dismissDialog();
                                Timber.v("Picasso Could not fetch image");
                            }
                        });
            }
        });
    }

    public static void returnIdlingDataWhenImageIsCached(Object job){
        String url = getDailyMorningImageUrl();
        Log.e("Z_", url);

        Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                IdlingDataModel.getInstance().setData(job, DataType.LOCK_SCREEN_MORNING_IMAGE_IS_CACHED);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Picasso.get().load(url).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        IdlingDataModel.getInstance().setData(job, DataType.LOCK_SCREEN_MORNING_IMAGE_IS_CACHED);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Timber.v(e);
                        // TODO handle error more better way
                        IdlingDataModel.getInstance().setData(job, DataType.LOCK_SCREEN_MORNING_IMAGE_ERROR);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    public static void getDailyMorningImageBitmap(Activity activity, boolean isCached){
        Picasso.get().load(getDailyMorningImageUrl(activity)).networkPolicy(NetworkPolicy.OFFLINE)
                .into(getTarget(isCached));
    }

    private static String getDailyMorningImageUrl(){
        String url = Constants.URL_BASE_MORNING_SPECIFIC_BACKGROUND + getScreenDimensionsParam()
                + "/daily" + URL_SEARCH_PARAM_MORNING;
        Timber.i("Daily Background URL: %s", url);
        return url;
    }

    private static String getDailyMorningImageUrl(Activity activity){
        String url = Constants.URL_BASE_MORNING_SPECIFIC_BACKGROUND + getScreenDimensionsParam(activity)
                + "/daily" + URL_SEARCH_PARAM_MORNING;
        Timber.i("Daily Background URL: %s", url);
        return url;
    }

    // TODO test the return is the same for immersed and normal activates or not
    private static String getScreenDimensionsParam(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        savedScreenDimensionsParam =  "/" + displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
        return savedScreenDimensionsParam;
    }

    private static String getScreenDimensionsParam(){
        return savedScreenDimensionsParam;
    }

    private static Target getTarget(boolean isCached){
        return new Target(){

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                if (isCached)
                    IdlingDataModel.getInstance().setData(bitmap, DataType.MORNING_IMAGE_CACHED_SUCCESS);
                else
                    IdlingDataModel.getInstance().setData(bitmap, DataType.MORNING_IMAGE_CACHED_SUCCESS);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                if (isCached)
                    IdlingDataModel.getInstance().setData(e, DataType.MORNING_IMAGE_CACHED_ERROR);
                else
                    IdlingDataModel.getInstance().setData(e, DataType.MORNING_IMAGE_ONLINE_ERROR);
            }


            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
    }

    //<editor-fold desc="TODO-feature: App Own Daily Morning Image System">
    /*public static String getMorningImageUrl(String imageId, Activity activity){
        return Constants.URL_BASE_MORNING_SPECIFIC_BACKGROUND + "/" + imageId + "/" + getImage250HeightDimensions(activity);
    }

    private static String getImage250HeightDimensions(Activity activity){
        Resources r = activity.getResources();
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, r.getDisplayMetrics());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        return width + "x" + height;
    }

    public int getMorningImageNumber(Activity a, int preferenceResource){
        SharedPreferences sharedPref = a.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int morningImageNumber = sharedPref.getInt(Constants.PREF_MORNING_IMAGE_NUMBER, 0);
        int morningImageMaxNumber = sharedPref.getInt(Constants.PREF_MORNING_IMAGES_MAX_NUMBER, -1);
        int newMorningImageNumber;

        if (morningImageNumber < morningImageMaxNumber)
            newMorningImageNumber = morningImageNumber+1;
        else
            newMorningImageNumber = 0;

        editor.putInt(a.getString(preferenceResource), newMorningImageNumber);
        editor.apply();

        return morningImageNumber;
    }

    public static int getAndUpdateMorningImagePageNumber(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);

        int morningImageNumber = sharedPref.getInt(Constants.PREF_MORNING_IMAGE_NUMBER, 0);
        int pageNumber = sharedPref.getInt(Constants.PREF_MORNING_IMAGE_PAGE_NUMBER, 1);

        if (morningImageNumber == 0)
            return pageNumber;

        int maxImagesNumber = sharedPref.getInt(Constants.PREF_MORNING_IMAGES_MAX_NUMBER, -1);
        int maxPagesNumber = sharedPref.getInt(Constants.PREF_MORNING_IMAGE_MAX_PAGE_NUMBER, 0);
        int newPageNumber;

        if (morningImageNumber == maxImagesNumber) {
            if (pageNumber < maxPagesNumber)
                newPageNumber = pageNumber + 1;
            else
                newPageNumber = 1;
        } else
            newPageNumber = pageNumber;

        sharedPref.edit().putInt(Constants.PREF_MORNING_IMAGE_PAGE_NUMBER, newPageNumber).apply();

        return pageNumber;
    }

    public static void setMorningMaxPagesAndImages(SharedPreferences sharedPref, int maxImages, int maxPages){
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt(Constants.PREF_MORNING_IMAGES_MAX_NUMBER, maxImages);
        editor.putInt(Constants.PREF_MORNING_IMAGE_MAX_PAGE_NUMBER, maxPages);
        editor.apply();
    }

    public static int getAndUpdateMorningImageNumber(SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int morningImageNumber = sharedPreferences.getInt(Constants.PREF_MORNING_IMAGE_NUMBER, 0);
        int morningImageMaxNumber = sharedPreferences.getInt(Constants.PREF_MORNING_IMAGE_MAX_PAGE_NUMBER, -1);
        int newMorningImageNumber;

        if (morningImageNumber < morningImageMaxNumber)
            newMorningImageNumber = morningImageNumber+1;
        else
            newMorningImageNumber = 0;

        editor.putInt(Constants.PREF_MORNING_IMAGE_NUMBER, newMorningImageNumber);
        editor.apply();


        return morningImageNumber;
    }*/
    //</editor-fold>

}