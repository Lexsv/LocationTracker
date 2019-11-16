package ua.com.location.di.provid

import dagger.Module
import ua.com.location.presentation.login.LoginView


@Module
class RounViewProvid(val loginView: LoginView) {
}