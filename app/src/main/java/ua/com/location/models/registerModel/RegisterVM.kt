package ua.com.location.models.registerModel

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.models.BaseVM
import ua.com.location.repository.room.userinfo.UserInfo

class RegisterVM: BaseVM(), IRegister {
    override fun creatUserInfo(mAuth: FirebaseAuth) {
        val userInfo = UserInfo(mAuth.uid!!, lastId = 0)
        LocalStoreVW.getUserid().postValue(userInfo)
        uiScope.launch {saveToUserInfoDB(userInfo) }
        FirebaseDatabase.getInstance().getReference().child(mAuth.uid!!).child("lastid").setValue(0)
    }

    private suspend fun saveToUserInfoDB(userInfo: UserInfo) {
        withContext(Dispatchers.IO) {
            userinfoDB.insert(userInfo)
        }
    }


    override fun upDataRegistr(mAuth: FirebaseAuth) {
        LocalStoreVW.getUserid().value = UserInfo(mAuth.uid!!,mAuth.currentUser!!.displayName!!,0)
        uiScope.launch { updataUserToDB(mAuth) }
    }

    private suspend fun updataUserToDB(mAuth: FirebaseAuth){
        withContext(Dispatchers.IO){
            userinfoDB.update(UserInfo(mAuth.uid!!,mAuth.currentUser!!.displayName!!,LocalStoreVW.lastID))
        }
    }
}