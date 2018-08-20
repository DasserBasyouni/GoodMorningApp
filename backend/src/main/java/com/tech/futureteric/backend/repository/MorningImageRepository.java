package com.tech.futureteric.backend.repository;

public class MorningImageRepository {

    //<editor-fold desc="TODO-feature: App Own Daily Morning Image System">
    /*private final MorningImageApi morningImageApi;
    private final SharedPreferences sharedPreferences;

    public MorningImageRepository(MorningImageApi morningImageApi, SharedPreferences sharedPreferences) {
        this.morningImageApi = morningImageApi;
        this.sharedPreferences = sharedPreferences;
    }

    public SharedPreferenceStringLiveData getMorningImageId(int pageNumber) {
        refreshUser(pageNumber);
        return new SharedPreferenceStringLiveData(sharedPreferences, PREF_MORNING_IMAGE_ID, "id_defaultValue");
    }

    private void refreshUser(final int pageNumber) {
        AppExecutors.getInstance().networkIO().execute(() -> {

            boolean MorningImageIdExists = sharedPreferences.contains(PREF_MORNING_IMAGE_ID);

            if (!MorningImageIdExists) {
                int imageNumber = getAndUpdateMorningImageNumber(sharedPreferences);
                int morningMaxPage = -1, morningMaxImagesInPage = -1;
                String MorningImageId = null;

                try {
                    Response<MorningBackgrounds> response = morningImageApi.getMorningBackgrounds(pageNumber).execute();
                    if (response.isSuccessful()) {
                        MorningBackgrounds body = response.body();
                        if (body != null) {
                            List<MorningBackgrounds.Results> results = body.getResults();
                            morningMaxPage = body.getTotalPages();

                            if (results != null) {
                                MorningImageId = results.get(imageNumber).getId();
                                morningMaxImagesInPage = results.size();
                            } else
                                Timber.e("Error 412 refreshing Morning Image: %s", response.message());

                        } else
                            Timber.e("Error 385 refreshing Morning Image: %s", response.message());

                    } else
                        Timber.e("Error 849 refreshing Morning Image: %s", response.message());

                } catch (IOException e) { e.printStackTrace(); }

                sharedPreferences.edit().putString(PREF_MORNING_IMAGE_ID, MorningImageId).apply();
                setMorningMaxPagesAndImages(sharedPreferences, morningMaxImagesInPage, morningMaxPage);
            }
        });
    }*/
    //</editor-fold>
}
