package com.tayfuncesur.data

import com.tayfuncesur.data.mapper.ProjectMapper
import com.tayfuncesur.data.store.ProjectsDataStoreFactory
import com.tayfuncesur.domain.model.Project
import com.tayfuncesur.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
    private val mapper: ProjectMapper,
    private val dataStore: ProjectsDataStoreFactory
) : ProjectsRepository {
    override fun getProjects(): Observable<List<Project>> {
        return dataStore.getRemoteDataStore().getProjects().map {
            it.map {
                mapper.mapFromEntity(it)
            }
        }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return dataStore.getCacheDataStore().setProjectBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return dataStore.getCacheDataStore().setProjectUnbookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<String>> {
        return dataStore.getCacheDataStore().getBookmarkedProjects()
    }
}