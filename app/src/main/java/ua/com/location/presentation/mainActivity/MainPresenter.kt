package ua.com.location.presentation.mainActivity


import android.content.Context
import android.content.IntentFilter
import androidx.work.*
import ua.com.location.repository.data.MyLocation
import ua.com.location.repository.firebase.SetDataFonWork
import ua.com.location.util.ProvidContext
import ua.com.location.util.checkPermissions

import ua.com.location.util.isNet
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainPresenter @Inject constructor(var mainView: MainView): MainPresenterInterfas{

    override fun onStart(context: Context) {
        checkPermissions(context)
        context.registerReceiver(GPSBroadcastReceiver(), IntentFilter("android.location.PROVIDERS_CHANGED"))
        isInternetConnection()
        sentMyLocation()
    }

    private fun sentMyLocation() {
        val workManager = WorkManager.getInstance(ProvidContext.getContext())
        val myWorkRequest = PeriodicWorkRequest.Builder(MyLocation::class.java,5,TimeUnit.SECONDS)
            .build()
        workManager.enqueueUniquePeriodicWork("local",ExistingPeriodicWorkPolicy.KEEP, myWorkRequest)

    }

    override fun onSaveData() {
        mainView.getVM().saveDataUser()
    }


    fun isInternetConnection (){
        if (!isNet(ProvidContext.getContext())) {
            mainView.actionMassege("Интернета нет")}
    }
}