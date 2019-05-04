package com.tayfuncesur.mobile.di.module

import com.tayfuncesur.data.ProjectsDataRepository
import com.tayfuncesur.data.mapper.ProjectMapper
import com.tayfuncesur.domain.repository.ProjectsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Module
    companion object {
        @Provides
        @Singleton
        @JvmStatic
        fun providesProjectMapper(): ProjectMapper {
            return ProjectMapper()
        }
    }

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository

}