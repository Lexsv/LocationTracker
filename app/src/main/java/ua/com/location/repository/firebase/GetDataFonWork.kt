package ua.com.location.repository.firebase

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.database.*
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.repository.room.contant.Content


class GetDataFonWork(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {




    override fun doWork(): Result {



        return try {
            val myRef = FirebaseDatabase.getInstance().getReference(LocalStoreVW.getUserid().value!!.id)
            val myQuery: Query = myRef

            myQuery.addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                    val content = p0.getValue(Content::class.java)
                    val list = LocalStoreVW.getContent().value!!
                    val set = list.union(listOf(content!!))
                    LocalStoreVW.getContent().value = set.toList()
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

           Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}