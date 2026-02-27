package com.softgenix.mathmap.core.network

import com.softgenix.mathmap.features.learning.data.datasources.remote.model.BookDto
import com.softgenix.mathmap.features.learning.data.datasources.remote.model.CourseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MathMapAPi {
    @GET("courses")
    suspend fun getAllCourses () : List<CourseDto>

    @GET("courses/{courseId}/books")
    suspend fun getBooksByCourse(
        @Path("courseId") courseId: Int
    ): List<BookDto>

}