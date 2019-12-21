package ua.com.location.presentation.dialog


import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.repository.room.content.Content
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
            if (list.size == 1){
                LocalStoreVW.getContent().postValue(list.plus(content))
            }else{
                val arrayList = list as ArrayList
                arrayList.add(0,content)
                LocalStoreVW.getContent().postValue(arrayList)
            }
        }
        myDialogView.getVM().saveData(content)
        myDialogView.getVM().upDataLastId(baseId)

    }

    override fun onEdit() {
        myDialogView.editContant()
    }

    override fun upDataItom(title: String, discription: String) {
        myDialogView.savedContant()
        val upDatItom = LocalStoreVW.workingItom
        upDatItom!!.title = title
        upDatItom.descript = discription
        myDialogView.getVM().upDataContant(upDatItom)



    }

    override fun onStart(tag: String) {
        when (tag) {
            "VIEWITEM" -> myDialogView.showDascritionDialog()
            "MAP" -> myDialogView.showAddDialog()
        }
    }

}

