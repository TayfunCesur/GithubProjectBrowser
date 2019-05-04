package com.tayfuncesur.mobile.ui.detail

import android.content.Intent
import android.os.Bundle
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.tayfuncesur.mobile.R
import com.tayfuncesur.mobile.base.UIConstants.Selected_Item_Key
import com.tayfuncesur.mobile.mapper.ProjectMapper
import com.tayfuncesur.mobile.model.Project
import com.tayfuncesur.mobile.test.TestApp
import com.tayfuncesur.mobile.ui.data.MockData
import com.tayfuncesur.presentation.mapper.ProjectViewMapper
import io.reactivex.Completable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MainDetailActivity>(MainDetailActivity::class.java, false, false)

    @Test
    fun shouldStarCountDisplays() {
        val randomProject = launchWith(true)
        onView(withId(R.id.starCount)).check(matches(withText(randomProject.starCount.toString() + " Stars")))
    }

    @Test
    fun shouldBookmarkActionCallsCorrectAction() {
        val randomProject = launchWith(false)
        Mockito.`when`(TestApp.appComponent().projectsRepository().bookmarkProject(randomProject.id)).thenReturn(
            Completable.complete()
        )
        onView(withId(R.id.bookmarkAnim)).perform(click())
        verify(TestApp.appComponent().projectsRepository()).bookmarkProject(randomProject.id)
    }


    @Test
    fun shouldUnBookmarkActionCallsCorrectAction() {
        val randomProject = launchWith(true)
        Mockito.`when`(TestApp.appComponent().projectsRepository().unbookmarkProject(randomProject.id)).thenReturn(
            Completable.complete()
        )
        onView(withId(R.id.bookmarkAnim)).perform(click())
        verify(TestApp.appComponent().projectsRepository()).unbookmarkProject(randomProject.id)
    }


    private fun launchWith(isBookmarked: Boolean = true): Project {
        val randomProject = MockData.generateRandomProjectView(isBookmarked)
        val mapper = ProjectViewMapper()
        val project = mapper.mapToView(randomProject)

        val anotherMapper = ProjectMapper()
        val realProjec = anotherMapper.mapToView(project)

        val intent = Intent()
        val bundle = Bundle(1)
        bundle.putParcelable(Selected_Item_Key, realProjec)
        intent.putExtras(bundle)

        activityRule.launchActivity(intent)
        return realProjec
    }
}