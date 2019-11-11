package ua.com.location.di.modul

import dagger.Module
import dagger.Provides
import ua.com.location.fragment.interfas.FragmentView
import ua.com.location.presentor.RegisterPresent
import ua.com.location.presentor.interfas.RegisterPresentInterfas


@Module
class RegisterPresentModul (val fragmentView: FragmentView) {
    @Provides fun getRoudView(): RegisterPresentInterfas = RegisterPresent(fragmentView = fragmentView)
}