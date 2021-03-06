package com.app.keepcount.application

import android.app.Application
import com.app.keepcount.utility.SharedPreferenceUtil
import com.google.firebase.FirebaseApp

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        SharedPreferenceUtil.initialize(this)
    }
}