package com.tayfuncesur.mobile.mapper

import com.tayfuncesur.mobile.model.Project
import com.tayfuncesur.presentation.model.ProjectView

class ProjectViewMapper : ViewMapper<ProjectView, Project> {

    override fun mapToView(view: ProjectView): Project {
        return Project(view.id, view.fullName, view.starCount, view.isBookmarked)
    }
}