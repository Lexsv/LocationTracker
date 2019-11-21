package ua.com.location.di.register

import dagger.Component
import ua.com.location.di.login.LoginViewProvid
import ua.com.location.presentation.register.Register
import ua.com.location.presentation.register.RegisterView


@Component(modules = arrayOf( RegisterViewProvid::class, RegisterPresentModul::class ))
interface RegisterComponent {
    fun inject(register: Register)

}