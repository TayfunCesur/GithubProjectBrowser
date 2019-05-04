package com.tayfuncesur.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tayfuncesur.domain.bookmark.BookmarkProject
import com.tayfuncesur.domain.bookmark.UnbookmarkProject
import com.tayfuncesur.domain.model.Project
import com.tayfuncesur.presentation.mapper.ProjectViewMapper
import com.tayfuncesur.presentation.state.Resource
import io.reactivex.observers.DisposableCompletableObserver
import javax.inject.Inject

class BookmarkViewModel @Inject constructor(
    private val bookmarkProject: BookmarkProject,
    private val unbookmarkProject: UnbookmarkProject
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<Any>> = MutableLiveData()

    fun getBookmarkLiveData(): MutableLiveData<Resource<Any>> {
        return liveData
    }

    fun bookmarkProject(projectId: String) {
        liveData.postValue(Resource.Loading())
        return bookmarkProject.execute(BookmarkSubscriber(), BookmarkProject.Params.projectId(projectId))
    }

    fun unbookmarkProject(projectId: String) {
        liveData.postValue(Resource.Loading())
        return unbookmarkProject.execute(UnbookmarkSubscriber(), UnbookmarkProject.Params.projectId(projectId))
    }

    inner class BookmarkSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource.Success(null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource.Failure(e.localizedMessage))
        }

    }

    inner class UnbookmarkSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource.Success(null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource.Failure(e.localizedMessage))
        }

    }

    override fun onCleared() {
        bookmarkProject.clear()
        unbookmarkProject.clear()
        super.onCleared()
    }

}