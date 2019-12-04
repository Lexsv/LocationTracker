package ua.com.location.presentation.register

import ua.com.location.models.IDistributorData

interface RegisterView {
    fun rout(key: String)
    fun actionMassege(key: String)
    fun getVM(): IDistributorData
}