package com.source.sportivnyy.model.data.repository

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.source.sportivnyy.model.data.Producto
//Thanks to: https://github.com/android/views-widgets-samples
class ProductosDataSource(resources: Resources) {
    private val initialProductosList=productosList(resources)
    private val productos_in_carritoLiveData=MutableLiveData(initialProductosList)

    /* Adds Producto to liveData and posts value. */
    fun addProductoToCarrito(producto: Producto) {
        val currentList = productos_in_carritoLiveData.value
        if (currentList == null) {
            productos_in_carritoLiveData.postValue(listOf(producto))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, producto)
            productos_in_carritoLiveData.postValue(updatedList)
        }
    }

    /* Removes Producto from liveData and posts value. */
    fun removeProductoFromCarrito(producto: Producto) {
        val currentList = productos_in_carritoLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(producto)
            productos_in_carritoLiveData.postValue(updatedList)
        }
    }
    /* Returns Producto given an ID. */
    fun getProductoForId(id: Long): Producto? {
        productos_in_carritoLiveData.value?.let { productoInCarrito ->
            return productoInCarrito.firstOrNull{ it.id == id}
        }
        return null
    }
    fun getProductosList():LiveData<List<Producto>>{
        return productos_in_carritoLiveData
    }

    /* Returns a random image asset for productos that are added. */
    fun getRandomProductoImageAsset(): Int {
        val randomNumber = (initialProductosList.indices).random()
        return initialProductosList[randomNumber].image
    }
    companion object{
        private var INSTANCE:ProductosDataSource?=null

        fun getProductosInCarritoDataSource(resources: Resources):ProductosDataSource{
            return synchronized(ProductosDataSource::class){
                val newInstance = INSTANCE?: ProductosDataSource(resources)
                INSTANCE=newInstance
                newInstance
            }
        }
        fun getProductosDataSource(resources: Resources):ProductosDataSource{
            return synchronized(ProductosDataSource::class.java) {
                val newInstante = INSTANCE ?: ProductosDataSource(resources)
                INSTANCE = newInstante
                newInstante
            }
        }
    }
}