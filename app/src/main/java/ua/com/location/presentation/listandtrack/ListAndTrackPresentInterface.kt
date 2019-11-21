package ua.com.location.presentation.listandtrack

import ua.com.location.data.LocatoinTrak

interface ListAndTrackPresentInterface {
    fun onStart()
    fun onExit()
    fun onGoTo(key:String)
}