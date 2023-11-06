package com.landfathich.callboard.utils

import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

object CityHelper {
    fun getAllCountries(context: Context): ArrayList<String> {
        val countries = ArrayList<String>()
        try {
            val inputStream: InputStream =
                context.assets.open("countriesToCities.json") // создаем inputStream и открываем файл с городами
            val size: Int = inputStream.available() // получаем размер файла
            val bytesArray = ByteArray(size) // создаем массив байтов указывая его размер
            inputStream.read(bytesArray) // читаем файл в массив байтов
            val jsonFile: String =
                String(bytesArray) // преобразуем массив байтов в строку JSON и сохраняем в переменную
            val jsonObject = JSONObject(jsonFile) // преобразуем строку JSON в JSONObject
            val countryNames = jsonObject.names() // получаем все ключи

            if (countryNames != null) {
                for (i in 0 until countryNames.length()) {
                    countries.add(countryNames.getString(i))
                }
            }
        } catch (e: IOException) {

        }
        return countries
    }
}