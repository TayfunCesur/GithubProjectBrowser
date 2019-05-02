package com.tayfuncesur.freeleticscodechallenge.mapper

import com.tayfuncesur.data.mapper.ProjectMapper
import com.tayfuncesur.freeleticscodechallenge.data.MockData
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals


@RunWith(MockitoJUnitRunner::class)
class ProjectMapperTest {

    private val projectMapper = ProjectMapper()

    @Test
    fun mapFromEntityTest() {
        val entity = MockData.randomProjectEntity()
        val domain = projectMapper.mapFromEntity(entity)
        assertEquals(entity.id, domain.id)
        assertEquals(entity.fullName, domain.fullName)
        assertEquals(entity.starCount, domain.starCount)
        assertEquals(entity.isBookmarked, domain.isBookmarked)
    }

    @Test
    fun mapToEntityTest() {
        val domain = MockData.randomProject()
        val entity = projectMapper.mapToEntity(domain)
        assertEquals(entity.id, domain.id)
        assertEquals(entity.fullName, domain.fullName)
        assertEquals(entity.starCount, domain.starCount)
        assertEquals(entity.isBookmarked, domain.isBookmarked)
    }


}