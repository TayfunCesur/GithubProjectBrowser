package com.tayfuncesur.mobile.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
open class ViewModelFactory : ViewModelProvider.Factory {

    private val creators: Map<Class<out ViewModel>, Provider<ViewModel>>

    @Inject constructor(creators: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>) {
        this.creators = creators
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalStateException("Unknown model class: " + modelClass)
        }
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}