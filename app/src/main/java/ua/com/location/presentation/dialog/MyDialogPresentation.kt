package ua.com.location.presentation.dialog

import androidx.lifecycle.Observer
import ua.com.location.data.LocatoinTrak
import ua.com.location.data.StoreViewModel

import javax.inject.Inject

class MyDialogPresentation @Inject constructor(var myDialogView: MyDialogView) : MyDialogPresentIntefas{


    override fun onSaveDiscription(title: String, discription: String,pair: Pair<Double,Double>) {
        val list = StoreViewModel.getListTrak().value
        StoreViewModel.getListTrak().postValue(list!!.plus(LocatoinTrak(title,"$discription \n ${pair}",pair)))
    }

    override fun onStart(tag: String) {
        when(tag){
            "VIEWITEM" -> myDialogView.showDascritionDialog()
            "MAP" -> myDialogView.showAddDialog()
        }
    }

}

