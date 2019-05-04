package com.tayfuncesur.data.mapper

import com.tayfuncesur.data.remote.MockData
import com.tayfuncesur.remote.mapper.ProjectResponseMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class ProjectModelMapperTest {

    private val projectResponseMapper = ProjectResponseMapper()


    @Test
    fun shouldMapFromModelRunsAsExpected() {
        val randomModel = MockData.generateRandomProjectModel()
        val entity = projectResponseMapper.mapFromModel(randomModel)
        assertEquals(randomModel.id, entity.id)
        assertEquals(randomModel.fullName, entity.fullName)
        assertEquals(randomModel.starCount, entity.starCount)
    }


}