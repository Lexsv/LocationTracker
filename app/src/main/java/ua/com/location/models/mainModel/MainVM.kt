package ua.com.location.models.mainModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.models.BaseVM
import ua.com.location.util.ProvidContext
import ua.com.location.util.isNet

class MainVM: BaseVM(), IMainVM {

    override fun saveDataUser() {

    }

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

}