package ua.com.location.di.login

import dagger.Module
import dagger.Provides
import ua.com.location.presentation.login.LoginView
import ua.com.location.presentation.login.LoginPresent
import ua.com.location.presentation.login.LoginPresentInterfas

@Module
class LoginPresentModul (val loginView: LoginView) {
    @Provides fun getLoginPresentInterfas(): LoginPresentInterfas =
        LoginPresent(loginView = loginView)
}