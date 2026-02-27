package com.softgenix.mathmap.features.learning.di

import com.softgenix.mathmap.core.di.AppContainer
import com.softgenix.mathmap.features.learning.domain.usecases.GetAllCoursesUseCase
import com.softgenix.mathmap.features.learning.domain.usecases.GetBooksByCourseUseCase
import com.softgenix.mathmap.features.learning.presentation.viewmodels.LearningViewModelFactory

class LearningModule (
    private val appContainer: AppContainer
){
    private fun provideGetAllCoursesUseCase(): GetAllCoursesUseCase {
        return GetAllCoursesUseCase(appContainer.courseRepository)
    }

    private fun provideGetBooksByCourseUseCase(): GetBooksByCourseUseCase {
        return GetBooksByCourseUseCase(appContainer.courseRepository)
    }

    fun provideCoursesViewModelFactory(): LearningViewModelFactory {
        return LearningViewModelFactory(
            getAllCoursesUseCase = provideGetAllCoursesUseCase(),
            getBooksByCourseUseCase = provideGetBooksByCourseUseCase()
        )
    }

}