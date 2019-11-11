package ua.com.location.di.component

import dagger.Component
import ua.com.location.MainActivity
import ua.com.location.di.modul.LoginPresentModul
import ua.com.location.di.modul.RegisterPresentModul
import ua.com.location.di.provid.RounViewProvid
import ua.com.location.fragment.Login
import ua.com.location.fragment.Register

@Component(modules = arrayOf(LoginPresentModul::class, RounViewProvid::class))
interface LoginComponent {
    fun inject(login: Login)
}