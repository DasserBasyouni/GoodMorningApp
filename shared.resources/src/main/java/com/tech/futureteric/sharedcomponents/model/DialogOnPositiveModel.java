package com.tech.futureteric.sharedcomponents.model;

public class DialogOnPositiveModel {

    public interface OnPositive {
        void onPositive();
    }

    private static DialogOnPositiveModel mInstance;
    private OnPositive mListener;

    private DialogOnPositiveModel() {}

    public static DialogOnPositiveModel getInstance() {
        if(mInstance == null)
            mInstance = new DialogOnPositiveModel();
        return mInstance;
    }

    public void setListener(OnPositive listener) {
        mListener = listener;
    }

    public void perform() {
        if(mListener != null)
            notifyListener();
    }

    private void notifyListener() {
        mListener.onPositive();
    }
}