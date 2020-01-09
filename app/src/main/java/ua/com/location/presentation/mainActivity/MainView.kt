package ua.com.location.presentation.mainActivity

import androidx.lifecycle.LifecycleOwner
import ua.com.location.models.mainModel.IMainVM
import ua.com.location.repository.room.userinfo.UserInfo
import ua.com.location.repository.room.userinfo.UserInfoDB

interface MainView {
    fun actionMassege(key: String)
    fun getLifecycleOwner():LifecycleOwner
    fun getVM(): IMainVM
    fun goTo(key: String)
    fun unMenuvisibility()
    fun menuvisibility()
    fun nameUser(userInfo: UserInfo)
}