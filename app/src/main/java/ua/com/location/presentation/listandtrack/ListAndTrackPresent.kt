package ua.com.location.presentation.listandtrack

import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.com.location.data.StoreViewModel
import ua.com.location.data.room.AppDatabase

import ua.com.location.util.ProvidContext
import javax.inject.Inject

class ListAndTrackPresent @Inject constructor(var listAndTrackView: ListAndTrackView) :
    ListAndTrackPresentInterface {

    override fun onStart() {
        val list = listAndTrackView.getVM().allUser.value
        if (list != null){
            StoreViewModel.getListTrak().postValue(list[0].listLocatoinTrak)
            StoreViewModel.getUserid().postValue(list[0].id)
        }

      listAndTrackView.showRecyclerList(StoreViewModel.getListTrak().value!!)
    }

    override fun onExit() {
        listAndTrackView.getVM().deleteAll()
        listAndTrackView.gotoFragment("LOGIN")
    }

    override fun onGoTo(key: String) {
        listAndTrackView.gotoFragment(key)
    }


}



