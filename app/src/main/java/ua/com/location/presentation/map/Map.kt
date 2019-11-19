package ua.com.location.presentation.map


import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng



import kotlinx.android.synthetic.main.fragment_map.*
import ua.com.location.R

import ua.com.location.di.map.DaggerMapComponent
import ua.com.location.di.map.MapPresentationModul
import ua.com.location.presentation.dialog.MyDialog

import javax.inject.Inject


class Map : Fragment(), OnMapReadyCallback, MapView {

    private lateinit var mMap: GoogleMap
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var pairLocation: Pair<Double,Double>
    @Inject
    lateinit var mapPresentationInterfas: MapPresentationInterfas

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_map, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        addDaggerDepand()
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addButnListener()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        checkPermission()
        goToMyLocation()

    }


    //Internal Function

    private fun goToMyLocation() {
        mFusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                val mLocatoin = LatLng(location!!.latitude,location.longitude)
                pairLocation = location.latitude to location.longitude
                mapPresentationInterfas.onSaveMyLocation(pairLocation)
                mMap.setMyLocationEnabled(true)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLocatoin, 16f))
            }
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                context!!,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context!!,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ), 123
            )
        }
    }


    private fun addDaggerDepand() {
        DaggerMapComponent.builder()
            .mapPresentationModul(MapPresentationModul(this))
            .build()
            .inject(this)
    }

    private fun addButnListener() {
        map_float_button.setOnClickListener { _ ->
            onMapReady(mMap)
            MyDialog(pairLocation).show(childFragmentManager, "MAP")
        }

    }


}
