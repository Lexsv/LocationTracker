package ua.com.location.repository.data


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import ua.com.location.repository.room.content.Content
import ua.com.location.repository.room.userinfo.UserInfo

class LocalStoreVW : ViewModel() {
    companion object {

        @Volatile
        private var lastLocation : MutableLiveData<Pair<Double,Double>> = MutableLiveData()
        fun  getLastLocation(): MutableLiveData<Pair<Double,Double>> = lastLocation

        @Volatile
        private var  userInfo: MutableLiveData<UserInfo> = MutableLiveData()
        fun  getUserid():  MutableLiveData<UserInfo> = userInfo

        @Volatile
        private var storeContent: MutableLiveData<List<Content>> = MutableLiveData()
        fun  getContent(): MutableLiveData<List<Content>> = storeContent

        @Volatile
        var lastID : Int = -1

        @Volatile
        var nowUpDataItom: Content? = null



        fun clearData(){
            lastLocation = MutableLiveData()
            userInfo = MutableLiveData()
            storeContent = MutableLiveData()
            lastID = -1
        }

    }


}