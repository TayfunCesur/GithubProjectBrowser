package com.tayfuncesur.data

import com.tayfuncesur.data.mapper.ProjectMapperTest
import com.tayfuncesur.data.store.ProjectsCacheDataStoreTest
import com.tayfuncesur.data.store.ProjectsRemoteDataStoreTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    ProjectDataRepositoryTest::class,
    ProjectMapperTest::class,
    ProjectsCacheDataStoreTest::class,
    ProjectsRemoteDataStoreTest::class
)
class RunAll