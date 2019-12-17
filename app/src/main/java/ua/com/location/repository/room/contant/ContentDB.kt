package ua.com.location.repository.room.contant

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Content::class),version = 2,exportSchema = false)
abstract class ContentDB  : RoomDatabase(){
    abstract fun getContentDao(): ContentDao

    companion object{
        @Volatile
        private var INIT: ContentDB? = null

        fun getInit(context: Context): ContentDB {
            synchronized(this) {
                var temp = INIT
                if (temp == null) {
                     temp = Room.databaseBuilder(context, ContentDB::class.java, "content")
                         .fallbackToDestructiveMigration()
                         .build()
                    INIT = temp
                }
                return temp
            }
        }
        
        fun closer(){
            if (INIT != null){
            INIT!!.close()
            INIT = null
            }
        }
    }
}