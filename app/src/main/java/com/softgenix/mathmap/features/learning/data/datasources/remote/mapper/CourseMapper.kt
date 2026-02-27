package com.softgenix.mathmap.features.learning.data.datasources.remote.mapper

import com.softgenix.mathmap.features.learning.data.datasources.remote.model.CourseDto
import com.softgenix.mathmap.features.learning.domain.entities.Course

fun CourseDto.toDomain () : Course {
    return Course (
        id = this.id,
        name = this.name,
        nivel = this.nivel,
        hoursContent = this.hoursContent,
        imageUrl = this.imageUrl
    )
}