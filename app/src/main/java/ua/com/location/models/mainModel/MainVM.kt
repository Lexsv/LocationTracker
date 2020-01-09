package ua.com.location.models.mainModel

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.models.BaseVM
import ua.com.location.repository.room.content.ContentDB
import ua.com.location.repository.room.userinfo.UserInfoDB
import ua.com.location.util.ProvidContext
import ua.com.location.util.isNet
import java.io.File

class MainVM: BaseVM(), IMainVM {


    override fun onStartApp() {
        uiScope.launch {loadUserFromStore()}
        val isUser = uiScope.async {userinfoDB.getAll().isEmpty()}
        uiScope.launch {loadContantFromFireDB(isUser.await())}

    }



    private suspend fun loadUserFromStore() {
        withContext(Dispatchers.IO) {
            val user = userinfoDB.getAll()
            if (!user.isEmpty()) {
                LocalStoreVW.getUserid().postValue(user[0])
                LocalStoreVW.lastID = user[0].lastId
                val contant = contentDB.getAll()
                if (!contant.isEmpty())LocalStoreVW.getContent().postValue(contant)
            }
        }
    }

    private fun loadContantFromFireDB(isUser: Boolean){
       if (!isUser){
           if (isNet(ProvidContext.getContext())){
             firebase.loadContant()

           }else{
              firebase.loadContantFoneWork()
           }
       }

    }



    override fun exit() {
        firebase.saveData()
        ContentDB.closer()
        UserInfoDB.closer()
        LocalStoreVW.clearData()
        val dataBase = File(ProvidContext.getContext().applicationInfo.dataDir + "/databases")
        val dbContent = File(dataBase,"content")
        val dbContent1 = File(dataBase,"content-shm")
        val dbContent2 = File(dataBase,"content-wal")
        val dbUserInfo = File(dataBase,"userinfo")
        val dbUserInfo1 = File(dataBase,"userinfo-shm")
        val dbUserInfo2 = File(dataBase,"userinfo-wal")
        if (dbContent.delete()) {
            dbContent1.delete()
            dbContent2.delete()
            Log.i("** DB Contant Delete **", "OK")
        }else Log.i("** DB Contant Delete **", "fail")

        if (dbUserInfo.delete()) {
            dbUserInfo1.delete()
            dbUserInfo2.delete()
            Log.i("* DB UserInfo Delete *", "OK")
        }else Log.i("* DB UserInfo Delete *", "fail")
    }

}