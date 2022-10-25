package com.simpletech.ewaysample.services

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.simpletech.ewaysample.models.Transaction
import com.simpletech.ewaysample.models.TransactionElement
import java.io.IOException

// Gets Json from assets folder and parses it to object

object LocalData {

    @Throws
    fun getData(context: Context): Array<TransactionElement>? {

        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("response.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (exception: IOException) {
         throw(exception)
        }
        return  Gson().fromJson(jsonString,Array<TransactionElement>::class.java)
    }
}