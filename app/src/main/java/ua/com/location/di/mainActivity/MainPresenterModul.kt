package ua.com.location.di.mainActivity

import dagger.Module
import dagger.Provides
import ua.com.location.presentation.mainActivity.MainPresenter
import ua.com.location.presentation.mainActivity.MainPresenterInterfas
import ua.com.location.presentation.mainActivity.MainView

@Module
class MainPresenterModul(var mainView: MainView) {
    @Provides
    fun getMainPresenterInterfas(): MainPresenterInterfas = MainPresenter(mainView)
}