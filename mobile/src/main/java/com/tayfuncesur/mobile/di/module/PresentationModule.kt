package com.tayfuncesur.mobile.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tayfuncesur.mobile.di.ViewModelFactory
import com.tayfuncesur.presentation.BookmarkViewModel
import com.tayfuncesur.presentation.ProjectsViewModel
import com.tayfuncesur.presentation.SplashScreenViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    abstract fun bindSplashScreenViewModel(
        splashViewModel: SplashScreenViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProjectsViewModel::class)
    abstract fun bindProjectsViewModel(
        projectsViewModel: ProjectsViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookmarkViewModel::class)
    abstract fun bindBookmarkViewModel(
        bookmarkViewModel: BookmarkViewModel
    ): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)