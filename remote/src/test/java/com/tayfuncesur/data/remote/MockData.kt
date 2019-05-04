package com.tayfuncesur.data.remote

import com.tayfuncesur.data.model.ProjectEntity
import com.tayfuncesur.remote.model.ProjectModel
import java.util.*

object MockData {

    fun generateRandomProjectEntity(): ProjectEntity {
        return ProjectEntity(UUID.randomUUID().toString(), UUID.randomUUID().toString(), Math.random().toInt(), false)
    }

    fun generateRandomProjectEntityList(count: Int = 20): List<ProjectEntity> {
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

    fun generateRandomProjectModel(): ProjectModel {
        return ProjectModel(UUID.randomUUID().toString(), UUID.randomUUID().toString(), Math.random().toInt())
    }

    fun generateRandomProjectModelList(count: Int = 20): List<ProjectModel> {
        val list = mutableListOf<ProjectModel>()
        repeat(count) {
            list.add(generateRandomProjectModel())
        }
        return list
    }


}