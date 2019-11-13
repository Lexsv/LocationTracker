package ua.com.location.di.modul

import dagger.Module
import dagger.Provides
import ua.com.location.presentor.login.LoginView
import ua.com.location.presentor.register.RegisterPresent
import ua.com.location.presentor.register.RegisterPresentInterfas


@Module
class RegisterPresentModul (val loginView: LoginView) {
    @Provides fun getRoudView(): RegisterPresentInterfas =
        RegisterPresent(loginView = loginView)
}