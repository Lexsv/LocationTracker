package ua.com.location.repository.data

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.database.FirebaseDatabase
import ua.com.location.util.myLocation
import java.lang.Exception

class MyLocation(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    private var firebaseDatabase = FirebaseDatabase.getInstance().getReference()

    override fun doWork(): Result {



        return try {

            myLocation().addOnSuccessListener {
                firebaseDatabase.child("lastLoc").setValue("lan: ${it.latitude} lon: ${it.longitude}")
            }
            Log.i("++++++++++++++","ok")


            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

}