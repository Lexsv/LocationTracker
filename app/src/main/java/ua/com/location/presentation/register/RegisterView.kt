package ua.com.location.presentation.register

import ua.com.location.models.registerModel.IRegister

interface RegisterView {
    fun rout(key: String)
    fun actionMassege(key: String)
    fun getVM(): IRegister
}