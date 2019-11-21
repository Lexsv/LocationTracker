package ua.com.location.presentation.register

import ua.com.location.models.PostViewModel

interface RegisterView {
    fun rout(key: String)
    fun actionMassege(key: String)
    fun getVM(): PostViewModel
}