package ua.com.location.presentor.login


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room


import kotlinx.android.synthetic.main.fragment_login.*

import ua.com.location.R
import ua.com.location.data.room.AppDatabase
import ua.com.location.di.component.DaggerLoginComponent

import ua.com.location.di.modul.LoginPresentModul

import javax.inject.Inject


class Login : Fragment(), LoginView {
    val TAGREG = "RAGISTER"


    val TAGCOL = "COLECTION"
    @Inject
    lateinit var loginPresentInterfas: LoginPresentInterfas
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addDaggerDepand()
        addButnListener()


    }


   fun addDaggerDepand(){
       DaggerLoginComponent.builder().loginPresentModul(LoginPresentModul(this))
           .build()
           .inject(this)
   }






    fun addButnListener(){
        authentication_bt_entry.setOnClickListener{_ ->
            loginPresentInterfas.login(authentication_email.text.toString(), authentication_password.text.toString())
        }
        authentication_bt_reg.setOnClickListener { _ ->
            loginPresentInterfas.startSckreen(TAGREG)

        }

    }

    override fun actionMassege(key: String) {
        Toast.makeText(context, key, Toast.LENGTH_LONG).show()
    }

    override fun rout(key: String) {
        when(key) {
            TAGREG -> NavHostFragment.findNavController(this).navigate(R.id.register)
            TAGCOL -> NavHostFragment.findNavController(this).navigate(R.id.listAndTrack)
        }
    }

    override fun getAppDataBase(): AppDatabase = Room.databaseBuilder(context!!,AppDatabase::class.java,"user").build()

}

