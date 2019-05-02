package com.tayfuncesur.data.repository

import com.tayfuncesur.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectEntity>>
}