package ua.com.location.presentation.map


import android.graphics.Color

import android.location.Location

import android.os.Bundle
import android.service.voice.VoiceInteractionSession
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import kotlinx.android.synthetic.main.fragment_map.*
import org.json.JSONObject
import ua.com.location.MainActivity
import ua.com.location.R
import ua.com.location.di.map.DaggerMapComponent
import ua.com.location.di.map.MapPresentationModul
import ua.com.location.models.loginModel.LoginVM
import ua.com.location.models.mapModel.IMap
import ua.com.location.models.mapModel.MapVM
import ua.com.location.presentation.dialog.MyDialog
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.util.myLocation


import javax.inject.Inject


class Map : Fragment(), OnMapReadyCallback, MapView {

    private lateinit var mMap: GoogleMap
    private lateinit var pairLocation: Pair<Double,Double>
    @Inject
    lateinit var mapPresentationInterfas: MapPresentationInterfas

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.MAINTAG = "MAP"
        val rootView = inflater.inflate(R.layout.fragment_map, container, false)
        val mapFragment= childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        addDaggerDepand()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addButnListener()
        mapPresentationInterfas.onStart()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        goToMyLocation()
    }

    //Internal Function
    private fun goToMyLocation() {
       myLocation().addOnSuccessListener { location : Location? ->
                val mLocatoin = LatLng(location!!.latitude,location.longitude)
                pairLocation = location.latitude to location.longitude
                mapPresentationInterfas.onSaveMyLocation(pairLocation)
                mMap.setMyLocationEnabled(true)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLocatoin, 16f))
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
           myLocation().addOnSuccessListener {
               mMap.clear()
               onMapReady(mMap)
               MyDialog(it.latitude to it.longitude).show(childFragmentManager, "MAP")
           }

        }
    }

   override fun creatWay(polylineOptions: PolylineOptions){
       mMap.addMarker(MarkerOptions()
           .position(LatLng(LocalStoreVW.workingItom!!.latitude,LocalStoreVW.workingItom!!.longitude))
           .title(LocalStoreVW.workingItom!!.title)     )
       mMap.addPolyline(polylineOptions)
    }

    override fun getVM(): IMap = ViewModelProviders.of(this).get(MapVM::class.java)
}
