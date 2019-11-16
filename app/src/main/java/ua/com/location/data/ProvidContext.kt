package ua.com.location.data

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


@SuppressLint("Registered")
class ProvidContext : Application(){


    init {
        instance = this
    }
    companion object {
        private var instance: ProvidContext? = null
        fun getContext() : Context = instance!!.applicationContext
    }


}