package ua.com.location.repository.room.userinfo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.com.location.repository.room.contant.ContentDB


@Database(entities = arrayOf(UserInfo::class),version = 2,exportSchema = false)
abstract class UserInfoDB: RoomDatabase() {
    abstract fun getUserInfoDao(): UserinfoDao

    companion object{
        @Volatile
        private var INIT: UserInfoDB? = null

        fun getInit(context: Context): UserInfoDB {
            synchronized(this) {
                var temp = INIT
                if (temp == null) {
                    temp = Room.databaseBuilder(context, UserInfoDB::class.java, "userinfo")
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