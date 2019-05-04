package com.tayfuncesur.mobile.di.module

import com.tayfuncesur.domain.executor.PostExecutionThread
import com.tayfuncesur.mobile.UIThread
import com.tayfuncesur.mobile.mapper.ProjectMapper
import com.tayfuncesur.mobile.ui.detail.MainDetailActivity
import com.tayfuncesur.mobile.ui.main.MainActivity
import com.tayfuncesur.mobile.ui.splash.SplashActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class UIModule {

    @Module
    companion object{
        @Provides
        @Singleton
        @JvmStatic
        fun providesProjectViewMapper() : ProjectMapper{
            return ProjectMapper()
        }
    }

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesSplashScreenActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesMainDetailActivity(): MainDetailActivity

}