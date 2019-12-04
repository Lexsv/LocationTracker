package ua.com.location.data


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import ua.com.location.models.repository.room.contant.Content
import ua.com.location.models.repository.room.userinfo.UserInfo

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
        fun setValContent(list: List<Content>){
            storeContent.value = list}

        fun clearData(){
            lastLocation = MutableLiveData()
            userInfo = MutableLiveData()
            storeContent = MutableLiveData()
        }

    }


}