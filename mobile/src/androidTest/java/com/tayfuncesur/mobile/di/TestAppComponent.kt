package com.tayfuncesur.mobile.di

import android.app.Application
import com.tayfuncesur.domain.repository.ProjectsRepository
import com.tayfuncesur.mobile.test.TestApp
import com.tayfuncesur.mobile.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        TestApplicationModule::class,
        TestCacheModule::class,
        TestDataModule::class,
        PresentationModule::class,
        UIModule::class,
        TestRemoteModule::class
    ]
)
interface TestAppComponent {


    fun projectsRepository(): ProjectsRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): TestAppComponent.Builder

        fun build(): TestAppComponent
    }

    fun inject(app: TestApp)

}

