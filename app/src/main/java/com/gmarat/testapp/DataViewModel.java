package com.gmarat.testapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmarat.testapp.model.Data;
import com.gmarat.testapp.model.DataItem;

import java.util.List;

public class DataViewModel extends ViewModel {

    private MediatorLiveData<List<DataItem>> mTab1DataMediator;
    private MediatorLiveData<List<DataItem>> mTab2DataMediator;
    private final DataRepository mRepository = new DataRepository();
    private final MutableLiveData<DataItem> mSelectedItem = new MutableLiveData<>();
    private int mCurrentTabPosition = 0;

    public LiveData<List<DataItem>> getTabData(int tabPosition) {
        switch (tabPosition) {
            case 0:
                if (mTab1DataMediator == null) {
                    mTab1DataMediator = new MediatorLiveData<>();
                    mTab1DataMediator.addSource(mRepository.loadData("cat"), dataEvent -> {
                        mTab1DataMediator.setValue(dataEvent);
                    });
                }
                return mTab1DataMediator;
            default:
                if (mTab2DataMediator == null) {
                    mTab2DataMediator = new MediatorLiveData<>();
                    mTab2DataMediator.addSource(mRepository.loadData("dog"), dataEvent -> {
                        mTab2DataMediator.setValue(dataEvent);
                    });
                }
                return mTab2DataMediator;
        }
    }

    public void select(DataItem item) {
        mSelectedItem.setValue(item);
    }

    public void setCurrentTab(int position) {
        mCurrentTabPosition = position;
    }

    public LiveData<DataItem> getSelected() {
        return mSelectedItem;
    }

    public int getCurrentTabPosition() {
        return mCurrentTabPosition;
    }


}
