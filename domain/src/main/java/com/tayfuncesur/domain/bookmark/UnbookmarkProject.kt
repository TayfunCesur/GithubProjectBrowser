package com.tayfuncesur.domain.bookmark

import com.tayfuncesur.domain.CompletableUseCase
import com.tayfuncesur.domain.executor.PostExecutionThread
import com.tayfuncesur.domain.repository.ProjectsRepository
import io.reactivex.Completable
import java.lang.IllegalArgumentException
import javax.inject.Inject

class UnbookmarkProject @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) :
    CompletableUseCase<UnbookmarkProject.Params>(postExecutionThread) {
    public override fun doWork(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("ProjectId is required to unbookmark")
        return projectsRepository.unbookmarkProject(params.projectId)
    }


    class Params constructor(val projectId: String) {
        companion object {
            fun projectId(projectId: String): Params = Params(projectId)
        }
    }
}