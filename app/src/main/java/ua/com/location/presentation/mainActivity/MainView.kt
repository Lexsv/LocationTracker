package ua.com.location.presentation.mainActivity

import androidx.lifecycle.LifecycleOwner
import ua.com.location.models.mainModel.IMainVM

interface MainView {
    fun actionMassege(key: String)
    fun getLifecycleOwner():LifecycleOwner
    fun getVM(): IMainVM
}