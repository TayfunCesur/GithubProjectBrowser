package com.tayfuncesur.mobile.di.module

import com.tayfuncesur.data.repository.ProjectsRemote
import com.tayfuncesur.remote.mapper.ProjectResponseMapper
import com.tayfuncesur.remote.service.GithubService
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
object TestRemoteModule {

    @Provides
    @Singleton
    @JvmStatic
    fun providesGithubService(): GithubService {
        return Mockito.mock(GithubService::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun providesProjectResponseMapper(): ProjectResponseMapper {
        return Mockito.mock(ProjectResponseMapper::class.java)
    }


    @Provides
    @Singleton
    @JvmStatic
    fun providesProjectsRemote(): ProjectsRemote {
        return Mockito.mock(ProjectsRemote::class.java)
    }


}