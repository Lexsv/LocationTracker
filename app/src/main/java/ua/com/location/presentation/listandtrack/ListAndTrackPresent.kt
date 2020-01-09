package ua.com.location.presentation.listandtrack

import android.view.MenuItem
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationView
import ua.com.location.R
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.repository.room.content.Content
import ua.com.location.util.ProvidContext
import ua.com.location.util.isLocationOn
import ua.com.location.util.isNet

import javax.inject.Inject

class ListAndTrackPresent @Inject constructor(var listAndTrackView: ListAndTrackView) :
    ListAndTrackPresentInterface {

    override fun onStart() {
        LocalStoreVW.nowFragment = "COLECTION"
        LocalStoreVW.getContent().observe(listAndTrackView.getLifecycleOwner(), Observer {
           t -> listAndTrackView.showRecyclerList(t)
       })
        listAndTrackView.menuvisibility()
    }



    override fun onGoTo(key: String) {
        if (isLocationOn()) {
            listAndTrackView.gotoFragment(key)
        } else listAndTrackView.actionMassege("Включите GPS")
    }

    override fun remove(position: Int) {
        LocalStoreVW.lastremovedItom = position to LocalStoreVW.getContent().value!![position]
        listAndTrackView.showRestart()
        if (LocalStoreVW.getContent().value!!.size == 1){
            listAndTrackView.getVM().removeItom(LocalStoreVW.getContent().value!![0])
            LocalStoreVW.getContent().value = mutableListOf()
        }else{
            val list = LocalStoreVW.getContent().value as ArrayList
            listAndTrackView.getVM().removeItom(list.removeAt(position))
            LocalStoreVW.getContent().value = list.toList()
        }

    }

    override fun creatPath(position: Int) {
        if (isLocationOn()) {
            LocalStoreVW.creatWay = true
            if (LocalStoreVW.getContent().value!!.size == 1){
                LocalStoreVW.workingItom = LocalStoreVW.getContent().value!![0]
            }else{
                LocalStoreVW.workingItom =  (LocalStoreVW.getContent().value as ArrayList)[position]
            }
            listAndTrackView.rout("MAP")
        }else {
            listAndTrackView.showRecyclerList(LocalStoreVW.getContent().value!!)
            listAndTrackView.actionMassege("Включите GPS")

        }
    }

    override fun nowUpdat(item: Content) {
        LocalStoreVW.workingItom = item
    }

    override fun onReestablishItom() {
        listAndTrackView.getVM().reestablishItom()
    }

}



