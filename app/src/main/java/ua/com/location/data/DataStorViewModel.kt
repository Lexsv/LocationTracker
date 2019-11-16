package ua.com.location.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataStorViewModel : ViewModel() {

    companion object {
        private val stores: MutableLiveData<List<LocatoinTrak>>? = null
        fun getListTrak()= stores
    }
}