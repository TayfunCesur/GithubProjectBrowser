package com.tayfuncesur.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.tayfuncesur.cache.Constants
import com.tayfuncesur.cache.dao.CachedProjectsDao
import com.tayfuncesur.cache.model.CachedProject
import javax.inject.Inject

@Database(version = 1, entities = [CachedProject::class])
abstract class ProjectsDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedProjectsDao(): CachedProjectsDao

    companion object {

        @Volatile
        private var Instance: ProjectsDatabase? = null

        fun getInstance(context: Context): ProjectsDatabase {
            return Instance ?: synchronized(this) {
                Instance ?: buildDatabase(context).also { Instance = it }
            }
        }

        private fun buildDatabase(context: Context): ProjectsDatabase {
            return Room.databaseBuilder(context.applicationContext, ProjectsDatabase::class.java, Constants.dbName)
                .build()
        }

    }
}