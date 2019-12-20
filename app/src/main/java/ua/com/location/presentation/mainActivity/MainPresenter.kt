package ua.com.location.presentation.mainActivity


import android.content.Context
import android.content.IntentFilter
import ua.com.location.util.ProvidContext
import ua.com.location.util.checkPermissions

import ua.com.location.util.isNet
import javax.inject.Inject

class MainPresenter @Inject constructor(var mainView: MainView): MainPresenterInterfas{

    override fun onStart(context: Context) {
        checkPermissions(context)
        context.registerReceiver(GPSBroadcastReceiver(), IntentFilter("android.location.PROVIDERS_CHANGED"))
        isInternetConnection()
    }

    override fun onSaveData() {
        mainView.getVM().saveDataUser()
    }


    fun isInternetConnection (){
        if (!isNet(ProvidContext.getContext())) {
            mainView.actionMassege("Интернета нет")}
    }
}