package com.tayfuncesur.presentation

import android.arch.lifecycle.*
import com.tayfuncesur.domain.bookmark.GetBookmarkedProjects
import com.tayfuncesur.domain.getProjects.GetProjects
import com.tayfuncesur.domain.model.Project
import com.tayfuncesur.presentation.mapper.ProjectViewMapper
import com.tayfuncesur.presentation.model.ProjectView
import com.tayfuncesur.presentation.state.Resource
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class ProjectsViewModel @Inject constructor(
    private val getProjects: GetProjects,
    private val getBookmarkedProjects: GetBookmarkedProjects,
    private val mapper: ProjectViewMapper
) : ViewModel() {

    private val remoteProjectsLiveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()
    private val bookmarkedProjectsLiveData: MutableLiveData<Resource<List<String>>> = MutableLiveData()

    init {
        loadProjects()
    }

    fun getRemoteProjectsLiveData(): MutableLiveData<Resource<List<ProjectView>>> {
        return remoteProjectsLiveData
    }

    fun getBookmarkedProjectsLiveData(): MutableLiveData<Resource<List<String>>> {
        return bookmarkedProjectsLiveData
    }

    fun loadProjects() {
        remoteProjectsLiveData.postValue(Resource.Loading())
        getProjects.execute(ProjectsSubscriber())
    }

    fun loadBookmarkedProjects() {
        getBookmarkedProjects.execute(BookmarkedProjectsSubscriber())
    }

    inner class ProjectsSubscriber : DisposableObserver<List<Project>>() {

        override fun onComplete() {

        }

        override fun onNext(t: List<Project>) {
            remoteProjectsLiveData.postValue(Resource.Success(t.map {
                mapper.mapToView(it)
            }))
        }

        override fun onError(e: Throwable) {
            remoteProjectsLiveData.postValue(Resource.Failure(e.localizedMessage))
        }

    }

    inner class BookmarkedProjectsSubscriber : DisposableObserver<List<String>>() {
        override fun onComplete() {}

        override fun onNext(t: List<String>) {
            bookmarkedProjectsLiveData.postValue(Resource.Success(t))
        }

        override fun onError(e: Throwable) {
            bookmarkedProjectsLiveData.postValue(Resource.Failure(e.localizedMessage))
        }

    }

    override fun onCleared() {
        getProjects.clear()
        super.onCleared()
    }

}