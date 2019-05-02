package com.tayfuncesur.data.mapper

import com.tayfuncesur.data.model.ProjectEntity
import com.tayfuncesur.domain.model.Project

class ProjectMapper : Mapper<ProjectEntity, Project> {
    override fun mapFromEntity(entity: ProjectEntity): Project {
        return Project(entity.id, entity.fullName, entity.starCount, entity.isBookmarked)
    }

    override fun mapToEntity(domain: Project): ProjectEntity {
        return ProjectEntity(domain.id, domain.fullName, domain.starCount, domain.isBookmarked)
    }
}