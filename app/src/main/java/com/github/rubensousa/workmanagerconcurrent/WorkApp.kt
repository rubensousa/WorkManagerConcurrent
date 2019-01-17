package com.github.rubensousa.workmanagerconcurrent

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager

class WorkApp : Application() {

    override fun onCreate() {
        super.onCreate()
        WorkManager.initialize(this, Configuration.Builder().build())
    }

}