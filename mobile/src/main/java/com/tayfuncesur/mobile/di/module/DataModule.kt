package com.tayfuncesur.mobile.di.module

import com.tayfuncesur.data.ProjectsDataRepository
import com.tayfuncesur.data.mapper.ProjectMapper
import com.tayfuncesur.domain.repository.ProjectsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DataModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesProjectMapper(): ProjectMapper {
            return ProjectMapper()
        }
    }

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository

}