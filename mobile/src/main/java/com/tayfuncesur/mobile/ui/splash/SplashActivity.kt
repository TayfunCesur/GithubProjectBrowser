package com.tayfuncesur.mobile.ui.splash

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.tayfuncesur.mobile.R
import com.tayfuncesur.mobile.base.BaseDaggerActivity
import com.tayfuncesur.mobile.di.ViewModelFactory
import com.tayfuncesur.mobile.ui.main.MainActivity
import com.tayfuncesur.presentation.SplashScreenViewModel
import javax.inject.Inject

class SplashActivity : BaseDaggerActivity() {

    private lateinit var splashScreenViewModel: SplashScreenViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.splash_screen)

        splashScreenViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashScreenViewModel::class.java)

        splashScreenViewModel.isFinished.observe(this, Observer {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        })
    }


}