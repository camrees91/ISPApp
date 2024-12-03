package com.college.app.ui.programs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.college.app.data.models.Course
import com.college.app.network.ApiService
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoursesViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> = _courses

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadCourses(programId: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                _error.value = null
                _courses.value = apiService.getCourses(programId)
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
} 