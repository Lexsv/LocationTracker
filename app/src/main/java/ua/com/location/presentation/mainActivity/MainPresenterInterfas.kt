package ua.com.location.presentation.mainActivity

import android.content.Context

interface MainPresenterInterfas {
    fun onStart(context: Context)
    fun onSaveData()
    fun onDestroy()
}