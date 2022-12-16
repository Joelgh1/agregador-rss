package com.joelgh.app.commons

import com.google.gson.Gson

interface KSerializer {
    fun<T> toJson(src: T, srcClass: Class<T>): String
    fun<T> fromJson(src: String?, srcClass: Class<T>): T
}

class GsonSerializer : KSerializer{

    private val gson = Gson()

    override fun <T> toJson(src: T, srcClass: Class<T>): String = gson.toJson(src, srcClass)

    override fun <T> fromJson(src: String?, srcClass: Class<T>): T = gson.fromJson(src, srcClass)

}