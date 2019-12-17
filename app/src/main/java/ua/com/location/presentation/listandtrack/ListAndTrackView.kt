package ua.com.location.presentation.listandtrack

import androidx.lifecycle.LifecycleOwner
import ua.com.location.models.listandtrakModel.IListAndTrak
import ua.com.location.repository.room.contant.Content

interface ListAndTrackView {
    fun showRecyclerList(list: List<Content>)
    fun getLifecycleOwner(): LifecycleOwner
    fun showDialig(item: Content)
    fun gotoFragment(key:String)
    fun getVM(): IListAndTrak
    fun rout(key: String)
}