package com.tayfuncesur.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import com.tayfuncesur.domain.bookmark.GetBookmarkedProjects
import com.tayfuncesur.domain.getProjects.GetProjects
import com.tayfuncesur.domain.model.Project
import com.tayfuncesur.presentation.ProjectsViewModel
import com.tayfuncesur.presentation.mapper.ProjectViewMapper
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import com.tayfuncesur.presentation.MockData
import com.tayfuncesur.presentation.state.Resource
import java.util.*

@RunWith(JUnit4::class)
class ProjectsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var getProjects = mock<GetProjects>()

    private var getBookmarkedProjects = mock<GetBookmarkedProjects>()

    private val mapper = mock<ProjectViewMapper>()

    private lateinit var projectsViewModel: ProjectsViewModel

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Project>>>()

    @Captor
    val captorString = argumentCaptor<DisposableObserver<List<String>>>()

    @Before
    fun setup() {
        projectsViewModel = ProjectsViewModel(getProjects, getBookmarkedProjects, mapper)
    }

    @Test
    fun shouldLoadProjectsExecutes() {
        projectsViewModel.loadProjects()

        verify(getProjects, times(2)).execute(any(), eq(null))
    }

    @Test
    fun shouldLoadProjectsReturnesData() {
        val projectList = MockData.generateRandomProjectList(2)
        val projectViewList = MockData.generateRandomProjectViewList(2)
        whenever(mapper.mapToView(projectList[0])).thenReturn(projectViewList[0])
        whenever(mapper.mapToView(projectList[1])).thenReturn(projectViewList[1])

        projectsViewModel.loadProjects()

        verify(getProjects, times(2)).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projectList)
        assert(projectsViewModel.getRemoteProjectsLiveData().value is Resource.Success<*>)

    }

    @Test
    fun shouldLoadBookmarkedProjectsExecutes() {
        projectsViewModel.loadBookmarkedProjects()

        verify(getProjects, times(1)).execute(any(), eq(null))
    }

    @Test
    fun shouldLoadBookmarkedProjectsReturnesData() {
        projectsViewModel.loadBookmarkedProjects()

        verify(getBookmarkedProjects, times(1)).execute(captorString.capture(), eq(null))
        captorString.firstValue.onNext(listOf(UUID.randomUUID().toString()))
        assert(projectsViewModel.getBookmarkedProjectsLiveData().value is Resource.Success<*>)
    }
}