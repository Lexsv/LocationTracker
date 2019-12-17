package ua.com.location.repository.firebase

import com.google.firebase.auth.FirebaseAuth
import ua.com.location.models.loginModel.LoginVM
import ua.com.location.repository.room.contant.Content

interface IDBOFireBase {

    fun insert(content: Content)
    fun update(content: Content)
    fun deleteContent(id: String)
    fun updateLastId(lastID: Int)
    fun loadContant()
    fun loadContantFoneWork()
    fun loadData(mAuth: FirebaseAuth,callbackToSave: LoginVM.CallbackToSave)


}