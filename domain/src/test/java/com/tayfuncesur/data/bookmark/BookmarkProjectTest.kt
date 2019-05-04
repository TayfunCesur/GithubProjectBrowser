package com.tayfuncesur.data.bookmark

import com.tayfuncesur.domain.bookmark.BookmarkProject
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
import java.lang.IllegalArgumentException
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class BookmarkProjectTest {

    lateinit var bookmarkProject: BookmarkProject

    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        bookmarkProject = BookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun shouldBookmarkProjectCompletes() {
        Mockito.`when`(projectsRepository.bookmarkProject(anyString())).thenReturn(Completable.complete())
        bookmarkProject.doWork(BookmarkProject.Params.projectId(UUID.randomUUID().toString())).test().assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowException() {
        bookmarkProject.doWork().test()
    }

}