package com.softgenix.mathmap.features.learning.presentation.screens

import com.softgenix.mathmap.features.learning.domain.entities.Book
import com.softgenix.mathmap.features.learning.domain.entities.Course

data class LearningUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val courses: List<Course> = emptyList(),
    val expandedCourseId: Int? = null,
    val booksByCourse: Map<Int, List<Book>> = emptyMap(),
    val loadingCourses: Set<Int> = emptySet() // cursos que están cargando libros
)
