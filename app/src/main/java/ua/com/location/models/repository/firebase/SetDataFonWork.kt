package ua.com.location.models.repository.firebase

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ua.com.location.data.LocalStoreVW
import ua.com.location.models.repository.room.contant.ContentDB
import ua.com.location.util.ProvidContext

class SetDataFonWork (ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {

        return try {
            val  contentDB = ContentDB.getInit(ProvidContext.getContext()).getContentDao()
            val resalt = GlobalScope.async {
                return@async contentDB.getAll()
            }
            GlobalScope.launch(Dispatchers.Main) {
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference(LocalStoreVW.getUserid().value!!.id)
                val type = resalt.await()
                if (!type.isEmpty()){
                    myRef.setValue(type)
                }
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}