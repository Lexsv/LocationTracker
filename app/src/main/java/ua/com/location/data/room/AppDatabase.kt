package ua.com.location.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DataBaseObjact::class),version = 1,exportSchema = false)


abstract class AppDatabase : RoomDatabase() {
    abstract fun dataBaseDou(): DataBaseInterfas

    companion object {
        private var INIT: AppDatabase? = null

        fun getAppDatabase(context: Context):AppDatabase?  {
           if (INIT == null){
               INIT = Room.databaseBuilder( context ,AppDatabase::class.java,"user").build()}
           return INIT
        }
        fun destroyInstance() {

            if (INIT?.isOpen == true) {
                INIT?.close()
            }

            INIT = null
        }
    }
}