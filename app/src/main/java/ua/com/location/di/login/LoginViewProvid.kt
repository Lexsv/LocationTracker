package ua.com.location.di.login

import dagger.Module
import ua.com.location.presentation.login.LoginView


@Module
class LoginViewProvid(var loginView: LoginView) {
}