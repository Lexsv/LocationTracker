package ua.com.location.presentation.listandtrack

import androidx.lifecycle.Observer
import ua.com.location.data.StoreViewModel
import javax.inject.Inject

class ListAndTrackPresent @Inject constructor(var listAndTrackView: ListAndTrackView) :
    ListAndTrackPresentInterfas {

    override fun onStart() {
      StoreViewModel.getListTrak().observe(listAndTrackView.getLifecycleOwner(),
          Observer { t -> listAndTrackView.showRecyclerList(t) })
    }


}



