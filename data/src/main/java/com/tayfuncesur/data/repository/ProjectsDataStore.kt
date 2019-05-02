package com.tayfuncesur.data.repository

import com.tayfuncesur.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsDataStore {

    fun getBookmarkedProjects(): Observable<List<String>>

    fun setProjectBookmarked(projectId: String): Completable

    fun setProjectUnbookmarked(projectId: String): Completable

    fun getProjects(): Observable<List<ProjectEntity>>
}