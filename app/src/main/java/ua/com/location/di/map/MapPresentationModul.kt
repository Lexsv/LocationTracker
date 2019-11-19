package ua.com.location.di.map

import dagger.Module
import dagger.Provides
import ua.com.location.presentation.map.MapPrasentation
import ua.com.location.presentation.map.MapPresentationInterfas
import ua.com.location.presentation.map.MapView

@Module
class MapPresentationModul(val mapView: MapView) {
    @Provides fun getMapPresetationIntefas():MapPresentationInterfas = MapPrasentation(mapView = mapView)
}