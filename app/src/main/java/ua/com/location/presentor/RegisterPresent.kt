package ua.com.location.presentor

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import ua.com.location.fragment.interfas.FragmentView
import ua.com.location.presentor.interfas.RegisterPresentInterfas
import ua.com.location.util.ActionMessage
import javax.inject.Inject

class RegisterPresent @Inject constructor(val fragmentView: FragmentView):RegisterPresentInterfas {

    val TAGMAP = "MAP"

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var actionMessage: ActionMessage = ActionMessage.SUCCESSFUL

    override fun startSckreen(key: String) {
        fragmentView.rout(key)
    }

    override fun registNew(name: String, email: String, password: String, repitPassword: String) {

        if (password != repitPassword) {actionMessage = ActionMessage.ERROR_NO_COINCIDENCE}
        if (password.length < 6) { actionMessage = ActionMessage.ERROR_LENGTH}

        when (actionMessage) {

            ActionMessage.ERROR_NO_COINCIDENCE -> {
                fragmentView.errorMassege(ActionMessage.ERROR_NO_COINCIDENCE.result)
                actionMessage = ActionMessage.SUCCESSFUL
            }
            ActionMessage.ERROR_LENGTH -> {
                fragmentView.errorMassege(ActionMessage.ERROR_LENGTH.result)
                actionMessage = ActionMessage.SUCCESSFUL
            }

            else -> {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            fragmentView.rout(TAGMAP)
                            mAuth.currentUser!!.updateProfile(
                                UserProfileChangeRequest.Builder().setDisplayName(name).build())
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {fragmentView.errorMassege("Привет ${mAuth.currentUser!!.displayName}\nвыберите точку")}
                                }

                        } else {
                            if (task.getException() is FirebaseAuthException) {

                                when ((task.getException() as FirebaseAuthException).errorCode) {
                                    "ERROR_WRONG_PASSWORD" -> fragmentView.errorMassege(ActionMessage.ERROR_WRONG_PASSWORD.result)
                                    "ERROR_USER_NOT_FOUND" -> fragmentView.errorMassege(ActionMessage.ERROR_USER_NOT_FOUND.result)
                                    "ERROR_EMAIL_ALREADY_IN_USE" -> fragmentView.errorMassege(ActionMessage.ERROR_EMAIL_ALREADY_IN_USE.result)
                                }
                            }
                        }
                    }
            }
        }
    }


}