package ua.com.location.models.registerModel

import com.google.firebase.auth.FirebaseAuth

interface IRegister {
    fun creatUserInfo(mAuth: FirebaseAuth)
    fun upDataRegistr(mAuth: FirebaseAuth)
}