package ua.com.location.util

fun validEnterDataRegister(name: String, email: String, password: String, repitPassword: String):Pair<Boolean,ActionMessage>{

   return when{
       name.isEmpty()
          && email.isEmpty()
          && password.isEmpty()
          &&  repitPassword.isEmpty() -> false to ActionMessage.ERROR_EMPTY_DATA
        password != repitPassword -> false to ActionMessage.ERROR_NO_COINCIDENCE
        password.length < 6 ->      false to ActionMessage.ERROR_LENGTH
       else -> true to ActionMessage.SUCCESSFUL
    }

}


fun validEnterDataAut( email: String, password: String):Pair<Boolean,ActionMessage>{

    return when{
                email.isEmpty()
                && password.isEmpty() -> false to ActionMessage.ERROR_EMPTY_DATA
                password.length < 6 ->      false to ActionMessage.ERROR_LENGTH
                 else -> true to ActionMessage.SUCCESSFUL
    }

}