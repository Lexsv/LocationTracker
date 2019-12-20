package ua.com.location.presentation.listandtrack

import androidx.lifecycle.Observer
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.repository.room.content.Content
import ua.com.location.util.ProvidContext
import ua.com.location.util.isNet

import javax.inject.Inject

class ListAndTrackPresent @Inject constructor(var listAndTrackView: ListAndTrackView) :
    ListAndTrackPresentInterface {

    override fun onStart() {
        LocalStoreVW.getContent().observe(listAndTrackView.getLifecycleOwner(), Observer {
           t -> listAndTrackView.showRecyclerList(t)
       })
        LocalStoreVW.getUserid().observe(listAndTrackView.getLifecycleOwner(), Observer {
            listAndTrackView.welcome(it.name)
        })

    }

    override fun onExit() {
        if(isNet(ProvidContext.getContext())){
        listAndTrackView.getVM().exit()
        listAndTrackView.gotoFragment("LOGIN")
        }else{
            listAndTrackView.actionMassege("Для выхода из аккаунта \n подключите интернет")
        }
    }

    override fun onGoTo(key: String) {
        listAndTrackView.gotoFragment(key)
    }

    override fun remove(position: Int) {
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
        LocalStoreVW.creatWay = true
        if (LocalStoreVW.getContent().value!!.size == 1){
            LocalStoreVW.workingItom = LocalStoreVW.getContent().value!![0]
        }else{
            LocalStoreVW.workingItom =  (LocalStoreVW.getContent().value as ArrayList)[position]
        }
        listAndTrackView.rout("MAP")
    }

    override fun nowUpdat(item: Content) {
        LocalStoreVW.workingItom = item
    }
}



