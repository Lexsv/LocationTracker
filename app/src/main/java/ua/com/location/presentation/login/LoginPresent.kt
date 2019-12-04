package ua.com.location.presentation.login

import android.util.Log
import androidx.lifecycle.Observer

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

import ua.com.location.data.LocalStoreVW
import ua.com.location.models.repository.room.userinfo.UserInfo


import ua.com.location.util.ActionMessage
import ua.com.location.util.ProvidContext
import ua.com.location.util.isNet
import ua.com.location.util.validEnterDataAut
import javax.inject.Inject

class LoginPresent @Inject constructor(var loginView: LoginView): LoginPresentInterfas {

    val TAGCOL = "COLECTION"
    val TAGMAP = "MAP"

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onLogin(email: String, password: String) {
        if (isNet(ProvidContext.getContext())) {
            val result = validEnterDataAut(email = email, password = password)
            if (result.first) {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            var name = "name empty"
                            if (mAuth.currentUser != null){name = mAuth.currentUser!!.displayName!!}
                            val user = UserInfo(id = mAuth.uid!!,name = name)
                            loginView.getVM().saveToRoom(user)
                            LocalStoreVW.getUserid().postValue(user)
                            loginView.getVM().fillData()
                            loginView.rout(TAGCOL)

                        } else {
                            if (task.exception is FirebaseAuthException) {
                                when ((task.exception as FirebaseAuthException).errorCode) {
                                    "ERROR_WRONG_PASSWORD" -> loginView.actionMassege(ActionMessage.ERROR_WRONG_PASSWORD.result)
                                    "ERROR_USER_NOT_FOUND" -> loginView.actionMassege(ActionMessage.ERROR_USER_NOT_FOUND.result)
                                    "ERROR_INVALID_EMAIL" -> loginView.actionMassege(ActionMessage.ERROR_USER_NOT_FOUND.result)
                                    else -> Log.e(
                                        "*****Login Error*****",
                                        (task.exception as FirebaseAuthException).errorCode
                                    )
                                }
                            }
                        }
                    }
            } else {
                loginView.actionMassege(result.second.result)
            }
        } else {
            loginView.actionMassege("Включите Интернет!!!")
        }
    }


    override fun onStartSckreen(key: String) {
        loginView.rout(key)
    }


    override fun onStart() {
        loginView.getVM().fillData()
        LocalStoreVW.getUserid().observe(loginView.getLifecycleOwner(), Observer {
           loginView.getVM().getDataUserFromFir(it.id)
           loginView.rout(TAGCOL)
        })

    }

}