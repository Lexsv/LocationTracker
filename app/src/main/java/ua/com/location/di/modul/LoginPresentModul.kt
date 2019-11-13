package ua.com.location.di.modul

import dagger.Module
import dagger.Provides
import ua.com.location.presentor.login.LoginView
import ua.com.location.presentor.login.LoginPresent
import ua.com.location.presentor.login.LoginPresentInterfas

@Module
class LoginPresentModul (val loginView: LoginView) {
    @Provides fun getRoudView(): LoginPresentInterfas =
        LoginPresent(loginView = loginView)
}