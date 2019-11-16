package ua.com.location.di.modul

import dagger.Module
import dagger.Provides
import ua.com.location.presentation.login.LoginView
import ua.com.location.presentation.register.RegisterPresent
import ua.com.location.presentation.register.RegisterPresentInterfas


@Module
class RegisterPresentModul (val loginView: LoginView) {
    @Provides fun getRoudView(): RegisterPresentInterfas =
        RegisterPresent(loginView = loginView)
}