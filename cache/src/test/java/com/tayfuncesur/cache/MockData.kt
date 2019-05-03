package com.tayfuncesur.cache

import com.tayfuncesur.cache.model.CachedProject
import com.tayfuncesur.data.model.ProjectEntity
import java.util.*

object MockData {

    fun generateRandomProjectEntity(isBookmarked : Boolean = false): ProjectEntity {
        return ProjectEntity(UUID.randomUUID().toString(), UUID.randomUUID().toString(), Math.random().toInt(), isBookmarked)
    }

    fun generateRandomCachedProject(): CachedProject {
        return CachedProject(UUID.randomUUID().toString(), Math.random() < 0.5)
    }

    fun generateRandomCachedProjectList(count: Int = 20): List<CachedProject> {
        val list = mutableListOf<CachedProject>()
        repeat(count) {
            list.add(generateRandomCachedProject())
        }
        return list
    }


}