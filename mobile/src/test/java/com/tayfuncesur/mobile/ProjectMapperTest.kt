package com.tayfuncesur.mobile

import com.tayfuncesur.mobile.mapper.ProjectMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ProjectMapperTest {

    lateinit var projectMapper: ProjectMapper

    @Before
    fun setup(){
        projectMapper = ProjectMapper()
    }

    @Test
    fun mapToView() {
        val randomProjectView = MockData.generateRandomProjectView()
        val mapped = projectMapper.mapToView(randomProjectView)
        assertEquals(randomProjectView.id, mapped.id)
        assertEquals(randomProjectView.fullName, mapped.fullName)
        assertEquals(randomProjectView.starCount, mapped.starCount)
        assertEquals(randomProjectView.isBookmarked, mapped.isBookmarked)
    }


}