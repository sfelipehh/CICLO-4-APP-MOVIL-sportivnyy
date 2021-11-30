package com.source.sportivnyy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.source.sportivnyy.model.data.Producto
import com.source.sportivnyy.model.data.repository.ComentariosDataSource
import com.source.sportivnyy.model.data.repository.ProductosDataSource
import java.lang.IllegalArgumentException

class DetailProductoViewModel(val ProductosDataSource: ProductosDataSource,
                              val ComentariosDataSource:ComentariosDataSource): ViewModel() {

    val comentariosLiveData = ComentariosDataSource.getCometariosList()

    fun getProductoForId(id:Long):Producto?{
        return ProductosDataSource.getProductoForId(id)
    }

    class DetailProductoViewModelFactory(private val context: Context):ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailProductoViewModel::class.java)){
                @Suppress("UNCHEKED_CAST")
                return DetailProductoViewModel(
                    ProductosDataSource = ProductosDataSource.getProductosDataSource(context.resources),
                    ComentariosDataSource = ComentariosDataSource.getComentariosDataSource(context.resources)
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}