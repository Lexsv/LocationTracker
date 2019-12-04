package ua.com.location.presentation.dialog



import ua.com.location.data.LocalStoreVW
import ua.com.location.models.repository.room.contant.Content
import javax.inject.Inject

class MyDialogPresentation @Inject constructor(var myDialogView: MyDialogView) : MyDialogPresentIntefas{


    override fun onSaveDiscription(title: String, discription: String,pair: Pair<Double,Double>) {
       val list = LocalStoreVW.getContent().value
        val content = Content()
        content.title = title
        content.descript = discription
        content.latitude = pair.first
        content.longitude = pair.second

        myDialogView.getVM().saveToRoom(content)
        LocalStoreVW.getContent().postValue(list!!.plus(content))
    }

    override fun onStart(tag: String) {
        when(tag){
            "VIEWITEM" -> myDialogView.showDascritionDialog()
            "MAP" -> myDialogView.showAddDialog()
        }
    }

}

