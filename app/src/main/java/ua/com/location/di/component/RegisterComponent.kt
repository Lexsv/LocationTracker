package ua.com.location.di.component

import dagger.Component
import ua.com.location.di.modul.RegisterPresentModul
import ua.com.location.di.provid.RounViewProvid
import ua.com.location.presentation.register.Register


@Component(modules = arrayOf( RounViewProvid::class, RegisterPresentModul::class ))
interface RegisterComponent {
    fun inject(register: Register)

}