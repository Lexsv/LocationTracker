package ua.com.location.presentation.listandtrack

import androidx.lifecycle.LifecycleOwner
import ua.com.location.models.IDistributorData
import ua.com.location.models.repository.room.contant.Content

interface ListAndTrackView {
    fun showRecyclerList(list: List<Content>)
    fun getLifecycleOwner(): LifecycleOwner
    fun showDialig(item: Content)
    fun gotoFragment(key:String)
    fun getVM(): IDistributorData
    fun rout(key: String)
}