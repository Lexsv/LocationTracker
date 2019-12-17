package ua.com.location.models.dialogModel

import ua.com.location.repository.room.contant.Content

interface IDialogVM {
    fun upDataLastId(lastId : Int)
    fun saveData(content: Content)
}