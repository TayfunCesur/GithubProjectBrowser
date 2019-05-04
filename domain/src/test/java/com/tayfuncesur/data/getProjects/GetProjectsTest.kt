package com.tayfuncesur.data.getProjects

import com.tayfuncesur.domain.executor.PostExecutionThread
import com.tayfuncesur.domain.getProjects.GetProjects
import com.tayfuncesur.domain.repository.ProjectsRepository
import com.tayfuncesur.data.MockData
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetProjectsTest {

    private lateinit var getProjects: GetProjects
    @Mock
    lateinit var projectRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        getProjects = GetProjects(projectRepository, postExecutionThread)
    }

    @Test
    fun shouldGetProjectsCompletes() {
        `when`(projectRepository.getProjects()).thenReturn(Observable.just(MockData.generateRandomProjectList()))

        val testObserver = getProjects.doWork().test()
        testObserver.assertComplete()
    }

    @Test
    fun shouldGetProjectsReturnsData() {
        val list = MockData.generateRandomProjectList()
        Mockito.`when`(projectRepository.getProjects()).thenReturn(Observable.just(list))
        val testObserver = getProjects.doWork().test()
        testObserver.assertValue(list)
    }


}