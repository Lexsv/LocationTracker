package ua.com.location.presentation.mainActivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.util.Log

class GPSBroadcastReceiver(): BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        val locationManager = p0!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Log.i("GPS","******* ON *********")

        }else{
            Log.i("GPS","******* OFF *********")
        }
    }


}