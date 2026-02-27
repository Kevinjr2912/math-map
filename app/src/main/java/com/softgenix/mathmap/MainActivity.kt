package com.softgenix.mathmap

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.softgenix.mathmap.core.di.AppContainer
import com.softgenix.mathmap.core.ui.theme.MathMapTheme
import com.softgenix.mathmap.features.home.presentation.screens.HomeScreen
import com.softgenix.mathmap.features.learning.di.LearningModule
import com.softgenix.mathmap.features.learning.presentation.viewmodels.LearningViewModel

class MainActivity : ComponentActivity() {

    lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContainer = AppContainer(this)
        val learningModule = LearningModule(appContainer)


        enableEdgeToEdge()
        setContent {
            MathMapTheme {
                HomeScreen(factory = learningModule.provideCoursesViewModelFactory())
            }
        }
    }
}



