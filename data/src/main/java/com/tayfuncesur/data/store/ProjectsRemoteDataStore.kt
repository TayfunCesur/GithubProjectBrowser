package com.tayfuncesur.data.store

import com.tayfuncesur.data.model.ProjectEntity
import com.tayfuncesur.data.repository.ProjectsDataStore
import com.tayfuncesur.data.repository.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class ProjectsRemoteDataStore @Inject constructor(
    private val projectsRemote: ProjectsRemote
) : ProjectsDataStore {
    override fun getBookmarkedProjects(): Observable<List<String>> {
        throw UnsupportedOperationException("Not supported")
    }

    override fun setProjectBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Not supported")
    }

    override fun setProjectUnbookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Not supported")
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }

}