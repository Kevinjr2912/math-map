package com.softgenix.mathmap.features.learning.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.softgenix.mathmap.features.learning.domain.usecases.GetAllCoursesUseCase
import com.softgenix.mathmap.features.learning.domain.usecases.GetBooksByCourseUseCase

class LearningViewModelFactory (
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
    private val getBooksByCourseUseCase: GetBooksByCourseUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LearningViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LearningViewModel(getAllCoursesUseCase, getBooksByCourseUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}