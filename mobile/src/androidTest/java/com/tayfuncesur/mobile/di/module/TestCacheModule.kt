package com.tayfuncesur.mobile.di.module

import android.app.Application
import com.tayfuncesur.cache.ProjectsCacheImpl
import com.tayfuncesur.cache.db.ProjectsDatabase
import com.tayfuncesur.cache.mapper.CachedProjectMapper
import com.tayfuncesur.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
object TestCacheModule {

    @Provides
    @Singleton
    @JvmStatic
    fun providesDatabase(application: Application): ProjectsDatabase {
        return ProjectsDatabase.getInstance(application)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun providesCachedProjectMapper(): CachedProjectMapper {
        return Mockito.mock(CachedProjectMapper::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun providesProjectsCache(): ProjectsCache {
        return Mockito.mock(ProjectsCache::class.java)
    }

}