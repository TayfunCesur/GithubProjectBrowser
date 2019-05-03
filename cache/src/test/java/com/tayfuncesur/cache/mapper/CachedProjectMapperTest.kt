package com.tayfuncesur.cache.mapper

import com.tayfuncesur.cache.MockData
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CachedProjectMapperTest {

    private val mapper = CachedProjectMapper()

    @Test
    fun shouldMapFromCache() {
        val cached = MockData.generateRandomCachedProject()
        val mapped = mapper.mapFromCache(cached)
        assertEquals(cached.id, mapped)
    }

    @Test
    fun shouldMapToCache() {
        val entity = MockData.generateRandomProjectEntity(true)
        val cached = mapper.mapToCache(entity.id)
        assertEquals(cached.id, entity.id)
        assertEquals(cached.isBookmarked, entity.isBookmarked)
    }

}