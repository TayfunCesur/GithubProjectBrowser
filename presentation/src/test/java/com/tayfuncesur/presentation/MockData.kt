package com.tayfuncesur.presentation

import com.tayfuncesur.domain.model.Project
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

    fun generateRandomProject(): Project {
        return Project(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            Math.random().toInt(),
            false
        )
    }

    fun generateRandomProjectViewList(count: Int = 20): List<ProjectView> {
        val list = mutableListOf<ProjectView>()
        repeat(count) {
            list.add(generateRandomProjectView())
        }
        return list
    }
    fun generateRandomProjectList(count: Int = 20): List<Project> {
        val list = mutableListOf<Project>()
        repeat(count) {
            list.add(generateRandomProject())
        }
        return list
    }

}