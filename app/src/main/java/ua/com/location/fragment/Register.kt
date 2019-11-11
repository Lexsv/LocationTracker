package ua.com.location.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_register.*
import ua.com.location.R
import ua.com.location.di.component.DaggerRegisterComponent

import ua.com.location.di.modul.LoginPresentModul
import ua.com.location.di.modul.RegisterPresentModul
import ua.com.location.fragment.interfas.FragmentView
import ua.com.location.fragment.interfas.RegisterInterfas
import ua.com.location.presentor.interfas.RegisterPresentInterfas
import javax.inject.Inject


class Register : Fragment(), FragmentView {
    val TAGMAP = "MAP"
    @Inject
    lateinit var registerPresentInterfas: RegisterPresentInterfas

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DaggerRegisterComponent.builder().registerPresentModul(RegisterPresentModul(this))
            .build()
            .inject(this)
        register_bt_reg.setOnClickListener{ _ ->
            registerPresentInterfas.registNew(
                register_user_name.text.toString(),
                register_email.text.toString(),
                register_password.text.toString(),
                register_repeat_password.text.toString()
            )
        }

    }


    override fun rout(key: String) {

            when(key) {
               TAGMAP -> NavHostFragment.findNavController(this).navigate(R.id.map)
            }
    }

    override fun errorMassege(key: String) {
        Toast.makeText(context, key, Toast.LENGTH_LONG).show()
    }


}
