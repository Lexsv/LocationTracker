package ua.com.location.models.savadata

import ua.com.location.data.LocatoinTrak

interface SaveDataInrerfas {
    fun saveToLostLocationTrak(locatoinTrak: LocatoinTrak)
}