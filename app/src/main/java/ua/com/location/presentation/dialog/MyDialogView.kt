package ua.com.location.presentation.dialog

import androidx.lifecycle.LifecycleOwner

interface MyDialogView {
    fun getLifecycleOwner(): LifecycleOwner
}