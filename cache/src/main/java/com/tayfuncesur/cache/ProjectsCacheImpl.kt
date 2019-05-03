package com.tayfuncesur.cache

import com.tayfuncesur.cache.db.ProjectsDatabase
import com.tayfuncesur.cache.mapper.CachedProjectMapper
import com.tayfuncesur.cache.model.CachedProject
import com.tayfuncesur.data.repository.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsCacheImpl @Inject constructor(
    private val projectsDatabase: ProjectsDatabase,
    private val mapper: CachedProjectMapper
) : ProjectsCache {

    override fun getBookmarkedProjects(): Observable<List<String>> {
        return projectsDatabase.cachedProjectsDao().getBookmarkedProjects().toObservable().map {
            it.map {
                mapper.mapFromCache(it)
            }
        }
    }

    override fun setProjectBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkedProject(CachedProject(projectId, true))
            Completable.complete()
        }

    }

    override fun setProjectUnbookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setUnBookmarkedProject(projectId)
            Completable.complete()
        }

    }
}