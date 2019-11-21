package ua.com.location.presentation.listandtrack

import androidx.lifecycle.LifecycleOwner
import ua.com.location.data.LocatoinTrak
import ua.com.location.models.PostViewModel

interface ListAndTrackView {
    fun showRecyclerList(list: List<LocatoinTrak>)
    fun getLifecycleOwner(): LifecycleOwner
    fun showDialig(item: LocatoinTrak)
    fun gotoFragment(key:String)
    fun getVM(): PostViewModel
}