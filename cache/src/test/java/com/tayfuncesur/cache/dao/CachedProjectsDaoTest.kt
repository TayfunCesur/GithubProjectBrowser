package com.tayfuncesur.cache.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.tayfuncesur.cache.MockData
import com.tayfuncesur.cache.db.ProjectsDatabase
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
class CachedProjectsDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        ProjectsDatabase::class.java
    ).allowMainThreadQueries()
        .build()

    @Test
    fun shouldGetBookmarkedProjectsReturnsExpectedData() {
        val cachedProject = MockData.generateRandomCachedProject()
        database.cachedProjectsDao().setBookmarkedProject(cachedProject)

        val list = database.cachedProjectsDao().getBookmarkedProjects().test()
        assertEquals(list.values()[0][0].id, cachedProject.id)
        assertEquals(list.values()[0][0].isBookmarked, cachedProject.isBookmarked)
    }

    @Test
    fun shouldSetBookmarkedProjectReturnsExpectedData() {
        val cachedProject = MockData.generateRandomCachedProject()
        cachedProject.isBookmarked = true
        database.cachedProjectsDao().setBookmarkedProject(cachedProject)

        val list = database.cachedProjectsDao().getBookmarkedProjects().test()
        assertEquals(list.values()[0][0].id, cachedProject.id)
        assertEquals(list.values()[0][0].isBookmarked, cachedProject.isBookmarked)
    }

    @Test
    fun shouldUnbookmarkedProjectReturnsExpectedData() {
        val cachedProject = MockData.generateRandomCachedProject()
        cachedProject.isBookmarked = true
        database.cachedProjectsDao().setBookmarkedProject(cachedProject)
        database.cachedProjectsDao().setUnBookmarkedProject(cachedProject.id)

        val list = database.cachedProjectsDao().getBookmarkedProjects().test()
        assertEquals(list.values()[0].size, 0)
    }


    @After
    fun close() {
        database.close()
    }


}