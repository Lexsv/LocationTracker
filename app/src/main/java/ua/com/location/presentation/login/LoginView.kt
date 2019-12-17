package ua.com.location.presentation.login

import androidx.lifecycle.LifecycleOwner
import ua.com.location.models.loginModel.ILoginVM

interface LoginView  {
    fun rout(key: String)
    fun actionMassege(key: String)
    fun getLifecycleOwner(): LifecycleOwner
    fun getVM(): ILoginVM
}