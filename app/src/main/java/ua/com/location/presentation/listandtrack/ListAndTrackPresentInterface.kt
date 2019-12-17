package ua.com.location.presentation.listandtrack

interface ListAndTrackPresentInterface {
    fun onStart()
    fun onExit()
    fun onGoTo(key:String)
}