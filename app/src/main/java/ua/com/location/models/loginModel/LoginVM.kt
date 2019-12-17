package ua.com.location.models.loginModel

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.models.BaseVM
import ua.com.location.repository.room.contant.Content
import ua.com.location.repository.room.userinfo.UserInfo
import ua.com.location.presentation.login.LoginPresent


class LoginVM : BaseVM(), ILoginVM {


    override fun loadData(mAuth: FirebaseAuth) {
        uiScope.launch { withContext(Dispatchers.IO) { contentDB.deleteAll() } }
        val userInfo = UserInfo(mAuth.uid!!, mAuth.currentUser!!.displayName!!)
        LocalStoreVW.getUserid().value = userInfo
        firebase.loadData(mAuth, callbackToSave)

    }

    override fun isUser(callBack: LoginPresent.CallBack) {
        val user = GlobalScope.async(Dispatchers.IO) { userinfoDB.getAll() }

        uiScope.launch {
            val resultUser = user.await()
            if (!resultUser.isEmpty()) {
                callBack.accessOpen()
                fillDataUser(resultUser[0])
            }
        }

    }

    private fun fillDataUser(userInfo: UserInfo) {
        LocalStoreVW.lastID = userInfo.lastId
        LocalStoreVW.getUserid().value = userInfo
        val dataFromRoom = GlobalScope.async(Dispatchers.IO) { contentDB.getAll() }
        uiScope.launch { LocalStoreVW.getContent().value = dataFromRoom.await() }
    }


    var callbackToSave: CallbackToSave = object : CallbackToSave {
        override fun saveToRoom(any: Any) {
            when (any) {
                is Content -> uiScope.launch {
                    saveToContentDB(any)
                }
                is UserInfo -> uiScope.launch {
                    LocalStoreVW.getUserid().value = any
                    saveToUserInfoDB(any)
                }
            }

        }

    }

    private suspend fun saveToContentDB(content: Content) {
        withContext(Dispatchers.IO) {
            try {
                contentDB.insert(content)
            } catch (e: Exception) {
                Log.e("LoginVM.saveToContentDB", "Id error  of obscure")
            }
        }
    }

    private suspend fun saveToUserInfoDB(userInfo: UserInfo) {
        withContext(Dispatchers.IO) {
            userinfoDB.deleteAll()
            userinfoDB.insert(userInfo)
        }
    }

    override fun clear() {
        clearDB()
    }


    interface CallbackToSave {
        fun saveToRoom(any: Any)
    }


}