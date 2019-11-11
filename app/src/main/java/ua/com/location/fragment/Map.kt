package ua.com.location.fragment

import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import kotlinx.android.synthetic.main.fragment_map.*
import ua.com.location.MainActivity
import ua.com.location.R

class Map : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_map, container, false)

        var mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)

        return rootView
    }



    override fun onMapReady(googleMap: GoogleMap?) {

        mMap = googleMap!!
        val mLocatoin = LatLng(10.2  , 15.5)
        mMap.addMarker(MarkerOptions().position(mLocatoin).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLocatoin,8f))
    }


}
