package com.tayfuncesur.mobile.di.module

import com.tayfuncesur.data.mapper.ProjectMapper
import com.tayfuncesur.domain.repository.ProjectsRepository
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
object TestDataModule {

    @Provides
    @Singleton
    @JvmStatic
    fun providesProjectMapper(): ProjectMapper {
        return Mockito.mock(ProjectMapper::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun providesDataRepository(): ProjectsRepository {
        return Mockito.mock(ProjectsRepository::class.java)
    }
}