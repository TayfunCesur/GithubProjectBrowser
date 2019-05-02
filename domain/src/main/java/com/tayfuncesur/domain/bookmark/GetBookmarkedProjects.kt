package com.tayfuncesur.domain.bookmark

import com.tayfuncesur.domain.ObservableUseCase
import com.tayfuncesur.domain.executor.PostExecutionThread
import com.tayfuncesur.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetBookmarkedProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<String>, Nothing?>(postExecutionThread) {
    public override fun doWork(params: Nothing?): Observable<List<String>> {
        return projectsRepository.getBookmarkedProjects()
    }
}