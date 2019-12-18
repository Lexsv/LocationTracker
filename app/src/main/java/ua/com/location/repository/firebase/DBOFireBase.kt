package ua.com.location.repository.firebase


import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.models.loginModel.LoginVM
import ua.com.location.repository.room.content.Content
import ua.com.location.repository.room.userinfo.UserInfo
import ua.com.location.util.ProvidContext
import ua.com.location.util.isNet

class DBOFireBase : IDBOFireBase {
    val database: FirebaseDatabase
    val myRef: DatabaseReference

    init {
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference()
    }


    override fun loadContant(){
        val myQuery: Query = myRef.child(LocalStoreVW.getUserid().value!!.id)
        myQuery.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                when(p0.key){
                    "lastid" -> {
                        val laslIdFireDB = p0.getValue(Int::class.java)
                        val lastIDRoom = LocalStoreVW.lastID
                        LocalStoreVW.lastID = if(lastIDRoom > laslIdFireDB!!) lastIDRoom else laslIdFireDB
                    }
                    else -> {
                        val content = p0.getValue(Content::class.java)
                        val list = LocalStoreVW.getContent().value!!
                        val set = list.union(listOf(content!!))
                        LocalStoreVW.getContent().value = set.toList()
                    }

                }





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


    }

    override fun loadContantFoneWork(){
        val workManager = WorkManager.getInstance(ProvidContext.getContext())
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val myWorkRequest = OneTimeWorkRequest.Builder(SetDataFonWork::class.java)
            .setConstraints(constraints)
            .build()
        workManager.enqueue(myWorkRequest)
    }

    override fun insert(content: Content) {
        if (isNet(ProvidContext.getContext())) {
            myRef.child(LocalStoreVW.getUserid().value!!.id).child(content.id.toString())
                .setValue(content)
        } else {

            myRef.child(LocalStoreVW.getUserid().value!!.id).child(content.id.toString())
                .setValue(content)


            SetDataFonWork.storeMap = SetDataFonWork.storeMap.plusElement(LocalStoreVW.getUserid().value!!.id to content)
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



    override fun update(content: Content) {

    }


    override fun loadData(mAuth: FirebaseAuth,callbackToSave: LoginVM.CallbackToSave) {
        val myQuery: Query = myRef.child(mAuth.uid!!)
        myQuery.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                when(p0.key){
                    "lastid" -> {
                        val lastID = p0.getValue(Int::class.java)!!
                        LocalStoreVW.lastID = p0.getValue(Int::class.java)!!
                        callbackToSave.saveToRoom(UserInfo(mAuth.uid!!,mAuth.currentUser!!.displayName!!,lastID))
                    }else -> {
                        val content = p0.getValue(Content::class.java)
                        val list = LocalStoreVW.getContent().value
                        if (list == null){
                            LocalStoreVW.getContent().value = listOf(content!!)
                        }else{
                            val set = list.union(listOf(content!!))
                            LocalStoreVW.getContent().value = set.toList()
                        }
                        callbackToSave.saveToRoom(content)
                    }
                }

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

    }

    override fun deleteContent(id: String) {
       myRef.child(LocalStoreVW.getUserid().value!!.id).child(id).removeValue()
    }

    override fun updateLastId(lastID: Int) {
        if (isNet(ProvidContext.getContext())){
            myRef.child(LocalStoreVW.getUserid().value!!.id).child("lastid").setValue(lastID)
        }else{
            val tempValue = SetDataFonWork.lastID
            val userId = LocalStoreVW.getUserid().value!!.id
            SetDataFonWork.lastID = tempValue.plusElement(userId to lastID)

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

    override fun saveData() {
        val list = LocalStoreVW.getContent().value
        val lastId = LocalStoreVW.lastID
        updateLastId(lastId)
        if (list != null){
            for (c in list){
                insert(c)
            }
        }


    }
}