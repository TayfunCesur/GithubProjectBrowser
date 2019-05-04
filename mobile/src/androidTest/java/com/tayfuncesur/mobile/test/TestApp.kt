package com.tayfuncesur.mobile.test

import android.app.Activity
import android.app.Application
import android.support.test.InstrumentationRegistry
import com.tayfuncesur.mobile.di.DaggerTestAppComponent
import com.tayfuncesur.mobile.di.TestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TestApp : Application(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>

    private lateinit var appComponent: TestAppComponent


    companion object {
        fun appComponent(): TestAppComponent {
            return (InstrumentationRegistry.getTargetContext().applicationContext as TestApp).appComponent
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerTestAppComponent.builder().application(this).build()
        appComponent.inject(this)

    }


}