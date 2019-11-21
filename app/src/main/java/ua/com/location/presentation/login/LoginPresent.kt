package ua.com.location.presentation.login

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStoreOwner
import com.google.android.gms.dynamic.IFragmentWrapper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.com.location.data.StoreViewModel
import ua.com.location.data.room.DataBaseObjact
import ua.com.location.models.PostVMInrefas
import ua.com.location.models.PostViewModel


import ua.com.location.util.ActionMessage
import ua.com.location.util.ProvidContext
import ua.com.location.util.getConnectivityNet
import ua.com.location.util.validEnterDataAut
import java.lang.Appendable
import javax.inject.Inject

class LoginPresent @Inject constructor(var loginView: LoginView,var postVMInrefas: PostVMInrefas) :
    LoginPresentInterfas {

    lateinit var  mPostViewModel : PostViewModel


    val TAGCOL = "COLECTION"
    val TAGMAP = "MAP"

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onLogin(email: String, password: String) {
        if (getConnectivityNet(ProvidContext.getContext())) {
            val result = validEnterDataAut(email=email,password = password)
            if (result.first) {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                                if (mPostViewModel.allUser.value != null){loginView.rout(TAGCOL)
                                }else{
                                    loginView.rout(TAGMAP)
                                }
                                StoreViewModel.getUserid().postValue(mAuth.uid)
                                StoreViewModel.getListTrak().postValue(mutableListOf())
                                mPostViewModel.upDate(DataBaseObjact(mAuth.uid!!, mutableListOf()))


                        } else {
                            if (task.exception is FirebaseAuthException) {
                                when ((task.exception as FirebaseAuthException).errorCode) {
                                    "ERROR_WRONG_PASSWORD" -> loginView.actionMassege(ActionMessage.ERROR_WRONG_PASSWORD.result)
                                    "ERROR_USER_NOT_FOUND" -> loginView.actionMassege(ActionMessage.ERROR_USER_NOT_FOUND.result)
                                    "ERROR_INVALID_EMAIL" -> loginView.actionMassege(ActionMessage.ERROR_USER_NOT_FOUND.result)
                                    else -> Log.e("*******Login*******" ,(task.exception as FirebaseAuthException).errorCode )
                                }
                            }
                        }
                    }
            } else {loginView.actionMassege(result.second.result)
            }
        }else{
            loginView.actionMassege("Включите Интернет!!!")
        }
    }




    override fun onStartSckreen(key: String) {
        loginView.rout(key)
    }


    override fun onStart() {
        this.mPostViewModel = loginView.getVM()
        if (mPostViewModel.allUser.value != null){loginView.rout(TAGCOL)}
    }

}