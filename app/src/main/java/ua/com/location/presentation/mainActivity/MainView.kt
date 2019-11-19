package ua.com.location.presentation.mainActivity

import androidx.lifecycle.LifecycleOwner

interface MainView {
    fun actionMassege(key: String)
    fun getLifecycleOwner():LifecycleOwner
}