package ua.com.location.repository.firebase

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.database.FirebaseDatabase
import ua.com.location.repository.room.content.Content

class SetDataFonWork (ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    private var firebaseDatabase = FirebaseDatabase.getInstance().getReference()



    companion object{
        @Volatile
        var storeMap: List<Pair<String, Content>> = mutableListOf()

        @Volatile
        var lastID : List<Pair<String, Int>> = mutableListOf()

    }


    override fun doWork(): Result {

        return try {

                if (!storeMap.isEmpty()){
                    for ((id, obj) in storeMap){
                        firebaseDatabase.child(id).child(obj.id.toString()).setValue(obj)
                    }
                }

            if (!lastID.isEmpty()){
                for ((id, value) in lastID){
                    firebaseDatabase.child(id).child("lastid").setValue(value)
                }
            }


            storeMap = mutableListOf()

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}