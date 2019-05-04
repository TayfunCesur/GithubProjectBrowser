package com.tayfuncesur.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.CountDownTimer
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor() : ViewModel() {

    val isFinished = MutableLiveData<Boolean>()

    init {
        object : CountDownTimer(3000, 1000) {

            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                isFinished.postValue(true)
            }

        }.start()
    }


}