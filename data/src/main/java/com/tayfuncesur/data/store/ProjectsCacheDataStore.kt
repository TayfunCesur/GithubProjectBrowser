package com.tayfuncesur.data.store

import com.tayfuncesur.data.model.ProjectEntity
import com.tayfuncesur.data.repository.ProjectsCache
import com.tayfuncesur.data.repository.ProjectsDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsCacheDataStore @Inject constructor(
    private val projectsCache: ProjectsCache
) : ProjectsDataStore {

    override fun getBookmarkedProjects(): Observable<List<String>> {
        return projectsCache.getBookmarkedProjects()
    }

    override fun setProjectBookmarked(projectId: String): Completable {
        return projectsCache.setProjectBookmarked(projectId)
    }

    override fun setProjectUnbookmarked(projectId: String): Completable {
        return projectsCache.setProjectUnbookmarked(projectId)
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("Unsupported operation")
    }
}