package ua.com.location.models

import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.*
import ua.com.location.data.LocalStoreVW

import ua.com.location.models.repository.firebase.DBOFireBase
import ua.com.location.models.repository.firebase.GetDataFonWork
import ua.com.location.models.repository.firebase.IDBOFireBase
import ua.com.location.models.repository.firebase.SetDataFonWork
import ua.com.location.models.repository.room.contant.Content
import ua.com.location.models.repository.room.contant.ContentDB
import ua.com.location.models.repository.room.contant.ContentDao
import ua.com.location.models.repository.room.userinfo.UserInfo
import ua.com.location.models.repository.room.userinfo.UserInfoDB
import ua.com.location.models.repository.room.userinfo.UserinfoDao
import ua.com.location.util.ProvidContext
import ua.com.location.util.isNet


class DistributorDataVM() : ViewModel(), IDistributorData {
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    var contentDB: ContentDao
    var userinfoDB: UserinfoDao
    var firebase: IDBOFireBase

    init {
        contentDB = ContentDB.getInit(ProvidContext.getContext()).getContentDao()
        userinfoDB = UserInfoDB.getInit(ProvidContext.getContext()).getUserInfoDao()
        firebase = DBOFireBase()
    }

    override fun onStartApp() {
        uiScope.launch {
            loadUserFromStore()
        }


    }

    override fun registr(mAuth: FirebaseAuth) {
        uiScope.launch {
            loadUserToDB(mAuth)
        }
        LocalStoreVW.getUserid().postValue(UserInfo(mAuth.uid!!,mAuth.currentUser!!.displayName!!))
    }

    private suspend fun loadUserToDB(mAuth: FirebaseAuth){
        withContext(Dispatchers.IO){
            userinfoDB.deleteAll()
            userinfoDB.insert(UserInfo(mAuth.uid!!,mAuth.currentUser!!.displayName!!))
        }
    }

    override fun saveToRoom(any: Any) {
        when (any) {
            is Content -> uiScope.launch { saveToContentDB(any) }
            is UserInfo -> uiScope.launch { saveToUserInfoDB(any) }
        }


    }


    override fun exit() {
        uiScope.launch {
            clearData()
        }
        LocalStoreVW.clearData()
    }


    private suspend fun clearData() {
        withContext(Dispatchers.IO) {
            contentDB.deleteAll()
            userinfoDB.deleteAll()
        }
    }

    private suspend fun saveToContentDB(content: Content) {
        withContext(Dispatchers.IO) {
            contentDB.insert(content)
        }
    }

    private suspend fun saveToUserInfoDB(userInfo: UserInfo) {
        withContext(Dispatchers.IO) {
            userinfoDB.insert(userInfo)
        }
    }


    override fun saveToFire() {
        if (isNet(ProvidContext.getContext())) {
            val resalt = GlobalScope.async {
                return@async contentDB.getAll()
            }
            uiScope.launch {
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference(LocalStoreVW.getUserid().value!!.id)
                val type = resalt.await()
                if (!type.isEmpty()){
                    myRef.setValue(type)
                }
            }
        }else{
            val workManager = WorkManager.getInstance(ProvidContext.getContext())
            val constraints: Constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            val myWorkRequest = OneTimeWorkRequest.Builder(SetDataFonWork::class.java)
                .setConstraints(constraints)
                .build()
            workManager.enqueue(myWorkRequest)
        }

    }

    override fun fillData() {
        uiScope.launch {
            loadData()
        }
    }

    override fun getDataUserFromFir(id:String) {


        val myRef = FirebaseDatabase.getInstance().getReference(id)

        if (isNet(ProvidContext.getContext())) {
            val myQuery: Query = myRef
            myQuery.addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                    val content = p0.getValue(Content::class.java)
                    val list = LocalStoreVW.getContent().value!!
                    val set = list.union(listOf(content!!))
                    LocalStoreVW.setValContent(set.toList())

                }

                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {

                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                }

                override fun onChildRemoved(p0: DataSnapshot) {

                }
            })


        }else{
            val workManager = WorkManager.getInstance(ProvidContext.getContext())
            val constraints: Constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            val myWorkRequest = OneTimeWorkRequest.Builder(GetDataFonWork::class.java)
                .setConstraints(constraints)
                .build()
            workManager.enqueue(myWorkRequest)
        }


    }

    private suspend fun loadUserFromStore() {
        withContext(Dispatchers.IO) {
            val user = userinfoDB.getAll()
            if (!user.isEmpty()) {
                LocalStoreVW.getUserid().postValue(user[0])
            }
        }
    }

    private suspend fun loadData() {
        withContext(Dispatchers.IO) {
            val listRoom = contentDB.getAll()
            LocalStoreVW.getContent().postValue(listRoom)
        }
    }


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}