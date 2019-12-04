package ua.com.location.presentation.listandtrack

import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import ua.com.location.R
import ua.com.location.data.LocalStoreVW

import javax.inject.Inject

class ListAndTrackPresent @Inject constructor(var listAndTrackView: ListAndTrackView) :
    ListAndTrackPresentInterface {

    override fun onStart() {

        LocalStoreVW.getContent().observe(listAndTrackView.getLifecycleOwner(), Observer {
           t -> listAndTrackView.showRecyclerList(t)
       })
        LocalStoreVW.getUserid().observe(listAndTrackView.getLifecycleOwner(), Observer {
            listAndTrackView.getVM().getDataUserFromFir(it.id)
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



