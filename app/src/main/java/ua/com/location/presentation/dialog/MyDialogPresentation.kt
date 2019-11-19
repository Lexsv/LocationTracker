package ua.com.location.presentation.dialog

import ua.com.location.data.LocatoinTrak
import ua.com.location.models.savadata.SaveData
import ua.com.location.models.savadata.SaveDataInrerfas
import javax.inject.Inject

class MyDialogPresentation @Inject constructor(var myDialogView: MyDialogView) : MyDialogPresentIntefas{


    override fun onSaveDiscription(title: String, discription: String,pair: Pair<Double,Double>) {


    }

}