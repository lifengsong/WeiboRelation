package com.streestone.weiborelation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WeiboLoginViewModel(application: Application) : AndroidViewModel(application) {

    val isSuccess: LiveData<Boolean> = MutableLiveData()

}