package ua.com.location

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import ua.com.location.di.mainActivity.DaggerMainComponent
import ua.com.location.di.mainActivity.MainPresenterModul
import ua.com.location.presentation.mainActivity.MainPresenterInterfas
import ua.com.location.presentation.mainActivity.MainView
import javax.inject.Inject


class MainActivity : AppCompatActivity(),MainView {

   @Inject
    lateinit var mainPresenterInterfas: MainPresenterInterfas


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addDaggerDepand()
        mainPresenterInterfas.onStart()
        setContentView(R.layout.activity_main)
    }


    override fun onStop() {
        super.onStop()
        mainPresenterInterfas.onSaveData()

    }

    override fun getLifecycleOwner(): LifecycleOwner = this

    private fun addDaggerDepand() {
        DaggerMainComponent.builder()
            .mainPresenterModul(MainPresenterModul(this))
            .build()
            .inject(this)
    }
    override fun actionMassege(key: String) {
        Toast.makeText(this, key, Toast.LENGTH_LONG).show()
    }

}
