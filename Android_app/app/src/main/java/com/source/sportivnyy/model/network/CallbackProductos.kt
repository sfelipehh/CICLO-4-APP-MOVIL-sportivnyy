package com.source.sportivnyy.model.network

import java.lang.Exception

interface CallbackProductos<T> {
    fun onSuccessProductos(resultProductos : T?)
    fun onFailed(exception: Exception)
}