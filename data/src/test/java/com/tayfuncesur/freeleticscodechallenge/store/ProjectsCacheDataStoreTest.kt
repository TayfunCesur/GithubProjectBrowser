package com.tayfuncesur.freeleticscodechallenge.store

import com.tayfuncesur.data.repository.ProjectsCache
import com.tayfuncesur.data.store.ProjectsCacheDataStore
import com.tayfuncesur.freeleticscodechallenge.data.MockData
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.UnsupportedOperationException
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ProjectsCacheDataStoreTest {

    lateinit var projectsCacheDataStore: ProjectsCacheDataStore

    @Mock
    lateinit var projectesCache: ProjectsCache

    @Before
    fun setup() {
        projectsCacheDataStore = ProjectsCacheDataStore(projectesCache)
    }

    @Test
    fun shouldGetBookmarkedProjectCompletes() {
        Mockito.`when`(projectesCache.getBookmarkedProjects())
            .thenReturn(Observable.just(MockData.generateRandomIdList()))
        projectsCacheDataStore.getBookmarkedProjects().test().assertComplete()
    }

    @Test
    fun shouldGetBookmarkedProjectReturnsExpectedData() {
        val list = MockData.generateRandomIdList()
        Mockito.`when`(projectesCache.getBookmarkedProjects()).thenReturn(Observable.just(list))
        projectsCacheDataStore.getBookmarkedProjects().test().assertValue(list)
    }

    @Test
    fun shouldBookmarkProjectsCompletes() {
        val projectId = UUID.randomUUID().toString()
        Mockito.`when`(projectesCache.setProjectBookmarked(anyString())).thenReturn(Completable.complete())
        projectsCacheDataStore.setProjectBookmarked(projectId).test().assertComplete()
    }

    @Test
    fun shouldBookmarkProjectCallsCacheStore() {
        val projectId = UUID.randomUUID().toString()
        Mockito.`when`(projectesCache.setProjectBookmarked(anyString())).thenReturn(Completable.complete())
        projectsCacheDataStore.setProjectBookmarked(projectId).test()
        Mockito.verify(projectesCache).setProjectBookmarked(projectId)
    }

    @Test
    fun shouldUnbookmarkProjectsCompletes() {
        val projectId = UUID.randomUUID().toString()
        Mockito.`when`(projectesCache.setProjectUnbookmarked(anyString())).thenReturn(Completable.complete())
        projectsCacheDataStore.setProjectUnbookmarked(projectId).test().assertComplete()
    }

    @Test
    fun shouldUnbookmarkProjectCallsCacheStore() {
        val projectId = UUID.randomUUID().toString()
        Mockito.`when`(projectesCache.setProjectUnbookmarked(anyString())).thenReturn(Completable.complete())
        projectsCacheDataStore.setProjectUnbookmarked(projectId).test()
        Mockito.verify(projectesCache).setProjectUnbookmarked(projectId)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun shouldThrowExceptionForGetProjectsRemote() {
        projectsCacheDataStore.getProjects()
    }


}