package ua.com.location.data


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StoreViewModel : ViewModel() {
    companion object {
        private var  userId: MutableLiveData<String> = MutableLiveData()
        fun getUserid():  MutableLiveData<String> = userId
        private var storesListLocatoinTrak: MutableLiveData<List<LocatoinTrak>> = MutableLiveData()
        fun getListTrak(): MutableLiveData<List<LocatoinTrak>> = storesListLocatoinTrak
    }

}