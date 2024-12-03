package com.college.app.network

import com.college.app.data.models.Program
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("programs")
    suspend fun getPrograms(): List<Program>

    @GET("programs/{programId}/courses")
    suspend fun getCourses(@Path("programId") programId: String): List<Course>
} 