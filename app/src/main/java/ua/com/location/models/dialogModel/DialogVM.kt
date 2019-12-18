package ua.com.location.models.dialogModel

import kotlinx.coroutines.*
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.models.BaseVM
import ua.com.location.repository.room.content.Content

class DialogVM : BaseVM(), IDialogVM {
    override fun upDataLastId(lastId: Int) {
        LocalStoreVW.lastID = lastId
        val userInfo = GlobalScope.async(Dispatchers.IO) {userinfoDB.getAll() }

        uiScope.launch {
            val user = userInfo.await()[0]
            user.lastId = lastId
            LocalStoreVW.getUserid().value = user
            withContext(Dispatchers.IO){userinfoDB.update(user)}
        }

            firebase.updateLastId(lastId)
    }


    override fun saveData(content: Content) {
        GlobalScope.launch(Dispatchers.IO) { contentDB.insert(content) }
        firebase.insert(content)
    }

    override fun upDataContant(upDatItom: Content) {
        uiScope.launch { withContext(Dispatchers.IO){contentDB.update(upDatItom)} }
        firebase.insert(upDatItom)

        if (LocalStoreVW.getContent().value!!.size == 1){
            LocalStoreVW.getContent().value = mutableListOf(upDatItom)
        }else{
            val list = LocalStoreVW.getContent().value as ArrayList
            for (l in list){
                if (l.id == upDatItom.id){
                    list[list.indexOf(l)]=  upDatItom
                }
            }
        }
    }
}


