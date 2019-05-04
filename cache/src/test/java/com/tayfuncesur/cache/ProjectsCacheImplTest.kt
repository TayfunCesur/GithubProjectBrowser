package com.tayfuncesur.cache

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.tayfuncesur.cache.db.ProjectsDatabase
import com.tayfuncesur.cache.mapper.CachedProjectMapper
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
class ProjectsCacheImplTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        ProjectsDatabase::class.java
    ).allowMainThreadQueries()
        .build()

    private val cacheMapper = CachedProjectMapper()

    private val projectsCacheImpl = ProjectsCacheImpl(database, cacheMapper)

    @Test
    fun shouldGetBookmarkedProjectsReturnsData() {
        val randomCachedProject = MockData.generateRandomCachedProject()
        projectsCacheImpl.setProjectBookmarked(randomCachedProject.id).test()
        val result = projectsCacheImpl.getBookmarkedProjects().test()
        assertEquals(result.values()[0].isNotEmpty(), true)
    }

    @Test
    fun shouldSetBookmarkProjectReturnsExpectedData() {
        val randomCachedProject = MockData.generateRandomCachedProject()
        randomCachedProject.isBookmarked = true
        projectsCacheImpl.setProjectBookmarked(randomCachedProject.id).test()
        val data = projectsCacheImpl.getBookmarkedProjects().test()
        assertEquals(data.values()[0][0], randomCachedProject.id)
    }

    @Test
    fun shouldUnbookmarkProjectWork() {
        val randomCachedProject = MockData.generateRandomCachedProject()
        randomCachedProject.isBookmarked = true
        projectsCacheImpl.setProjectBookmarked(randomCachedProject.id).test()


        projectsCacheImpl.setProjectUnbookmarked(randomCachedProject.id).test()


        val result = projectsCacheImpl.getBookmarkedProjects().test()
        assertEquals(result.values()[0].size, 0)
    }

}