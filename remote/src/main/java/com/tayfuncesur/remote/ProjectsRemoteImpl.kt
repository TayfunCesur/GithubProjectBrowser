package com.tayfuncesur.remote

import com.tayfuncesur.data.model.ProjectEntity
import com.tayfuncesur.data.repository.ProjectsRemote
import com.tayfuncesur.remote.mapper.ProjectResponseMapper
import com.tayfuncesur.remote.service.GithubService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
    private val service: GithubService,
    private val mapper: ProjectResponseMapper
) : ProjectsRemote {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.getProjects().map {
            it.map {
                mapper.mapFromModel(it)
            }
        }
    }

}