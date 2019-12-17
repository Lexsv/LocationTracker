package ua.com.location.models.listandtrakModel

import android.util.Log
import ua.com.location.models.BaseVM
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.repository.room.contant.ContentDB
import ua.com.location.repository.room.userinfo.UserInfoDB
import ua.com.location.util.ProvidContext
import java.io.File

class ListAndTrakVM: BaseVM(),IListAndTrak {
    override fun exit() {
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