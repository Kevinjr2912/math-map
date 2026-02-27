package com.softgenix.mathmap.core.di

import android.content.Context
import com.softgenix.mathmap.core.network.MathMapAPi
import com.softgenix.mathmap.features.learning.data.repositories.CourseRepositoryImpl
import com.softgenix.mathmap.features.learning.domain.repositories.CourseRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer (context: Context) {
    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val mathMapRetrofit = createRetrofit(baseUrl = "http://192.168.1.179:3000/api/v1/")

    val mathMapApi : MathMapAPi by lazy {
        mathMapRetrofit.create(MathMapAPi::class.java)
    }

    val courseRepository : CourseRepository by lazy {
        CourseRepositoryImpl(mathMapApi)
    }

}