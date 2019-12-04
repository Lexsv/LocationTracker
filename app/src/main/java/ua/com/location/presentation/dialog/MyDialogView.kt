package ua.com.location.presentation.dialog

import androidx.lifecycle.LifecycleOwner
import ua.com.location.models.IDistributorData

interface MyDialogView {
    fun getLifecycleOwner(): LifecycleOwner
    fun showAddDialog()
    fun showDascritionDialog()
    fun getVM(): IDistributorData
}