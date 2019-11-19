package ua.com.location.di.login

import dagger.Component
import ua.com.location.presentation.login.Login

@Component(modules = arrayOf(LoginPresentModul::class, LoginViewProvid::class))
interface LoginComponent {
    fun inject(login: Login)
}