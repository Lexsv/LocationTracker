package ua.com.location.models.repository.room.userinfo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = arrayOf(UserInfo::class),version = 1,exportSchema = false)
abstract class UserInfoDB: RoomDatabase() {
    abstract fun getUserInfoDao(): UserinfoDao

    companion object{
        @Volatile
        private var INIT: UserInfoDB? = null

        fun getInit(context: Context): UserInfoDB {
            synchronized(this) {
                var temp = INIT
                if (temp == null) {
                    temp = Room.databaseBuilder(context, UserInfoDB::class.java, "userinfo").build()
                    INIT = temp
                }
                return temp
            }
        }
    }

}