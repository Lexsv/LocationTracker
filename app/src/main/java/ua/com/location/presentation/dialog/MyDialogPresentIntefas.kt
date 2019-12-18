package ua.com.location.presentation.dialog

import kotlinx.android.synthetic.main.dialog_fragment_description.*

interface MyDialogPresentIntefas {
    fun onSaveDiscription(title:String,discription:String,pair: Pair<Double,Double>)
    fun  onStart(tag: String)
    fun onEdit()
    fun upDataItom(title : String, discription : String)
}