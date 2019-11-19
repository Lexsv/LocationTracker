package ua.com.location.di.map

import dagger.Component
import ua.com.location.presentation.map.Map

@Component(modules = arrayOf(MapPresentationModul::class,MapViewProvid::class))
interface MapComponent {
    fun inject(map: Map)
}