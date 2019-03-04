package com.tech.futureteric.goodmorning.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tech.futureteric.backend.model.Friend;
import com.tech.futureteric.findconnections.ui.FindFriendsActivity;
import com.tech.futureteric.goodmorning.R;
import com.tech.futureteric.goodmorning.utils.CoordinatorTabLayout;
import com.tech.futureteric.goodmorning.utils.TypefaceSpan;
import com.tech.futureteric.goodmorning.utils.UiUtils;
import com.tech.futureteric.introui.WelcomeActivity;
import com.tech.futureteric.sharedcomponents.DataType;
import com.tech.futureteric.sharedcomponents.dialog.AppBasicDialog;
import com.tech.futureteric.sharedcomponents.dialog.AppProgressDialog;
import com.tech.futureteric.sharedcomponents.model.IdlingDataModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.tech.futureteric.backend.utils.MorningImageUtils.setDailyMorningImageInto;
import static com.tech.futureteric.backend.utils.user.UserFriendsUtils.listenToUserFriends;
import static com.tech.futureteric.backend.utils.user.UserInfoUtils.isUserLoggedIn;
import static com.tech.futureteric.sharedcomponents.utils.AnimationUtils.startActivityWithAnimation;

public class MainActivity extends AppCompatActivity implements IdlingDataModel.OnDataReady{


    // TODO fix after siging up for the first time in app when finding friends no dilaog appear
    @BindView(R.id.vp) ViewPager mViewPager;
    @BindView(R.id.coordinatortablayout) CoordinatorTabLayout mCoordinatorTabLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    private AppProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        progressDialog = new AppProgressDialog();
        progressDialog.showProgressDialog(this,
                getString(R.string.label_checking_account),
                getString(R.string.label_please_wait));

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        if (isUserLoggedIn(this)){
            progressDialog.setNewTitle(getString(R.string.label_finding_friends));
            IdlingDataModel.getInstance().setListener(this);
            listenToUserFriends( this);

        } else {
            progressDialog.dismissDialog();
            startWelcomeActivity();
        }
        super.onResume();
    }

    @Override
    public void dataAreReceived() {
        int dataType = IdlingDataModel.getInstance().getDataType();
        Object data = IdlingDataModel.getInstance().getData();
        Timber.i("Received data type: %s", dataType);

        if (dataType == DataType.FINDING_FRIENDS) {
            List<Friend> friends = (List<Friend>) data;
            Timber.i("friends == null? %s", friends == null);

            if (friends == null) {
                Timber.i("progressDialog == null? %s", progressDialog == null);
                progressDialog.dismissDialog();
                new AppBasicDialog().showFindConnectionsDialog(this,
                        getString(R.string.label_oh_you_do_not_have_connections),
                        getString(R.string.label_lets_find_some), () ->
                                startActivityWithAnimation(new Intent(MainActivity.this,
                                        FindFriendsActivity.class), this));
            } else
                setupMainActivity(friends);
        }
    }

    //<editor-fold desc="TODO-feature: App Using External Storage Instead of Caching">
    /*private void saveBitmapToExternalStorage() {
     // TODO you can't download image of UnSplash without their API so fix this
        Bitmap bitmap = (Bitmap) IdlingDataModel.getInstance().getData();
        File dir = new File(Environment.getExternalStorageDirectory().getPath() +
                getString(R.string.folder_good_morning_app_data));

        if (!dir.exists()) dir.mkdirs();

        File todayBackground = new File(dir, "Today-Background.png");
        if (todayBackground.exists()) todayBackground.delete();

        try {
            FileOutputStream outputStream = new FileOutputStream(todayBackground);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        }
        catch (IOException e) { Timber.e(e.getLocalizedMessage()); }
    }*/
    //</editor-fold>

    private void setupMainActivity(List<Friend> friends) {
        setupViewPager(friends);
        setupCoordinatorTabLayout();

        setDailyMorningImageInto(this, mCoordinatorTabLayout.getImageView(), progressDialog);

        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());
    }

    private void startWelcomeActivity() {
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivityWithAnimation(intent, this);
    }

    // TODO-feature for making lock screen get image of tomorrow
    //<editor-fold desc="TODO-feature: App Own Daily Morning Image System">
    /*private void setupViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_BASE_MORNING_BACKGROUNDS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MorningImageApi morningImageApi = retrofit.create(MorningImageApi.class);

        MorningBackgroundViewModel viewModel = ViewModelProviders.of(this).get(MorningBackgroundViewModel.class);
        viewModel.init(morningImageApi, MorningImageUtils.getAndUpdateMorningImagePageNumber(this),getPreferences(Context.MODE_PRIVATE));

        SharedPreferenceStringLiveData sharedPreferenceStringLiveData = viewModel.getMorningImagesLiveData();
        sharedPreferenceStringLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String imageId) {

                String url = getMorningImageUrl(imageId, MainActivity.this);
                Timber.i("background url: %s", url);
                //Picasso.get().load(url).into(mCoordinatorTabLayout.getImageView());

                sharedPreferenceStringLiveData.removeObserver(this);
            }
        });
    }*/
    //</editor-fold>

    private void setupViewPager(List<Friend> friends) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new PendingMessageFragment());
        fragments.add(ContactsFragment.newInstance(friends));

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @NonNull
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Pending Messages";
                    case 1:
                        return "Contacts";
                }
                return "New Tab";
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
    }

    private void setupCoordinatorTabLayout() {
        SpannableString title = new SpannableString(getResources().getString(R.string.app_name));
        title.setSpan(new TypefaceSpan(this,"Lobster-Regular.ttf"), 0, title.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mCoordinatorTabLayout.getActionBar().setTitle(title);

        mCoordinatorTabLayout.enableSingleImageMode()
                .setTranslucentStatusBar(this)
                .setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;

            case R.id.action_about:
                UiUtils.launchAboutActivity(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}