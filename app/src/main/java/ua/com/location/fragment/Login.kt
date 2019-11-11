package ua.com.location.fragment


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.navigation.fragment.NavHostFragment


import kotlinx.android.synthetic.main.fragment_login.*

import ua.com.location.R
import ua.com.location.di.component.DaggerLoginComponent

import ua.com.location.di.modul.LoginPresentModul
import ua.com.location.fragment.interfas.FragmentView
import ua.com.location.presentor.interfas.LoginPresentInterfas

import javax.inject.Inject


class Login : Fragment(), FragmentView {


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


    override fun errorMassege(key: String) {
        Toast.makeText(context, key, Toast.LENGTH_LONG).show()
    }





    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DaggerLoginComponent.builder().loginPresentModul(LoginPresentModul(this))
            .build()
            .inject(this)


        authentication_bt_entry.setOnClickListener{_ ->
            loginPresentInterfas.login(authentication_email.text.toString(), authentication_password.text.toString())
        }
        authentication_bt_reg.setOnClickListener { _ ->
            loginPresentInterfas.startSckreen(TAGREG)

        }

    }

    override fun rout(key: String) {
        when(key) {
            TAGREG -> NavHostFragment.findNavController(this).navigate(R.id.register)
            TAGCOL -> NavHostFragment.findNavController(this).navigate(R.id.listAndTrack)
        }
    }

}

