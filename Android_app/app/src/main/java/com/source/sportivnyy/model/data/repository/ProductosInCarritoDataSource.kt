package com.source.sportivnyy.model.data.repository

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.source.sportivnyy.model.data.ProductoInCarrito
//Thanks to: https://github.com/android/views-widgets-samples
class ProductosInCarritoDataSource(resources: Resources) {
    private val initialProductosList=productosList(resources)
    private val productos_in_carritoLiveData=MutableLiveData(initialProductosList)

    /* Adds Producto to liveData and posts value. */
    fun addProductoToCarrito(productoInCarrito: ProductoInCarrito) {
        val currentList = productos_in_carritoLiveData.value
        if (currentList == null) {
            productos_in_carritoLiveData.postValue(listOf(productoInCarrito))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, productoInCarrito)
            productos_in_carritoLiveData.postValue(updatedList)
        }
    }

    /* Removes Producto from liveData and posts value. */
    fun removeProductoFromCarrito(productoInCarrito: ProductoInCarrito) {
        val currentList = productos_in_carritoLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(productoInCarrito)
            productos_in_carritoLiveData.postValue(updatedList)
        }
    }
    /* Returns Producto given an ID. */
    fun getProductoForId(id: Long): ProductoInCarrito? {
        productos_in_carritoLiveData.value?.let { prductoInCarrito ->
            return prductoInCarrito.firstOrNull{ it.id == id}
        }
        return null
    }
    fun getProductosInCarritoList():LiveData<List<ProductoInCarrito>>{
        return productos_in_carritoLiveData
    }

    /* Returns a random flower asset for flowers that are added. */
    fun getRandomProductoImageAsset(): Int? {
        val randomNumber = (initialProductosList.indices).random()
        return initialProductosList[randomNumber].image
    }
    companion object{
        private var INSTANCE:ProductosInCarritoDataSource?=null

        fun getProductosInCarritoDataSource(resources: Resources):ProductosInCarritoDataSource{
            return synchronized(ProductosInCarritoDataSource::class){
                val newIntance = INSTANCE?: ProductosInCarritoDataSource(resources)
                INSTANCE=newIntance
                newIntance
            }
        }
    }
}