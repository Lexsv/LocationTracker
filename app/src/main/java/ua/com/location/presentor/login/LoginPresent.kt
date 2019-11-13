package ua.com.location.presentor.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import ua.com.location.util.ActionMessage
import javax.inject.Inject

class LoginPresent @Inject constructor(var loginView: LoginView) :
    LoginPresentInterfas {

    val TAGREG = "RAGISTER"
    val TAGCOL = "COLECTION"


    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun login(email: String, password: String) {

        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        loginView.rout(TAGCOL)
                        Log.e("**********************", mAuth.uid!!)
                    } else {
                        Log.e(
                            "************",
                            (task.getException() as FirebaseAuthException).errorCode
                        )
                        if (task.exception is FirebaseAuthException) {
                            when ((task.exception as FirebaseAuthException).errorCode) {
                                "ERROR_WRONG_PASSWORD" -> loginView.actionMassege(ActionMessage.ERROR_WRONG_PASSWORD.result)
                                "ERROR_USER_NOT_FOUND" -> loginView.actionMassege(ActionMessage.ERROR_USER_NOT_FOUND.result)
                            }
                        }
                    }
                }
        } else {loginView.actionMassege(ActionMessage.ERROR_EMPTY_DATA.result)
        }
    }




    override fun startSckreen(key: String) {
        loginView.rout(key)
    }

}