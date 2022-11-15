package com.joelgh.app.commons

interface KSerializer {
    fun<T> toJson(src: T, srcClass: Class<T>): String
    fun<T> fromJson(src: String, srcClass: Class<T>): T
}