package ua.com.location.presentation.mainActivity

import androidx.lifecycle.Observer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ua.com.location.data.StoreViewModel
import ua.com.location.data.LocatoinTrak
import ua.com.location.util.ProvidContext
import ua.com.location.data.room.DataBaseObjact
import ua.com.location.models.roommodel.RoomData
import ua.com.location.models.roommodel.RoomWorkInterfas
import ua.com.location.util.getConnectivityNet
import javax.inject.Inject

class MainPresenter @Inject constructor(var mainView: MainView): MainPresenterInterfas{
    val roomWorkInterfas: RoomWorkInterfas
    init {
        roomWorkInterfas = RoomData()
    }

    override fun onStart() {
        onInternetConnection ()
        getUser()
    }

    fun getUser(){
        val result = GlobalScope.async {
            roomWorkInterfas.getAllUser()}
        GlobalScope.launch {
            val mResult = result.await()
            if ( !mResult.isEmpty()){
                if (mResult[0].listLocatoinTrak != null) {
                    StoreViewModel.getUserid().postValue(mResult[0].id)
                    StoreViewModel.getListTrak().postValue(mResult[0].listLocatoinTrak)
                }
            }
        }
    }

    override fun onSaveData() {
        var idUser:String = ""
        var listDescription : List<LocatoinTrak>? = null
        StoreViewModel.getListTrak().observe(mainView.getLifecycleOwner(),
            Observer {list -> listDescription = list })
        StoreViewModel.getUserid().observe(mainView.getLifecycleOwner(),
            Observer {id -> idUser = id })

      GlobalScope.launch {
          RoomData().update(DataBaseObjact(idUser,listDescription))
      }
    }

    fun onInternetConnection (){
        if (getConnectivityNet(ProvidContext.getContext())){
            mainView.actionMassege("Интернет есть")
        }else{
            mainView.actionMassege("Интернета нет")
        }
    }
}