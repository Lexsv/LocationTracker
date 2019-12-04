package ua.com.location.presentation.mainActivity


import android.content.Context
import ua.com.location.util.ProvidContext
import ua.com.location.util.checkPermissions

import ua.com.location.util.isNet
import javax.inject.Inject

class MainPresenter @Inject constructor(var mainView: MainView): MainPresenterInterfas{

    override fun onStart(context: Context) {
        checkPermissions(context)
        mainView.getVM().onStartApp()
        onInternetConnection()
    }

    override fun onSaveData() {
        mainView.getVM().saveToFire()
    }

    override fun onDestroy() {

    }

    fun onInternetConnection (){
        if (!isNet(ProvidContext.getContext())) {
            mainView.actionMassege("Интернета нет")}
    }
}