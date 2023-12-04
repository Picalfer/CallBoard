package com.landfathich.callboard.utils

import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.util.Locale

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

    fun getAllCities(context: Context, country: String): ArrayList<String> {
        val cities = ArrayList<String>()
        try {
            val inputStream: InputStream =
                context.assets.open("countriesToCities.json") // создаем inputStream и открываем файл с городами
            val size: Int = inputStream.available() // получаем размер файла
            val bytesArray = ByteArray(size) // создаем массив байтов указывая его размер
            inputStream.read(bytesArray) // читаем файл в массив байтов
            val jsonFile: String =
                String(bytesArray) // преобразуем массив байтов в строку JSON и сохраняем в переменную
            val jsonObject = JSONObject(jsonFile) // преобразуем строку JSON в JSONObject

            val cityNames =
                jsonObject.getJSONArray(country) // получаем массив городов по названию страны

            for (i in 0 until cityNames.length()) {
                cities.add(cityNames.getString(i))
            }
        } catch (e: IOException) {

        }
        return cities
    }

    fun filterListData(list: ArrayList<String>, searchText: String): ArrayList<String> {
        val filteredList = ArrayList<String>()
        filteredList.clear()

        if (searchText == null) {
            filteredList.add("No results found")
            return filteredList
        }

        for (selection: String in list) {
            if (selection.lowercase(Locale.ROOT).startsWith(searchText.lowercase(Locale.ROOT))) {
                filteredList.add(selection)
            }
        }
        if (filteredList.size == 0) {
            filteredList.add("No results found")
        }
        return filteredList
    }
}