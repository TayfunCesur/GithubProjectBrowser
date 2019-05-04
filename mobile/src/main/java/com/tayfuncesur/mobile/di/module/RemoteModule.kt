package com.tayfuncesur.mobile.di.module

import com.tayfuncesur.data.repository.ProjectsRemote
import com.tayfuncesur.mobile.BuildConfig
import com.tayfuncesur.remote.ProjectsRemoteImpl
import com.tayfuncesur.remote.mapper.ProjectResponseMapper
import com.tayfuncesur.remote.service.GithubService
import com.tayfuncesur.remote.service.GithubServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesGithubService(): GithubService {
            return GithubServiceFactory.create(BuildConfig.DEBUG)
        }

        @Provides
        @JvmStatic
        fun providesProjectResponseMapper(): ProjectResponseMapper {
            return ProjectResponseMapper()
        }
    }


    @Binds
    abstract fun bindProjectsRemote(projectsRemoteImpl: ProjectsRemoteImpl): ProjectsRemote


}