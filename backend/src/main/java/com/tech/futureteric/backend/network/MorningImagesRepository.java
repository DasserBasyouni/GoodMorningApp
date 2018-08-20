package com.tech.futureteric.backend.network;

// TODO (1) is this optimal implantation ?
//@Singleton
public class MorningImagesRepository {

    //<editor-fold desc="TODO-feature: App Own Daily Morning Image System">
    /*private MorningImageApi morningImageApi;
    private LiveData<MorningBackgrounds> morningBackgroundsCache;


    public LiveData<MorningBackgrounds> getMorningBackgrounds(int pageNumber) {
        LiveData<MorningBackgrounds> cached = morningBackgroundsCache;
        if (cached != null) {
            return cached;
        }

        final MutableLiveData<MorningBackgrounds> data = new MutableLiveData<>();
        morningBackgroundsCache = data;

        morningImageApi = new MorningImageApi() {
            @Override
            public Call<MorningBackgrounds> getMorningBackgrounds(int pageNumber) {
                return null;
            }
        };
        morningImageApi.getMorningBackgrounds(pageNumber).enqueue(new Callback<MorningBackgrounds>() {

            @Override
            public void onResponse(@NonNull Call<MorningBackgrounds> call, @NonNull Response<MorningBackgrounds> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MorningBackgrounds> call, @NonNull Throwable t) {
                Timber.e(t);
            }
        });
        return data;
    }*/
    //</editor-fold>
}
