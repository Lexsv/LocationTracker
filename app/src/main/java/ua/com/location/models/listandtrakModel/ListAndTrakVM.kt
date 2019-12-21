package ua.com.location.models.listandtrakModel

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.com.location.models.BaseVM
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.repository.room.content.Content
import ua.com.location.repository.room.content.ContentDB
import ua.com.location.repository.room.userinfo.UserInfoDB
import ua.com.location.util.ProvidContext
import java.io.File

class ListAndTrakVM: BaseVM(),IListAndTrak {

    override fun removeItom(content: Content) {
        uiScope.launch { withContext(Dispatchers.IO){contentDB.delete(content)} }
        firebase.deleteContent(content.id.toString())


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

    override fun reestablishItom() {
        GlobalScope.launch(Dispatchers.IO) { contentDB.insert(LocalStoreVW.lastremovedItom.second!!) }
        firebase.insert(LocalStoreVW.lastremovedItom.second!!)
        if (LocalStoreVW.getContent().value == null){
            LocalStoreVW.getContent().value = listOf( LocalStoreVW.lastremovedItom.second!!)
        }else{
            if(LocalStoreVW.getContent().value!!.size == 1){
                LocalStoreVW.getContent().value =  LocalStoreVW.getContent().value!!.plusElement(LocalStoreVW.lastremovedItom.second!!)
            }else{
                val arrayList =  LocalStoreVW.getContent().value as ArrayList
                arrayList.add(LocalStoreVW.lastremovedItom.first,LocalStoreVW.lastremovedItom.second!!)
                LocalStoreVW.getContent().value = arrayList
            }
        }

    }
}