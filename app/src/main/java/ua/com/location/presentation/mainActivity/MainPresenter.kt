package ua.com.location.presentation.mainActivity


import android.content.Context
import android.content.IntentFilter
import android.location.LocationManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.work.*
import ua.com.location.R
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.repository.data.MyLocation
import ua.com.location.repository.firebase.SetDataFonWork
import ua.com.location.util.ProvidContext
import ua.com.location.util.checkPermissions
import ua.com.location.util.isLocationOn

import ua.com.location.util.isNet
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainPresenter @Inject constructor(var mainView: MainView) : MainPresenterInterfas {

    override fun onStart(context: Context) {
        checkPermissions(context)
        context.registerReceiver(GPSBroadcastReceiver(), IntentFilter("android.location.PROVIDERS_CHANGED"))
        isInternetConnection()
        sentMyLocation()
        LocalStoreVW.getUserid().observe(mainView.getLifecycleOwner(), Observer {
           if (it != null){ mainView.nameUser(it)}
        })
    }

    private fun sentMyLocation() {
        val workManager = WorkManager.getInstance(ProvidContext.getContext())
        val myWorkRequest = PeriodicWorkRequest.Builder(MyLocation::class.java, 5, TimeUnit.SECONDS)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "local",
            ExistingPeriodicWorkPolicy.KEEP,
            myWorkRequest
        )

    }

    override fun onClick(key: String) {
        if (key == "MAP"){
           if (isLocationOn()){
               mainView.goTo(key)
           }else mainView.actionMassege("Включите GPS")
        }else  mainView.goTo(key)

    }

    fun isInternetConnection() {
        if (!isNet(ProvidContext.getContext())) {
            mainView.actionMassege("Интернета нет")
        }
    }


    override fun onExit() {
        if (isNet(ProvidContext.getContext())) {
            mainView.getVM().exit()
            mainView.goTo("LOGIN")
            LocalStoreVW.nowFragment = "LOGIN"
            mainView.unMenuvisibility()
        } else {
            mainView.actionMassege("Для выхода из аккаунта \n подключите интернет")
        }
    }


}