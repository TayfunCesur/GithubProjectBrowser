package com.tayfuncesur.mobile.ui.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.tayfuncesur.mobile.R
import com.tayfuncesur.mobile.test.TestApp
import com.tayfuncesur.mobile.test.withViewAtPosition
import com.tayfuncesur.mobile.ui.data.MockData
import io.reactivex.Observable
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import java.util.*


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    @Test
    fun shouldActivityLaunchesSuccessfully() {
        Mockito.`when`(TestApp.appComponent().projectsRepository().getProjects()).thenReturn(
            Observable.just(listOf(MockData.generateRandomProjectView()))
        )
        Mockito.`when`(TestApp.appComponent().projectsRepository().getBookmarkedProjects()).thenReturn(
            Observable.just(listOf(UUID.randomUUID().toString()))
        )
        activity.launchActivity(null)
    }

    @Test
    fun shouldProjectsShow() {
        val projects = listOf(
            MockData.generateRandomProjectView(),
            MockData.generateRandomProjectView(),
            MockData.generateRandomProjectView(),
            MockData.generateRandomProjectView(),
            MockData.generateRandomProjectView()
        )
        Mockito.`when`(TestApp.appComponent().projectsRepository().getProjects()).thenReturn(
            Observable.just(projects)
        )
        Mockito.`when`(TestApp.appComponent().projectsRepository().getBookmarkedProjects()).thenReturn(
            Observable.just(listOf(UUID.randomUUID().toString()))
        )
        activity.launchActivity(null)

        projects.forEachIndexed { index, project ->
            onView(withId(R.id.mainRecycler)).perform(RecyclerViewActions.scrollToPosition<MainAdapter.SingleRow>(index))

            onView(withId(R.id.mainRecycler)).check(matches(hasDescendant(withText(project.fullName))))
        }
    }

    @Test
    fun shouldBookmarksShow() {
        val projects = listOf(MockData.generateRandomProjectView(true), MockData.generateRandomProjectView(false))
        Mockito.`when`(TestApp.appComponent().projectsRepository().getProjects()).thenReturn(
            Observable.just(projects)
        )
        Mockito.`when`(TestApp.appComponent().projectsRepository().getBookmarkedProjects()).thenReturn(
            Observable.just(listOf(projects[0].id))
        )
        activity.launchActivity(null)

        projects.forEachIndexed { index, project ->
            onView(withId(R.id.mainRecycler)).perform(RecyclerViewActions.scrollToPosition<MainAdapter.SingleRow>(index))

            onView(withId(R.id.mainRecycler))
                .check(
                    matches(
                        withViewAtPosition(
                            index,
                            hasDescendant(
                                allOf(
                                    withId(R.id.favImage),
                                    if (project.isBookmarked) isDisplayed() else not(isDisplayed())
                                )
                            )
                        )
                    )
                )
        }
    }
}