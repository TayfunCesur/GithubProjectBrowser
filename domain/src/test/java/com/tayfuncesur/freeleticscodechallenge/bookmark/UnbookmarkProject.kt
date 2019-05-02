package com.tayfuncesur.freeleticscodechallenge.bookmark

import com.tayfuncesur.domain.bookmark.UnbookmarkProject
import com.tayfuncesur.domain.executor.PostExecutionThread
import com.tayfuncesur.domain.repository.ProjectsRepository
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class UnbookmarkProjectTest {

    lateinit var unbookmarkProject: UnbookmarkProject

    @Mock
    lateinit var projectsRepository: ProjectsRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        unbookmarkProject = UnbookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun shouldUnbookmarkProjectCompletes() {
        Mockito.`when`(projectsRepository.unbookmarkProject(anyString())).thenReturn(Completable.complete())
            unbookmarkProject.doWork(UnbookmarkProject.Params.projectId(UUID.randomUUID().toString())).test()
                .assertComplete()

    }


}