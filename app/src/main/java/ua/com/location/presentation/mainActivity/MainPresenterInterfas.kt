package ua.com.location.presentation.mainActivity

import android.content.Context

interface MainPresenterInterfas {
    fun onStart(context: Context)
    fun onExit()
    fun onClick(key:String)
}