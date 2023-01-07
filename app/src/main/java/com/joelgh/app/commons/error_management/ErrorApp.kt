package com.joelgh.app.commons.error_management

sealed class ErrorApp {
    class DataError() : ErrorApp()
    object UnknowError : ErrorApp()
}