package ua.com.location.di.modul

import dagger.Module
import dagger.Provides
import ua.com.location.fragment.interfas.FragmentView
import ua.com.location.presentor.LoginPresent
import ua.com.location.presentor.interfas.LoginPresentInterfas

@Module
class LoginPresentModul (val fragmentView: FragmentView) {
    @Provides fun getRoudView(): LoginPresentInterfas = LoginPresent(fragmentView = fragmentView)
}