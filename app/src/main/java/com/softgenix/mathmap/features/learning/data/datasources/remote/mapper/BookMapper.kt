package com.softgenix.mathmap.features.learning.data.datasources.remote.mapper

import com.softgenix.mathmap.features.learning.data.datasources.remote.model.BookDto
import com.softgenix.mathmap.features.learning.domain.entities.Book

fun BookDto.toDomain(): Book {
    return Book(
        id = id,
        name = name,
        autorName = autorName,
        yearPublication = yearPublication,
        numberPages = numberPages,
        imageUri = imageUri,
        editorial = editorial,
        courseId = courseId
    )
}
