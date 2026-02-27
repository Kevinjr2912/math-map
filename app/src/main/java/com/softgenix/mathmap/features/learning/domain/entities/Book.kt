package com.softgenix.mathmap.features.learning.domain.entities

data class Book(
    val id: Int,
    val name: String,
    val autorName: String,
    val yearPublication: String,
    val numberPages: Int,
    val imageUri: String,
    val editorial: String,
    val courseId: Int
)
