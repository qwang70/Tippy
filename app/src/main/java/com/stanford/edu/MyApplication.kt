package com.stanford.edu

import android.app.Application

class MyApplication : Application() {
    companion object {
        var tipInfoList = ArrayList<TipInfo>()
    }
}