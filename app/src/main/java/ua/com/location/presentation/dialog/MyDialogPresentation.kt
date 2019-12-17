package ua.com.location.presentation.dialog


import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.repository.room.contant.Content
import javax.inject.Inject

class MyDialogPresentation @Inject constructor(var myDialogView: MyDialogView) :
    MyDialogPresentIntefas {


    override fun onSaveDiscription(title: String, discription: String, pair: Pair<Double, Double>) {
        val list = LocalStoreVW.getContent().value
        var baseId = LocalStoreVW.lastID
        baseId++

        val content = Content()
        content.id = baseId
        content.title = title
        content.descript = discription
        content.latitude = pair.first
        content.longitude = pair.second
        if (list == null) {
            LocalStoreVW.getContent().postValue(listOf(content))
        } else {
            LocalStoreVW.getContent().postValue(list.plus(content))
        }
        myDialogView.getVM().saveData(content)
        myDialogView.getVM().upDataLastId(baseId)



    }

    override fun onStart(tag: String) {
        when (tag) {
            "VIEWITEM" -> myDialogView.showDascritionDialog()
            "MAP" -> myDialogView.showAddDialog()
        }
    }

}

