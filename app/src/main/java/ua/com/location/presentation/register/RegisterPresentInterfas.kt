package ua.com.location.presentation.register

interface RegisterPresentInterfas{
    fun startSckreen( key : String)
    fun registNew(name: String,email: String, password: String, repitPassword: String)
}