package com.tayfuncesur.mobile.di.module

import android.app.Application
import com.tayfuncesur.cache.ProjectsCacheImpl
import com.tayfuncesur.cache.db.ProjectsDatabase
import com.tayfuncesur.cache.mapper.CachedProjectMapper
import com.tayfuncesur.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class CacheModule {

    @Module
    companion object {
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
            return CachedProjectMapper()
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCacheImp: ProjectsCacheImpl): ProjectsCache

}