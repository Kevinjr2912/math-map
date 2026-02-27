package com.softgenix.mathmap.features.learning.data.repositories

import com.softgenix.mathmap.core.network.MathMapAPi
import com.softgenix.mathmap.features.learning.data.datasources.remote.mapper.toDomain
import com.softgenix.mathmap.features.learning.domain.entities.Book
import com.softgenix.mathmap.features.learning.domain.entities.Course
import com.softgenix.mathmap.features.learning.domain.repositories.CourseRepository

class CourseRepositoryImpl (
    private val api : MathMapAPi
) : CourseRepository {

    override suspend fun getAllCourses () : List<Course> {
        val response = api.getAllCourses()
        return response.map { it.toDomain() }
    }
    override suspend fun getBooksByCourse(courseId: Int): List<Book> {
        val response = api.getBooksByCourse(courseId)

        return response.map { it.toDomain() }
    }


}