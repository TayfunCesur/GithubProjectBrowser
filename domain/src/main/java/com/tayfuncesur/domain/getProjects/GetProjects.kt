package com.tayfuncesur.domain.getProjects

import com.tayfuncesur.domain.ObservableUseCase
import com.tayfuncesur.domain.executor.PostExecutionThread
import com.tayfuncesur.domain.model.Project
import com.tayfuncesur.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {
    public override fun doWork(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }
}