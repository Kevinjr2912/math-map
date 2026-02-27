package com.softgenix.mathmap.features.home.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softgenix.mathmap.features.home.presentation.components.HomeHeader
import com.softgenix.mathmap.features.learning.presentation.components.CardBook
import com.softgenix.mathmap.features.learning.presentation.components.CardCourse
import com.softgenix.mathmap.features.learning.presentation.viewmodels.LearningViewModel
import com.softgenix.mathmap.features.learning.presentation.viewmodels.LearningViewModelFactory
import com.softgenix.mathmap.features.progress.presentation.components.StateCard
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (
    factory: LearningViewModelFactory
) {

    val learningViewModel : LearningViewModel = viewModel(factory = factory)
    val learningUiState by learningViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(top = 30.dp),
                title = { HomeHeader(userName = "Kev") }
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 20.dp)
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item {
                        StateCard(
                            title = "Cursos completados",
                            value = "12/24",
                            icon = Icons.Default.CheckCircle,
                            iconContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            iconColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    item {
                        StateCard(
                            title = "Racha activa",
                            value = "15 días",
                            icon = Icons.Default.DateRange,
                            iconContainerColor = MaterialTheme.colorScheme.errorContainer,
                            iconColor = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }

                    item {
                        StateCard(
                            title = "Libros desbloqueados",
                            value = "8 libros",
                            icon = Icons.Default.FavoriteBorder,
                            iconContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            iconColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }

                    item {
                        StateCard(
                            title = "Lecciones completadas",
                            value = "132",
                            icon = Icons.Default.Check,
                            iconContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            iconColor = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }

                    item {
                        StateCard(
                            title = "Tiempo estudiado",
                            value = "24h 30m",
                            icon = Icons.Default.PlayArrow,
                            iconContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            iconColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

            }

            item {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Ruta de aprendizaje",
                    fontSize = 20.sp
                )
            }

            if (learningUiState.isLoading) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else if (learningUiState.error != null) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = learningUiState.error ?: "Error",
                            color = Color.Red
                        )
                    }
                }
            } else {
                learningUiState.courses.forEach { course ->
                    item(key = "course_${course.id}") {
                        CardCourse(
                            id = course.id,
                            name = course.name,
                            nivel = course.nivel,
                            hoursContent = course.hoursContent,
                            imageUrl = course.imageUrl,
                            isExpanded = learningUiState.expandedCourseId == course.id,
                            onClickArrow = { learningViewModel.onCourseClicked(course.id) }
                        )
                    }

                    // Si está expandido, mostrar sus libros
                    if (learningUiState.expandedCourseId == course.id) {
                        val books = learningUiState.booksByCourse[course.id] ?: emptyList()

                        items(
                            items = books,
                            key = { book -> "book_${book.id}" }
                        ) { book ->
                            CardBook(
                                id = book.id,
                                autorName = book.autorName,
                                bookTitle = book.name,
                                yearPublication = book.yearPublication,
                                numberPages = book.numberPages,
                                imageUri = book.imageUri
                            )
                        }
                    }
                }
            }

        }
    }
}