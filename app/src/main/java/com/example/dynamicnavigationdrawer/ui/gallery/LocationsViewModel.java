package com.example.dynamicnavigationdrawer.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class LocationsViewModel extends ViewModel {

    MutableLiveData<List<String>> mList;

    public LocationsViewModel() {
        mList = new MutableLiveData<>();
        List<String> cityList = new ArrayList<>();
        cityList.add("London");
        cityList.add("New York");
        cityList.add("Milan");
        cityList.add("Paris");
        cityList.add("Monaco");
        mList.setValue(cityList);
    }

    public MutableLiveData<List<String>> getList() {
        return mList;
    }
}