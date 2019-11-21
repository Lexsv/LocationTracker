package ua.com.location.presentation.register

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.com.location.data.StoreViewModel
import ua.com.location.data.room.DataBaseObjact
import ua.com.location.presentation.login.LoginView
import ua.com.location.util.ActionMessage
import ua.com.location.util.ProvidContext
import ua.com.location.util.getConnectivityNet
import ua.com.location.util.validEnterDataRegister
import javax.inject.Inject

class RegisterPresent @Inject constructor(val registerView: RegisterView):
    RegisterPresentInterfas {

    val TAGMAP = "MAP"

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun startSckreen(key: String) {
        registerView.rout(key)
    }

    override fun registNew(name: String, email: String, password: String, repitPassword: String) {
        if (getConnectivityNet(ProvidContext.getContext())) {
            val resalt =    validEnterDataRegister(name = name,
                                                                   email = email,
                                                                   password = password,
                                                                   repitPassword = repitPassword)

            if (!resalt.first) {
                registerView.actionMassege(resalt.second.result)

            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            registerView.rout(TAGMAP)
                            mAuth.currentUser!!.updateProfile(
                                UserProfileChangeRequest.Builder().setDisplayName(name).build())
                                .addOnCompleteListener { taskUpData ->
                                    if (taskUpData.isSuccessful) {
                                        GlobalScope.launch {
                                            val mPostViewModel = registerView.getVM()
                                            StoreViewModel.getUserid().postValue(mAuth.uid)
                                            StoreViewModel.getListTrak().postValue(mutableListOf())
                                            mPostViewModel.upDate(DataBaseObjact(mAuth.uid!!, mutableListOf()))
                                        }
                                        registerView.actionMassege("Привет ${mAuth.currentUser!!.displayName}\nвыберите точку")}
                                }

                        } else {
                            if (task.getException() is FirebaseAuthException) {

                                when ((task.getException() as FirebaseAuthException).errorCode) {
                                    "ERROR_WRONG_PASSWORD" -> registerView.actionMassege(ActionMessage.ERROR_WRONG_PASSWORD.result)
                                    "ERROR_USER_NOT_FOUND" -> registerView.actionMassege(ActionMessage.ERROR_USER_NOT_FOUND.result)
                                    "ERROR_EMAIL_ALREADY_IN_USE" -> registerView.actionMassege(ActionMessage.ERROR_EMAIL_ALREADY_IN_USE.result)
                                }
                            }
                        }
                    }
            }
        }else{
            registerView.actionMassege("Включите Интернет!!!")
        }
    }
    }


