package ua.com.location.presentation.map

import android.content.pm.PackageManager

import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import ua.com.location.R
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.com.location.data.ProvidContext
import ua.com.location.data.room.AppDatabase
import ua.com.location.presentation.dialog.MyDialog


class Map : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_map, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        map_float_button.setOnClickListener {_ ->
            MyDialog().show(childFragmentManager, "111")

        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {

        mMap = googleMap!!

        if (ActivityCompat.checkSelfPermission(context!!,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context!!,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),123 )
        }
        mFusedLocationProviderClient
            .lastLocation
            .addOnCompleteListener { location ->
                if (location.isSuccessful){
                    val mLocatoin = LatLng(location.result!!.latitude  , location.result!!.longitude)
                    mMap.addMarker(MarkerOptions().position(mLocatoin).title("Marker in ..."))
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLocatoin,16f))

                }else {
                    location.exception
                }

        }


    }


}
