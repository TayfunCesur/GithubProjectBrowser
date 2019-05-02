package com.tayfuncesur.freeleticscodechallenge

import com.tayfuncesur.data.ProjectsDataRepository
import com.tayfuncesur.data.mapper.ProjectMapper
import com.tayfuncesur.data.store.ProjectsCacheDataStore
import com.tayfuncesur.data.store.ProjectsDataStoreFactory
import com.tayfuncesur.data.store.ProjectsRemoteDataStore
import com.tayfuncesur.freeleticscodechallenge.data.MockData
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ProjectDataRepositoryTest {

    lateinit var projectsDataRepository: ProjectsDataRepository

    @Mock
    lateinit var mapper: ProjectMapper

    @Mock
    lateinit var projectsRemoteDataStore: ProjectsRemoteDataStore

    @Mock
    lateinit var projectsCacheDataStore: ProjectsCacheDataStore

    lateinit var projectsDataStoreFactory: ProjectsDataStoreFactory

    @Before
    fun setup() {
        projectsDataStoreFactory = ProjectsDataStoreFactory(projectsCacheDataStore, projectsRemoteDataStore)
        projectsDataRepository = ProjectsDataRepository(mapper, projectsDataStoreFactory)
    }

    @Test
    fun shouldGetProjectsCompletes() {
        Mockito.`when`(projectsDataStoreFactory.getRemoteDataStore().getProjects())
            .thenReturn(Observable.just(MockData.generateRandomProjectEntityList()))
        projectsDataRepository.getProjects().test().assertComplete()
    }

    @Test
    fun shouldGetProjectsReturnsExpectedData() {
        val list = MockData.generateRandomProjectList(1)
        val entityList = MockData.generateRandomProjectEntityList(1)

        Mockito.`when`(projectsDataStoreFactory.getRemoteDataStore().getProjects())
            .thenReturn(Observable.just(entityList))

        Mockito.`when`(mapper.mapFromEntity(entityList[0]))
            .thenReturn(list[0])

        projectsDataRepository.getProjects().test().assertValue(list)
    }

    @Test
    fun shouldSetProjectBookmarkedCompletes() {
        val projectId = UUID.randomUUID().toString()
        Mockito.`when`(projectsDataStoreFactory.getCacheDataStore().setProjectBookmarked(projectId)).thenReturn(
            Completable.complete()
        )
        projectsDataRepository.bookmarkProject(projectId).test().assertComplete()
    }

    @Test
    fun shouldSetProjectUnbookmarkedCompletes() {
        val projectId = UUID.randomUUID().toString()
        Mockito.`when`(projectsDataStoreFactory.getCacheDataStore().setProjectUnbookmarked(projectId)).thenReturn(
            Completable.complete()
        )
        projectsDataRepository.unbookmarkProject(projectId).test().assertComplete()
    }

    @Test
    fun shouldGetBookmarkedProjectCompletes(){
        Mockito.`when`(projectsDataStoreFactory.getCacheDataStore().getBookmarkedProjects())
            .thenReturn(Observable.just(MockData.generateRandomIdList()))
        projectsDataRepository.getBookmarkedProjects().test().assertComplete()
    }



}