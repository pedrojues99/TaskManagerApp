package com.kaising.taskmanagerapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
public class App @Inject constructor(): Application() {
    // You can override onCreate() or other Application methods if needed
    override fun onCreate() {
        super.onCreate()
        // Hilt initialization happens automatically
    }
}