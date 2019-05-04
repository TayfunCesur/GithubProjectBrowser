package com.tayfuncesur.data

import com.tayfuncesur.domain.model.Project
import java.util.*

object MockData {

    fun generateRandomProject(): Project {
        return Project(UUID.randomUUID().toString(), UUID.randomUUID().toString(), Math.random().toInt(), false)
    }

    fun generateRandomProjectList(): List<Project> {
        val list = mutableListOf<Project>()
        repeat(20) {
            list.add(generateRandomProject())
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


}