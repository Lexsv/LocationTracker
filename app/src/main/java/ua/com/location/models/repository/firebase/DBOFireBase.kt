package ua.com.location.models.repository.firebase


import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.firebase.database.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.com.location.data.LocalStoreVW
import ua.com.location.data.LocatoinTrak
import ua.com.location.models.repository.room.contant.Content
import ua.com.location.util.ProvidContext
import ua.com.location.util.isNet

class DBOFireBase : IDBOFireBase {
    val database: FirebaseDatabase
    val myRef: DatabaseReference

    init {
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("lod")
    }

    override suspend fun insert(list: List<Content>) {
        if (isNet(ProvidContext.getContext())){
                myRef.setValue(list)
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

    override suspend fun update(content: Content) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override  fun getUserData(id: String) {
        if (isNet(ProvidContext.getContext())) {
            val myQuery: Query = myRef.child(id)
            myQuery.addChildEventListener(object : ChildEventListener{
                override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                    val content = p0.getValue(Content::class.java)
                    val list = LocalStoreVW.getContent().value!!
                    val set = list.union(listOf(content!!))
                    LocalStoreVW.getContent().postValue(set.toList())

                }

                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {

                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                }

                override fun onChildRemoved(p0: DataSnapshot) {

                }
            })}
//        } else {
//            val workManager = WorkManager.getInstance(ProvidContext.getContext())
//                    val constraints: Constraints = Constraints.Builder()
//                        .setRequiredNetworkType(NetworkType.CONNECTED)
//                        .build()
//                    val myWorkRequest = OneTimeWorkRequest.Builder(GetDataFonWork::class.java)
//                        .setConstraints(constraints)
//                        .build()
//                    workManager.enqueue(myWorkRequest)
//        }

    }

    override suspend fun deleteContent(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}