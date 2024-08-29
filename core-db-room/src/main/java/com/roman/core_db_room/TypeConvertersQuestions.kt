package com.roman.core_db_room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConvertersQuestions {

    @TypeConverter
    fun fromQuestionsJson(text: String): List<String>? {
        val arrayTutorialType = object : TypeToken<List<String>?>() {}.type
        val adapterGson = Gson()
        return adapterGson.fromJson<List<String>>(text, arrayTutorialType)
    }

    @TypeConverter
    fun dataQuestionsToJson(listGenre: List<String>): String {
        val adapterGson = Gson()
        return adapterGson.toJson(listGenre)
    }


}