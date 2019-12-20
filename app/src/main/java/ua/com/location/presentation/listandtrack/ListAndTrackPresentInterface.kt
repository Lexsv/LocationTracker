package ua.com.location.presentation.listandtrack

import ua.com.location.repository.room.content.Content

interface ListAndTrackPresentInterface {
    fun onStart()
    fun onExit()
    fun onGoTo(key:String)
    fun remove(position: Int)
    fun creatPath(position: Int)
    fun nowUpdat(item: Content)
}