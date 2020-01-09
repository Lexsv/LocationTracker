package ua.com.location.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor


fun validEnterDataRegister(
    name: String,
    email: String,
    password: String,
    repitPassword: String
): Pair<Boolean, ActionMessage> {

    return when {
        name.isEmpty()
                && email.isEmpty()
                && password.isEmpty()
                && repitPassword.isEmpty() -> false to ActionMessage.ERROR_EMPTY_DATA
        password != repitPassword -> false to ActionMessage.ERROR_NO_COINCIDENCE
        password.length < 6 -> false to ActionMessage.ERROR_LENGTH
        else -> true to ActionMessage.SUCCESSFUL
    }

}

fun validEnterDataAut(email: String, password: String): Pair<Boolean, ActionMessage> {
    return when {
        email.isEmpty()
                && password.isEmpty() -> false to ActionMessage.ERROR_EMPTY_DATA
        password.length < 6 -> false to ActionMessage.ERROR_LENGTH
        else -> true to ActionMessage.SUCCESSFUL
    }

}

fun isNet(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkRequest = cm.activeNetwork
    val capabilities = cm.getNetworkCapabilities(networkRequest)
    return capabilities != null

}


fun checkPermissions(context: Context) {
    if (ActivityCompat.checkSelfPermission(
            context
            ,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        requestPermissions(
            context as Activity,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ), 123
        )
    }
}


fun myLocation(): Task<Location> {
    val locationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(ProvidContext.getContext())
    return locationClient.lastLocation

}

fun getURLQuaryPath(from: LatLng, to: LatLng): String {
    val origin = "origin=" + from.latitude + "," + from.longitude
    val dest = "destination=" + to.latitude + "," + to.longitude
    val sensor = "sensor=false"
    val params = "$origin&$dest&$sensor"
    return "https://maps.googleapis.com/maps/api/directions/json?$params&key=AIzaSyAFxSY7hczw7yUQjR96zh-N825aesUxe6I"
}



fun isLocationOn(): Boolean{
    val locationManager = ProvidContext.getContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

}




