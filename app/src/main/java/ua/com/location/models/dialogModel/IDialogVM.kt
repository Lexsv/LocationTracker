package ua.com.location.models.dialogModel

import ua.com.location.repository.room.content.Content

interface IDialogVM {
    fun upDataLastId(lastId : Int)
    fun saveData(content: Content)
    fun upDataContant(upDatItom: Content)
}