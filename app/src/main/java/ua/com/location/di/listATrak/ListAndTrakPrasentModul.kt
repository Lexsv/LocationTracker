package ua.com.location.di.listATrak

import dagger.Module
import dagger.Provides
import ua.com.location.presentation.listandtrack.ListAndTrackPresent
import ua.com.location.presentation.listandtrack.ListAndTrackPresentInterface
import ua.com.location.presentation.listandtrack.ListAndTrackView


@Module
class ListAndTrakPrasentModul(var listAndTrackView: ListAndTrackView) {
    @Provides fun getListAndTratPresenterInterfas(): ListAndTrackPresentInterface = ListAndTrackPresent(listAndTrackView)
}