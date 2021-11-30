package com.source.sportivnyy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.source.sportivnyy.model.data.Producto
import com.source.sportivnyy.model.data.repository.ProductosDataSource
import java.lang.IllegalArgumentException
import kotlin.random.Random

class ShoppingCartViewModel(val datasource: ProductosDataSource) : ViewModel() {
    val productos_in_carritoLiveData = datasource.getProductosList()

    /* Para  crear un nuevo ProductoInCarrito y lo añade al DataSource*/
    fun insertProductoInCarrito(productoName:String?,productoDescripcion:String?,productoPrecio:Long?){
        if(productoName==null || productoDescripcion==null || productoPrecio==null){
            return
        }

        val image = datasource.getRandomProductoImageAsset()
        val newProducto = Producto(
            Random.nextLong(),
            productoName,
            image,
            productoDescripcion,
            productoPrecio
        )
        datasource.addProductoToCarrito(newProducto)
    }

    class ShoppingCartViewModelFactory(private val context:Context):ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ShoppingCartViewModel::class.java)){
                @Suppress("UNCHEKED_CAST")
                return ShoppingCartViewModel(
                    datasource = ProductosDataSource.getProductosInCarritoDataSource(context.resources)
                ) as T
            }
            throw IllegalArgumentException("Unknonw ViewModel class")
        }
    }
}