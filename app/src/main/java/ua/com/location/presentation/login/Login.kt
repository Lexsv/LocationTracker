package ua.com.location.presentation.login


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders


import androidx.navigation.fragment.NavHostFragment


import kotlinx.android.synthetic.main.fragment_login.*
import ua.com.location.MainActivity

import ua.com.location.R
import ua.com.location.di.login.DaggerLoginComponent
import ua.com.location.di.login.LoginPresentModul
import ua.com.location.models.loginModel.ILoginVM
import ua.com.location.models.loginModel.LoginVM
import ua.com.location.presentation.BaseFragment


import javax.inject.Inject


class Login : BaseFragment(), LoginView {

    @Inject
    lateinit var loginPresentInterfas: LoginPresentInterfas

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addDaggerDepand()
        return inflater.inflate(R.layout.fragment_login, container, false)}



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginPresentInterfas.onStart()
        addButnListener()
    }


   fun addDaggerDepand(){
       DaggerLoginComponent.builder()
           .loginPresentModul(LoginPresentModul(this))
           .build()
           .inject(this)
   }

    override fun getVM(): ILoginVM = ViewModelProviders.of(this).get(LoginVM::class.java)


    fun addButnListener(){
        authentication_bt_entry.setOnClickListener{_ ->
            loginPresentInterfas.onLogin(authentication_email.text.toString(), authentication_password.text.toString())
        }
        authentication_bt_reg.setOnClickListener { _ ->
            loginPresentInterfas.onStartSckreen(TAGREG)

        }

    }

    override fun actionMassege(key: String) {
        Toast.makeText(context, key, Toast.LENGTH_LONG).show()
    }

    override fun getLifecycleOwner(): LifecycleOwner = this

    override fun rout(key: String) {
        when(key) {
            TAGREG -> NavHostFragment.findNavController(this).navigate(R.id.register)
            TAGCOL -> NavHostFragment.findNavController(this).navigate(R.id.listAndTrack)
            TAGMAP -> NavHostFragment.findNavController(this).navigate(R.id.map)

        }
    }

}

