package ua.com.location.presentation.listandtrack

import androidx.lifecycle.Observer
import ua.com.location.repository.data.LocalStoreVW

import javax.inject.Inject

class ListAndTrackPresent @Inject constructor(var listAndTrackView: ListAndTrackView) :
    ListAndTrackPresentInterface {

    override fun onStart() {
        LocalStoreVW.getContent().observe(listAndTrackView.getLifecycleOwner(), Observer {
           t -> listAndTrackView.showRecyclerList(t)
       })

    }

    override fun onExit() {
        listAndTrackView.getVM().exit()
        listAndTrackView.gotoFragment("LOGIN")
    }

    override fun onGoTo(key: String) {
        listAndTrackView.gotoFragment(key)
    }


}



