package ua.com.location.presentation.map

interface MapPresentationInterfas {
    fun onSaveMyLocation(pairLocation: Pair<Double,Double>)
    fun onStart()
}