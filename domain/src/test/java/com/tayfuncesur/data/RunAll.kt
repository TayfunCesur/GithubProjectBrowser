package com.tayfuncesur.data

import com.tayfuncesur.data.bookmark.BookmarkProjectTest
import com.tayfuncesur.data.bookmark.GetBookmarkedProjectsTest
import com.tayfuncesur.data.bookmark.UnbookmarkProjectTest
import com.tayfuncesur.data.getProjects.GetProjectsTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    BookmarkProjectTest::class,
    GetBookmarkedProjectsTest::class,
    UnbookmarkProjectTest::class,
    GetProjectsTest::class
)
class RunAll