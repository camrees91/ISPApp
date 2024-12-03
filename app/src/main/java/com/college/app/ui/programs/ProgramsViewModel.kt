package com.college.app.ui.programs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.app.data.models.Program
import com.college.app.network.ApiService
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProgramsViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _programs = MutableLiveData<List<Program>>()
    val programs: LiveData<List<Program>> = _programs

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadPrograms() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _error.value = null
                _programs.value = apiService.getPrograms()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
} 