package ua.com.location.di.register

import dagger.Component
import ua.com.location.di.login.LoginViewProvid
import ua.com.location.presentation.register.Register


@Component(modules = arrayOf( LoginViewProvid::class, RegisterPresentModul::class ))
interface RegisterComponent {
    fun inject(register: Register)

}