package com.source.sportivnyy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.source.sportivnyy.model.data.Producto
import com.source.sportivnyy.model.data.repository.ProductosDataSource
import java.lang.IllegalArgumentException

class DetailProductoViewModel(val dataSource: ProductosDataSource): ViewModel() {

    fun getProductoForId(id:Long):Producto?{
        return dataSource.getProductoForId(id)
    }

    class DetailProductoViewModelFactory(private val context: Context):ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailProductoViewModel::class.java)){
                @Suppress("UNCHEKED_CAST")
                return DetailProductoViewModel(
                    dataSource = ProductosDataSource.getProductosDataSource(context.resources)
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}