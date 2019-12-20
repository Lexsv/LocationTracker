package ua.com.location.models.mapModel

import android.graphics.Color
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import ua.com.location.models.BaseVM
import ua.com.location.models.mapModel.okhttp.GoogleMapDTO
import ua.com.location.util.getURLQuaryPath
import java.lang.Exception
import java.net.URL
import java.security.cert.Extension

class MapVM : BaseVM(), IMap {



    override fun getPoliline(from: LatLng, to: LatLng)=
        GlobalScope.async {
            val waypoint = getWayPoint(URL(getURLQuaryPath(from,to))).await()

            val lineoption = PolylineOptions()
            for (i in waypoint.indices) {
                lineoption.addAll(waypoint[i])
                lineoption.width(10f)
                lineoption.color(Color.BLUE)
                lineoption.geodesic(true)
            }

            return@async lineoption
        }


    fun getWayPoint(url: URL) =
     GlobalScope.async {   val client = OkHttpClient()
         val reqoest = Request.Builder().url(url).build()
         val response = client.newCall(reqoest).execute()
         val data = response.body!!.string()
         Log.i("***********data******", data)
         val result = ArrayList<List<LatLng>>()
         try {

             val resObj = Gson().fromJson(data,GoogleMapDTO::class.java)
             val path = ArrayList<LatLng>()

             for (i in 0..(resObj.routes[0].legs[0].steps.size - 1)) {
                 path.addAll(decodePolyline(resObj.routes[0].legs[0].steps[i].polyline.points))
             }
             result.add(path)
         }catch (e: Exception){
             e.printStackTrace()

         }
         return@async result
     }




    fun decodePolyline(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val latLng = LatLng((lat.toDouble() / 1E5), (lng.toDouble() / 1E5))
            poly.add(latLng)
        }
        return poly
    }
}