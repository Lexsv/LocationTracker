package ua.com.location.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DataBaseObjact::class), version = 1, exportSchema = false)


abstract class AppDatabase : RoomDatabase() {
    abstract fun dataBaseDou(): DataBaseDAO

    companion object {
        @Volatile
        private var INIT: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            val tempvalue = INIT
            if (tempvalue != null) {
                return tempvalue
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, "user").build()
                INIT = instance
                return instance
            }

        }
    }
}