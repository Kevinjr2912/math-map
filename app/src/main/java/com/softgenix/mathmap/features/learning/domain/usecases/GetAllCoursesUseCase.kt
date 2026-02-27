package com.softgenix.mathmap.features.learning.domain.usecases

import com.softgenix.mathmap.features.learning.domain.entities.Course
import com.softgenix.mathmap.features.learning.domain.repositories.CourseRepository

class GetAllCoursesUseCase (
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke () : Result<List<Course>> {
        return try {
            val courses = courseRepository.getAllCourses()

            if (courses.isEmpty()) {
                Result.failure(Exception("No se encontraron personajes válidos"))
            } else {
                Result.success(courses)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}