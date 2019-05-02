package com.tayfuncesur.remote.service

import com.tayfuncesur.remote.model.ProjectModel
import io.reactivex.Observable
import retrofit2.http.GET

interface GithubService {

    @GET("/orgs/square/repos")
    fun getProjects(): Observable<List<ProjectModel>>

}