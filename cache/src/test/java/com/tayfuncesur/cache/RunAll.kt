package com.tayfuncesur.cache

import com.tayfuncesur.cache.dao.CachedProjectsDaoTest
import com.tayfuncesur.cache.mapper.CachedProjectMapperTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(CachedProjectsDaoTest::class, CachedProjectMapperTest::class, ProjectsCacheImplTest::class)
class RunAll