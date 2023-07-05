package com.kweku.armah.core.data.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kweku.armah.core.domain.model.Answer
import java.lang.reflect.Type
import javax.inject.Inject

@ProvidedTypeConverter
class AnswerConverter @Inject constructor(private val gson: Gson) {
    @TypeConverter
    fun fromStringToList(value: String?): List<Answer> {
        val listType: Type = object : TypeToken<List<Answer>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringToList(list: List<Answer>): String {
        val type = object : TypeToken<List<Answer>>() {}.type
        return gson.toJson(list, type)
    }
}
