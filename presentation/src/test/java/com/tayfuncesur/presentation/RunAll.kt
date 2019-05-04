package com.tayfuncesur.presentation

import com.tayfuncesur.presentation.mapper.ProjectViewMapperTest
import com.tayfuncesur.presentation.viewmodel.BookmarkViewModelTest
import com.tayfuncesur.presentation.viewmodel.ProjectsViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(ProjectViewMapperTest::class, ProjectsViewModelTest::class, BookmarkViewModelTest::class)
class RunAll