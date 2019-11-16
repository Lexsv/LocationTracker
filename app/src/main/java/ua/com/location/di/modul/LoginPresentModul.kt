package ua.com.location.di.modul

import dagger.Module
import dagger.Provides
import ua.com.location.presentation.login.LoginView
import ua.com.location.presentation.login.LoginPresent
import ua.com.location.presentation.login.LoginPresentInterfas

@Module
class LoginPresentModul (val loginView: LoginView) {
    @Provides fun getRoudView(): LoginPresentInterfas =
        LoginPresent(loginView = loginView)
}