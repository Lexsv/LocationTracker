package ua.com.location.di.mainActivity

import dagger.Component
import ua.com.location.MainActivity

@Component(modules = arrayOf(MainPresenterModul::class,MainViewProvid::class))
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}