package ua.com.location.presentation.register

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.com.location.data.room.DataBaseObjact
import ua.com.location.models.roommodel.RoomData
import ua.com.location.models.roommodel.RoomWorkInterfas
import ua.com.location.presentation.login.LoginView
import ua.com.location.util.ActionMessage
import ua.com.location.util.validEnterDataRegister
import javax.inject.Inject

class RegisterPresent @Inject constructor(val loginView: LoginView):
    RegisterPresentInterfas {
    val roomWorkInterfas: RoomWorkInterfas
    init {
        roomWorkInterfas = RoomData()
    }

    val TAGMAP = "MAP"

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun startSckreen(key: String) {
        loginView.rout(key)
    }

    override fun registNew(name: String, email: String, password: String, repitPassword: String) {
         val resalt =    validEnterDataRegister(name = name,
                                                                email = email,
                                                                password = password,
                                                                repitPassword = repitPassword)

            if (!resalt.first) {
                loginView.actionMassege(resalt.second.result)

            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            GlobalScope.launch {
                                roomWorkInterfas.insert( DataBaseObjact(mAuth.uid!!, mutableListOf()))
                            }


                            loginView.rout(TAGMAP)
                            mAuth.currentUser!!.updateProfile(
                                UserProfileChangeRequest.Builder().setDisplayName(name).build())
                                .addOnCompleteListener { taskUpData ->
                                    if (taskUpData.isSuccessful) {
                                        loginView.actionMassege("Привет ${mAuth.currentUser!!.displayName}\nвыберите точку")}
                                }

                        } else {
                            if (task.getException() is FirebaseAuthException) {

                                when ((task.getException() as FirebaseAuthException).errorCode) {
                                    "ERROR_WRONG_PASSWORD" -> loginView.actionMassege(ActionMessage.ERROR_WRONG_PASSWORD.result)
                                    "ERROR_USER_NOT_FOUND" -> loginView.actionMassege(ActionMessage.ERROR_USER_NOT_FOUND.result)
                                    "ERROR_EMAIL_ALREADY_IN_USE" -> loginView.actionMassege(ActionMessage.ERROR_EMAIL_ALREADY_IN_USE.result)
                                }
                            }
                        }
                    }
            }
        }
    }


