package com.softgenix.mathmap.features.learning.data.datasources.remote.model

data class BookDto(
    val id: Int,
    val name: String,
    val autorName: String,
    val yearPublication: String,
    val numberPages: Int,
    val imageUri: String,
    val editorial: String,
    val courseId: Int
)
