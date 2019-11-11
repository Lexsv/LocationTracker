package ua.com.location.presentor

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import ua.com.location.fragment.interfas.FragmentView
import ua.com.location.presentor.interfas.LoginPresentInterfas
import ua.com.location.util.ActionMessage
import javax.inject.Inject

class LoginPresent @Inject constructor(var fragmentView: FragmentView) :
    LoginPresentInterfas {

    val TAGREG = "RAGISTER"
    val TAGCOL = "COLECTION"


    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun login(email: String, password: String) {

        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        fragmentView.rout(TAGCOL)
                        Log.e("**********************", mAuth.uid!!)
                    } else {
                        Log.e(
                            "************",
                            (task.getException() as FirebaseAuthException).errorCode
                        )
                        if (task.exception is FirebaseAuthException) {
                            when ((task.exception as FirebaseAuthException).errorCode) {
                                "ERROR_WRONG_PASSWORD" -> fragmentView.errorMassege(ActionMessage.ERROR_WRONG_PASSWORD.result)
                                "ERROR_USER_NOT_FOUND" -> fragmentView.errorMassege(ActionMessage.ERROR_USER_NOT_FOUND.result)
                            }
                        }
                    }
                }
        } else {fragmentView.errorMassege(ActionMessage.ERROR_EMPTY_DATA.result)
        }
    }




    override fun startSckreen(key: String) {
        fragmentView.rout(key)
    }

}