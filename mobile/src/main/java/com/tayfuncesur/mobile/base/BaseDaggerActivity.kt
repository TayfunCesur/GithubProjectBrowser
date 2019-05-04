package com.tayfuncesur.mobile.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.tayfuncesur.mobile.R
import com.tayfuncesur.mobile.ui.noConnection.NoConnectionFragment
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

open class BaseDaggerActivity : AppCompatActivity() {


    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)


        compositeDisposable.add(
            ReactiveNetwork
                .observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isConnectedToInternet ->
                    val noConnectionFragment = NoConnectionFragment.newInstance()
                    if (!isConnectedToInternet) {
                        supportFragmentManager.beginTransaction().setCustomAnimations(
                            android.R.anim.fade_in,
                            android.R.anim.fade_out,
                            android.R.anim.fade_in,
                            android.R.anim.fade_out
                        )
                            .add(android.R.id.content, noConnectionFragment, getString(R.string.noConnectionStr))
                            .addToBackStack(getString(R.string.noConnectionStr)).commit()
                    } else {
                        supportFragmentManager.popBackStack()
                        supportFragmentManager.executePendingTransactions()
                    }
                }
        )
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }



}