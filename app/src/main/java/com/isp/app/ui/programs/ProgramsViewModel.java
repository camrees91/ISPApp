package com.isp.app.ui.programs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.isp.app.data.models.Program;
import com.isp.app.network.ApiService;
import java.util.List;

public class ProgramsViewModel extends ViewModel {
    private final ApiService apiService;
    private final MutableLiveData<List<Program>> programs = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    public ProgramsViewModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<List<Program>> getPrograms() {
        return programs;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void loadPrograms() {
        new Thread(() -> {
            try {
                loading.postValue(true);
                error.postValue(null);
                programs.postValue(apiService.getPrograms());
            } catch (Exception e) {
                error.postValue(e.getMessage());
            } finally {
                loading.postValue(false);
            }
        }).start();
    }
} 