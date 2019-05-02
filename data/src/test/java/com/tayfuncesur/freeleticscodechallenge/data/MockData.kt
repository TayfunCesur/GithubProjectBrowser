package com.tayfuncesur.freeleticscodechallenge.data

import com.tayfuncesur.data.model.ProjectEntity
import com.tayfuncesur.domain.model.Project
import java.util.*

object MockData {

    fun randomProjectEntity() = ProjectEntity(
        UUID.randomUUID().toString(),
        UUID.randomUUID().toString(),
        Math.random().toInt(),
        false
    )


    fun randomProject() = Project(
        UUID.randomUUID().toString(),
        UUID.randomUUID().toString(),
        Math.random().toInt(),
        false
    )

    fun generateRandomProjectEntity(): ProjectEntity {
        return ProjectEntity(UUID.randomUUID().toString(), UUID.randomUUID().toString(), Math.random().toInt(), false)
    }

    fun generateRandomProjectEntityList(count : Int = 20): List<ProjectEntity> {
        val list = mutableListOf<ProjectEntity>()
        repeat(count) {
            list.add(generateRandomProjectEntity())
        }
        return list
    }

    fun generateRandomIdList(): List<String> {
        val list = mutableListOf<String>()
        repeat(20) {
            list.add(UUID.randomUUID().toString())
        }
        return list
    }

    fun generateRandomProject(): Project {
        return Project(UUID.randomUUID().toString(), UUID.randomUUID().toString(), Math.random().toInt(), false)
    }

    fun generateRandomProjectList(count : Int = 20): List<Project> {
        val list = mutableListOf<Project>()
        repeat(count) {
            list.add(generateRandomProject())
        }
        return list
    }


}