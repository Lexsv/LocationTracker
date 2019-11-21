package ua.com.location.presentation.login

import ua.com.location.data.room.AppDatabase
import ua.com.location.models.PostViewModel

interface LoginView  {
    fun rout(key: String)
    fun actionMassege(key: String)
    fun getVM(): PostViewModel


}