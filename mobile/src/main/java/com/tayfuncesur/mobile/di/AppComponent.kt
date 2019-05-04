package com.tayfuncesur.mobile.di

import android.app.Application
import com.tayfuncesur.mobile.App
import com.tayfuncesur.mobile.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ApplicationModule::class,
        CacheModule::class,
        DataModule::class,
        PresentationModule::class,
        RemoteModule::class,
        UIModule::class]
)
interface AppComponent {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}

