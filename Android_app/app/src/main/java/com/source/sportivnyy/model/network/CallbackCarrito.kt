package com.source.sportivnyy.model.network

import java.lang.Exception

interface CallbackCarrito<T> {
    fun onSuccessCarrito(resultCarrito : T?)
    fun onFailed(exception: Exception)
}