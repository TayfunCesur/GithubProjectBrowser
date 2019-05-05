package com.tayfuncesur.mobile.ui.detail

import android.animation.Animator
import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.tayfuncesur.mobile.R
import com.tayfuncesur.mobile.base.BaseDaggerActivity
import com.tayfuncesur.mobile.base.UIConstants.Selected_Item_Key
import com.tayfuncesur.mobile.di.ViewModelFactory
import com.tayfuncesur.mobile.model.Project
import com.tayfuncesur.presentation.BookmarkViewModel
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.android.synthetic.main.main_detail.*
import javax.inject.Inject

class MainDetailActivity : BaseDaggerActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var bookmarkViewModel: BookmarkViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_detail)
        val selectedProject = intent?.extras?.getParcelable<Project>(Selected_Item_Key)

        toolbar_detail_title.text = selectedProject?.fullName
        starCount.text = "${selectedProject?.starCount} Stars"

        val defaultSpeed = bookmarkAnim.speed

        bookmarkViewModel = ViewModelProviders.of(this, viewModelFactory).get(BookmarkViewModel::class.java)
        if (selectedProject?.isBookmarked!!) {
            bookmarkAnim.speed = defaultSpeed * 3
            bookmarkAnim.playAnimation()
            bookmarkLabel.text = getString(R.string.unbookmark_project)
        }


        bookmarkAnim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                if (!selectedProject.isBookmarked)
                    bookmarkLabel.text = getString(R.string.bookmark_project)
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {}
        })

        PushDownAnim.setPushDownAnimTo(bookmarkProjectButton).setScale(PushDownAnim.MODE_STATIC_DP, 5F).setOnClickListener {
            bookmarkAction(selectedProject, defaultSpeed)
        }
    }


    private fun bookmarkAction(selectedProject: Project, defaultSpeed: Float) {
        if (selectedProject.isBookmarked) {
            bookmarkAnim.speed = -2 * defaultSpeed
            bookmarkAnim.playAnimation()
            bookmarkViewModel.unbookmarkProject(selectedProject.id)
        } else {
            bookmarkViewModel.bookmarkProject(selectedProject.id)
            bookmarkAnim.speed = defaultSpeed
            bookmarkAnim.playAnimation()
            bookmarkLabel.text = getString(R.string.unbookmark_project)
        }
        selectedProject.isBookmarked = !selectedProject.isBookmarked
    }

}