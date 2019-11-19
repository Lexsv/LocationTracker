package ua.com.location.presentation.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ua.com.location.data.StoreViewModel
import ua.com.location.data.room.DataBaseObjact
import ua.com.location.models.roommodel.RoomData
import ua.com.location.models.roommodel.RoomWorkInterfas
import ua.com.location.util.ActionMessage
import ua.com.location.util.validEnterDataAut
import javax.inject.Inject

class LoginPresent @Inject constructor(var loginView: LoginView) :
    LoginPresentInterfas {
    val roomWorkInterfas: RoomWorkInterfas
    init {
        roomWorkInterfas = RoomData()
    }


    val TAGCOL = "COLECTION"
    val TAGMAP = "MAP"

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onLogin(email: String, password: String) {
        val result = validEnterDataAut(email=email,password = password)
        if (result.first) {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {


                        val resultUs = GlobalScope.async {
                            roomWorkInterfas.insert( DataBaseObjact(mAuth.uid!!, mutableListOf()))
                            return@async roomWorkInterfas.getAllUser()
                        }

                        GlobalScope.launch {
                            val mResult = resultUs.await()
                            if ( mResult[0].listLocatoinTrak!!.isEmpty()){
                                loginView.rout(TAGMAP)
                            }else{
                                loginView.rout(TAGCOL)
                            }
                        }


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
    }




    override fun onStartSckreen(key: String) {
        loginView.rout(key)
    }


    override fun onStart() {
        val result = GlobalScope.async {
            roomWorkInterfas.getAllUser()

        }

        GlobalScope.launch {

            val mResult = result.await()
            if ( !mResult.isEmpty()){
                if (mResult[0].listLocatoinTrak != null) {
                    StoreViewModel.getListTrak().postValue(mResult[0].listLocatoinTrak)
                    loginView.rout(TAGCOL)
                }else {
                    loginView.rout(TAGMAP)
                }
            }
        }

    }

}