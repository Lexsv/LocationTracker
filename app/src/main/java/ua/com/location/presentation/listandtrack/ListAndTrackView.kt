package ua.com.location.presentation.listandtrack

import androidx.lifecycle.LifecycleOwner
import ua.com.location.data.LocatoinTrak

interface ListAndTrackView {
    fun showRecyclerList(list: List<LocatoinTrak>)
    fun getLifecycleOwner(): LifecycleOwner
}