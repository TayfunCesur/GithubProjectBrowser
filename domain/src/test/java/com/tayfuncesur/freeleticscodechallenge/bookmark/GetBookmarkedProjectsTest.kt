package com.tayfuncesur.freeleticscodechallenge.bookmark

import com.tayfuncesur.domain.bookmark.GetBookmarkedProjects
import com.tayfuncesur.domain.executor.PostExecutionThread
import com.tayfuncesur.domain.repository.ProjectsRepository
import com.tayfuncesur.freeleticscodechallenge.MockData
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetBookmarkedProjectsTest {

    lateinit var getBookmarkedProjects: GetBookmarkedProjects

    @Mock
    lateinit var projectsRepository: ProjectsRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        getBookmarkedProjects = GetBookmarkedProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun shouldGetBookmarkedProjectsCompletes() {
        Mockito.`when`(projectsRepository.getBookmarkedProjects())
            .thenReturn(Observable.just(MockData.generateRandomIdList()))
        getBookmarkedProjects.doWork().test().assertComplete()
    }

    @Test
    fun shouldGetBookmarkedProjectsReturnsExpectedData(){
        val list = MockData.generateRandomIdList()
        Mockito.`when`(projectsRepository.getBookmarkedProjects()).thenReturn(Observable.just(list))
        getBookmarkedProjects.doWork().test().assertValue(list)
    }


}