package com.tayfuncesur.data.model

data class ProjectEntity(
    val id: String, val fullName: String,
    val starCount: Int, val isBookmarked: Boolean
)