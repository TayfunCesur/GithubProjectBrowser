package com.tayfuncesur.presentation.mapper

import com.tayfuncesur.domain.model.Project
import com.tayfuncesur.presentation.model.ProjectView
import javax.inject.Inject

class ProjectViewMapper @Inject constructor() : Mapper<Project, ProjectView> {
    override fun mapToView(project: Project): ProjectView {
        return ProjectView(project.id, project.fullName, project.starCount, project.isBookmarked)
    }
}