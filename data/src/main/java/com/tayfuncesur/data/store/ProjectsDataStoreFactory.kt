package com.tayfuncesur.data.store

import javax.inject.Inject

class ProjectsDataStoreFactory @Inject constructor(
    private val projectsCacheDataStore: ProjectsCacheDataStore,
    private val projectsRemoteDataStore: ProjectsRemoteDataStore
) {
    fun getCacheDataStore(): ProjectsCacheDataStore {
        return projectsCacheDataStore
    }

    fun getRemoteDataStore(): ProjectsRemoteDataStore {
        return projectsRemoteDataStore
    }
}