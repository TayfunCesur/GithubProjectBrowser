package com.tayfuncesur.data.repository

import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsCache {

    fun getBookmarkedProjects(): Observable<List<String>>

    fun setProjectBookmarked(projectId: String): Completable

    fun setProjectUnbookmarked(projectId: String): Completable

}