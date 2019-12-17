package ua.com.location.models.loginModel

import com.google.firebase.auth.FirebaseAuth
import ua.com.location.presentation.login.LoginPresent

interface ILoginVM {
    fun loadData(mAuth: FirebaseAuth)
    fun isUser(callBack: LoginPresent.CallBack)
    fun clear()
}