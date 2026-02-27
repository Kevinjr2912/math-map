package com.softgenix.mathmap.features.learning.domain.usecases

import com.softgenix.mathmap.features.learning.domain.entities.Book
import com.softgenix.mathmap.features.learning.domain.repositories.CourseRepository

class GetBooksByCourseUseCase(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(courseId: Int): Result<List<Book>> {
        return try {
            Result.success(repository.getBooksByCourse(courseId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

