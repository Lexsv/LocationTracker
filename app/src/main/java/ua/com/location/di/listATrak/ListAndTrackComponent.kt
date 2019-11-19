package ua.com.location.di.listATrak

import dagger.Component
import ua.com.location.presentation.listandtrack.ListAndTrack

@Component(modules = arrayOf(ListAndTrackViewProvid::class,ListAndTrakPrasentModul::class))
interface ListAndTrackComponent {
    fun inject(listAndTrak: ListAndTrack)
}