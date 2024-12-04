package com.isp.app.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> text;

    public MainViewModel() {
        text = new MutableLiveData<>();
        text.setValue("Welcome to the Main Activity");
    }

    public LiveData<String> getText() {
        return text;
    }
} 