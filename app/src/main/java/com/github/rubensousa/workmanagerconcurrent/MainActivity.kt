package com.github.rubensousa.workmanagerconcurrent

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import androidx.work.impl.utils.WakeLocks
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            handler.postDelayed(this, 1)
            WakeLocks.checkWakeLocks()
        }
    }
    private lateinit var btn: View
    private val classes = arrayOf(
        TestWorkerA::class.java,
        TestWorkerB::class.java,
        TestWorkerC::class.java,
        TestWorkerD::class.java,
        TestWorkerE::class.java
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById<View>(R.id.startBtn)
        if (savedInstanceState == null) {
            handler.post(runnable)
            startWork()
        }
    }

    override fun onPause() {
        super.onPause()
        btn.setOnClickListener(null)
    }

    override fun onResume() {
        super.onResume()
        btn.setOnClickListener {
            startWork()
        }
    }

    private fun startWork() {
        for (classe in classes) {
            WorkManager.getInstance().enqueueUniquePeriodicWork(
                classe.simpleName,
                ExistingPeriodicWorkPolicy.REPLACE,
                PeriodicWorkRequest.Builder(classe, 15, TimeUnit.MINUTES)
                    .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
                    .setConstraints(getDefaultConstraints()).build()
            )
        }
    }

    private fun getDefaultConstraints() = Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .build()
}
