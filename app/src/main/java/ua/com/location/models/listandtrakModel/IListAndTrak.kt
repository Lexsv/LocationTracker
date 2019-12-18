package ua.com.location.models.listandtrakModel

import ua.com.location.repository.room.content.Content

interface IListAndTrak {
    fun exit()
    fun removeItom(content : Content)
}