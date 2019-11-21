package ua.com.location.presentation.login

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ua.com.location.models.PostViewModel


interface LoginPresentInterfas {


    fun onStartSckreen(key : String)
    fun onLogin(email: String, password: String)
    fun onStart()

}