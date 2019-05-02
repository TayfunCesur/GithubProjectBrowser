package com.tayfuncesur.remote.mapper

import com.tayfuncesur.data.model.ProjectEntity
import com.tayfuncesur.remote.model.ProjectModel

class ProjectResponseMapper : Mapper<ProjectModel, ProjectEntity> {
    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.fullName, model.starCount, false)
    }
}