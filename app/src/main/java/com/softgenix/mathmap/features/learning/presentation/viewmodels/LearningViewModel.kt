package com.softgenix.mathmap.features.learning.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softgenix.mathmap.features.learning.domain.usecases.GetAllCoursesUseCase
import com.softgenix.mathmap.features.learning.domain.usecases.GetBooksByCourseUseCase
import com.softgenix.mathmap.features.learning.presentation.screens.LearningUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LearningViewModel(
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
    private val getBooksByCourseUseCase: GetBooksByCourseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LearningUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCourses()
    }

    private fun loadCourses() {

        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = getAllCoursesUseCase()

            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { list ->
                        currentState.copy(
                            isLoading = false,
                            courses = list
                        )
                    },
                    onFailure = { error ->
                        currentState.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                )
            }
        }
    }

    fun onCourseClicked(courseId: Int) {
        val currentState = _uiState.value

        if (currentState.expandedCourseId == courseId) {
            _uiState.update {
                it.copy(expandedCourseId = null)
            }
            return
        }

        // marcar solo este curso como cargando
        _uiState.update { it.copy(loadingCourses = it.loadingCourses + courseId) }

        viewModelScope.launch {
            val result = getBooksByCourseUseCase(courseId)

            _uiState.update { state ->
                result.fold(
                    onSuccess = { books ->
                        state.copy(
                            expandedCourseId = courseId,
                            booksByCourse = state.booksByCourse + (courseId to books),
                            loadingCourses = state.loadingCourses - courseId // ya cargó
                        )
                    },
                    onFailure = { error ->
                        state.copy(
                            error = error.message,
                            loadingCourses = state.loadingCourses - courseId
                        )
                    }
                )
            }
        }
    }

}
