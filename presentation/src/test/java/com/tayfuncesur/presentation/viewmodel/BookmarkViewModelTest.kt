package com.tayfuncesur.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import com.tayfuncesur.domain.bookmark.BookmarkProject
import com.tayfuncesur.domain.bookmark.UnbookmarkProject
import com.tayfuncesur.presentation.BookmarkViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class BookmarkViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var bookmarkProject = mock<BookmarkProject>()

    private var unbookmarkProject = mock<UnbookmarkProject>()

    private lateinit var bookmarkViewModel: BookmarkViewModel

    @Before
    fun setup() {
        bookmarkViewModel = BookmarkViewModel(bookmarkProject, unbookmarkProject)
    }

    @Test
    fun shouldBookmarkProjectExecutes() {
        val id = UUID.randomUUID().toString()
        bookmarkViewModel.bookmarkProject(id)
        verify(bookmarkProject, times(1)).execute(any(), eq(BookmarkProject.Params(id)))
    }

}