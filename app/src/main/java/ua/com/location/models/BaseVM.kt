package ua.com.location.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ua.com.location.repository.firebase.DBOFireBase
import ua.com.location.repository.firebase.IDBOFireBase
import ua.com.location.repository.room.contant.ContentDB
import ua.com.location.repository.room.contant.ContentDao
import ua.com.location.repository.room.userinfo.UserInfoDB
import ua.com.location.repository.room.userinfo.UserinfoDao
import ua.com.location.util.ProvidContext

open class BaseVM: ViewModel() {
    private val job = Job()
    protected val uiScope = CoroutineScope(Dispatchers.Main + job)
    var contentDB: ContentDao
    var userinfoDB: UserinfoDao
    var firebase: IDBOFireBase


    init {
        contentDB = ContentDB.getInit(ProvidContext.getContext()).getContentDao()
        userinfoDB = UserInfoDB.getInit(ProvidContext.getContext()).getUserInfoDao()
        firebase = DBOFireBase()
    }
    fun clearDB(){
        GlobalScope.launch {
            contentDB.deleteAll()
            userinfoDB.deleteAll()
        }
    }


}