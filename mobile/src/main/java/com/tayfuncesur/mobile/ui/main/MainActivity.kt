package com.tayfuncesur.mobile.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.tayfuncesur.mobile.R
import com.tayfuncesur.mobile.base.BaseDaggerActivity
import com.tayfuncesur.mobile.base.UIConstants.Selected_Item_Key
import com.tayfuncesur.mobile.base.launchActivity
import com.tayfuncesur.mobile.di.ViewModelFactory
import com.tayfuncesur.mobile.mapper.ProjectMapper
import com.tayfuncesur.mobile.model.Project
import com.tayfuncesur.mobile.ui.detail.MainDetailActivity
import com.tayfuncesur.mobile.ui.noConnection.NoConnectionFragment
import com.tayfuncesur.presentation.ProjectsViewModel
import com.tayfuncesur.presentation.state.Resource
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseDaggerActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var mapper: ProjectMapper

    lateinit var projectsViewModel: ProjectsViewModel

    private var mainAdapter: MainAdapter? = null

    private var dataSource: List<Project>? = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        projectsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProjectsViewModel::class.java)

        projectsViewModel.getRemoteProjectsLiveData().observe(this, Observer { it ->
            if (it is Resource.Success) {
                dataSource = it.data?.map { mapper.mapToView(it) }

                mainAdapter = dataSource?.let { it1 ->
                    MainAdapter(it1) {
                        val bundle = Bundle(1)
                        bundle.putParcelable(Selected_Item_Key, it)
                        launchActivity<MainDetailActivity>(bundle)
                    }
                }
                mainRecycler.adapter = mainAdapter
            }
            loadingLayout.visibility = if (it is Resource.Loading) View.VISIBLE else View.GONE
            errorLayout.visibility = if (it is Resource.Failure) View.VISIBLE else View.GONE
        })

        projectsViewModel.getBookmarkedProjectsLiveData().observe(this, Observer { it ->
            if (it is Resource.Success) {
                if (it.data?.isNotEmpty()!!) {
                    dataSource?.map { project ->
                        for (id in it.data!!) {
                            if (project.id == id) {
                                project.isBookmarked = true
                                break
                            } else {
                                project.isBookmarked = false
                            }
                        }
                    }
                } else {
                    dataSource?.map {
                        it.isBookmarked = false
                    }
                }
                dataSource?.let { it1 -> mainAdapter?.updateData(it1) }
            }
        })


        PushDownAnim.setPushDownAnimTo(tryAgainLayout).setScale(PushDownAnim.MODE_STATIC_DP, 5F).setOnClickListener {
            projectsViewModel.loadProjects()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            var founded = false
            for (item in supportFragmentManager.fragments) {
                if (item is NoConnectionFragment)
                    founded = true
            }
            if (founded) finishAffinity()
            else super.onBackPressed()
        } else {
            super.onBackPressed()
        }

    }

}
