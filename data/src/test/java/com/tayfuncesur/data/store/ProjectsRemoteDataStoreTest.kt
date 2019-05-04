package com.tayfuncesur.data.store

import com.tayfuncesur.data.repository.ProjectsRemote
import com.tayfuncesur.data.data.MockData
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.UnsupportedOperationException
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ProjectsRemoteDataStoreTest {

    lateinit var projectsRemoteDataStore: ProjectsRemoteDataStore

    @Mock
    lateinit var projectsRemote: ProjectsRemote

    @Before
    fun setup() {
        projectsRemoteDataStore = ProjectsRemoteDataStore(projectsRemote)
    }

    @Test
    fun shouldGetProjectsCompletes() {
        Mockito.`when`(projectsRemote.getProjects()).thenReturn(Observable.just(MockData.generateRandomProjectEntityList()))
        projectsRemoteDataStore.getProjects().test().assertComplete()
    }

    @Test
    fun shouldGetProjectsReturnsExpectedData() {
        val list = MockData.generateRandomProjectEntityList()
        Mockito.`when`(projectsRemote.getProjects()).thenReturn(Observable.just(list))
        projectsRemoteDataStore.getProjects().test().assertValue(list)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun shouldThrowExceptionForBookmarkProject() {
        projectsRemoteDataStore.setProjectBookmarked(UUID.randomUUID().toString())
    }

    @Test(expected = UnsupportedOperationException::class)
    fun shouldThrowExceptionForUnbookmarkProject() {
        projectsRemoteDataStore.setProjectUnbookmarked(UUID.randomUUID().toString())
    }


    @Test(expected = UnsupportedOperationException::class)
    fun shouldThrowExceptionForGetBookmarkedProject() {
        projectsRemoteDataStore.getBookmarkedProjects()
    }


}