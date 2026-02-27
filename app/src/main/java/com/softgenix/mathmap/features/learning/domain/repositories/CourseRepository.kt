package com.softgenix.mathmap.features.learning.domain.repositories

import com.softgenix.mathmap.features.learning.domain.entities.Book
import com.softgenix.mathmap.features.learning.domain.entities.Course

interface CourseRepository {
    suspend fun getAllCourses(): List<Course>
    suspend fun getBooksByCourse(courseId: Int): List<Book>
}