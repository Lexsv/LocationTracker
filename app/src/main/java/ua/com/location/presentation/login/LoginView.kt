package ua.com.location.presentation.login

import ua.com.location.data.room.AppDatabase

interface LoginView  {
    fun rout(key: String)
    fun actionMassege(key: String)
    fun  getAppDataBase(): AppDatabase


}