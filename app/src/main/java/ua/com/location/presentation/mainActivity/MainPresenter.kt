package ua.com.location.presentation.mainActivity


import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.com.location.data.StoreViewModel
import ua.com.location.data.room.DataBaseObjact
import ua.com.location.util.ProvidContext
import ua.com.location.util.checkPermissions

import ua.com.location.util.getConnectivityNet
import javax.inject.Inject

class MainPresenter @Inject constructor(var mainView: MainView): MainPresenterInterfas{

    override fun onStart(context: Context) {
        checkPermissions(context)
        onInternetConnection()
    }

    override fun onSaveData() {

            val mVM = mainView.getVM()
            mVM.deleteAll()
            mVM.insert(DataBaseObjact(StoreViewModel.getUserid().value!!,StoreViewModel.getListTrak().value!!))


    }

    fun onInternetConnection (){
        if (!getConnectivityNet(ProvidContext.getContext())) {
            mainView.actionMassege("Интернета нет")}
    }
}