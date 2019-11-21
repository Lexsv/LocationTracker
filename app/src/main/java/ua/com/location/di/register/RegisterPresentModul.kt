package ua.com.location.di.register

import dagger.Module
import dagger.Provides
import ua.com.location.presentation.login.LoginView
import ua.com.location.presentation.register.RegisterPresent
import ua.com.location.presentation.register.RegisterPresentInterfas
import ua.com.location.presentation.register.RegisterView


@Module
class RegisterPresentModul (val registerView: RegisterView) {
    @Provides fun getRoudView(): RegisterPresentInterfas =
        RegisterPresent(registerView)
}