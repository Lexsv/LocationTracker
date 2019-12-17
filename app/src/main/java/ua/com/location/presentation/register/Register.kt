package ua.com.location.presentation.register


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_register.*
import ua.com.location.MainActivity
import ua.com.location.R
import ua.com.location.di.register.DaggerRegisterComponent


import ua.com.location.di.register.RegisterPresentModul
import ua.com.location.models.registerModel.IRegister
import ua.com.location.models.registerModel.RegisterVM
import javax.inject.Inject


class Register : Fragment(), RegisterView {
    val TAGMAP = "MAP"
    @Inject
    lateinit var registerPresentInterfas: RegisterPresentInterfas

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addDaggerDepand()
        MainActivity.MAINTAG = "RAGISTER"
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addButnListenar()
    }
    fun  addButnListenar(){
        register_bt_reg.setOnClickListener{ _ ->
            registerPresentInterfas.registNew(
                register_user_name.text.toString(),
                register_email.text.toString(),
                register_password.text.toString(),
                register_repeat_password.text.toString()
            )

        }
    }


    fun  addDaggerDepand(){
        DaggerRegisterComponent.builder()
            .registerPresentModul(RegisterPresentModul(this))
            .build()
            .inject(this)
    }

    override fun rout(key: String) {
            when(key) {
               TAGMAP -> NavHostFragment.findNavController(this).navigate(R.id.map)
            }
    }


    override fun actionMassege(key: String) {
        Toast.makeText(context, key, Toast.LENGTH_LONG).show()
    }
    override fun getVM(): IRegister =  ViewModelProviders.of(this).get(RegisterVM::class.java)


}
