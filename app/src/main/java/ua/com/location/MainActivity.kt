package ua.com.location

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import ua.com.location.di.mainActivity.DaggerMainComponent
import ua.com.location.di.mainActivity.MainPresenterModul
import ua.com.location.models.DistributorDataVM
import ua.com.location.models.IDistributorData
import ua.com.location.presentation.mainActivity.MainPresenterInterfas
import ua.com.location.presentation.mainActivity.MainView
import javax.inject.Inject


class MainActivity : AppCompatActivity(),MainView {

   @Inject
    lateinit var mainPresenterInterfas: MainPresenterInterfas
    companion object{
         var MAINTAG: String = "LOGIN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addDaggerDepand()
        mainPresenterInterfas.onStart(this)
        setContentView(R.layout.activity_main)
    }


    override fun getLifecycleOwner(): LifecycleOwner = this
    override fun getVM():IDistributorData = ViewModelProviders.of(this).get(DistributorDataVM::class.java)

    private fun addDaggerDepand() {
        DaggerMainComponent.builder()
            .mainPresenterModul(MainPresenterModul(this))
            .build()
            .inject(this)
    }
    override fun actionMassege(key: String) {
        Toast.makeText(this, key, Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        mainPresenterInterfas.onSaveData()
    }
    override fun onBackPressed() {
        if(MAINTAG != "COLECTION") {super.onBackPressed()}
    }



}
