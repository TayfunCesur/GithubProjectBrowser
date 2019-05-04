package com.tayfuncesur.mobile.di.module

import com.tayfuncesur.domain.executor.PostExecutionThread
import com.tayfuncesur.mobile.UIThread
import com.tayfuncesur.mobile.ui.splash.SplashActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UIModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesSplashScreenActivity(): SplashActivity

}