package com.tayfuncesur.mobile.ui.data

import com.tayfuncesur.domain.model.Project
import java.util.*

object MockData {

    fun generateRandomProjectView(isBookmarked: Boolean = false): Project {
        return Project(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            Math.random().toInt(),
            isBookmarked
        )
    }

}