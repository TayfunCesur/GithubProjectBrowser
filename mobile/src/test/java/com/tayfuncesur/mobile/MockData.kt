package com.tayfuncesur.mobile

import com.tayfuncesur.presentation.model.ProjectView
import java.util.*

object MockData {

    fun generateRandomProjectView(isBookmarked: Boolean = false): ProjectView {
        return ProjectView(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            Math.random().toInt(),
            isBookmarked
        )
    }


}