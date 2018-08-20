package com.tech.futureteric.sharedcomponents.model;

/***
 * User setData() or setData1() and SetData2()
 * the notifyDataChange() launches only when SetData2() or setData() are called
 */

public class IdlingDataModel {

    public interface OnDataReady {
        void dataAreReceived();
    }

    private static IdlingDataModel mInstance;
    private OnDataReady mListener;
    private Object mData, mData1, mData2;
    private int mDataType, mDataType2;

    private IdlingDataModel() {}

    public static IdlingDataModel getInstance() {
        if(mInstance == null)
            mInstance = new IdlingDataModel();
        return mInstance;
    }

    public void setListener(OnDataReady listener) {
        mListener = listener;
    }

    public void setData(Object data, int dataType) {
        if(mListener != null) {
            mData = data;
            mDataType = dataType;
            notifyDataChange();
        }
    }

    public Object getData() {
        return mData;
    }

    public int getDataType() {
        return mDataType;
    }


    public void setData1(Object data1) {
        if(mListener != null) {
            mData1 = data1;
        }
    }

    public Object getData1() {
        return mData1;
    }

    public void setData2(Object data2, int dataType2) {
        if(mListener != null) {
            mData2 = data2;
            mDataType2 = dataType2;
            notifyDataChange();
        }
    }

    public Object getData2() {
        return mData2;
    }

    public int getDataType1And2() {
        return mDataType2;
    }

    private void notifyDataChange() {
        mListener.dataAreReceived();
    }
}
