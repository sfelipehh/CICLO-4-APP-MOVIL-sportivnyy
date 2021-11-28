package com.source.sportivnyy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.source.sportivnyy.model.data.repository.ProductosDataSource
import java.lang.IllegalArgumentException

class HomeViewModel(val datasource: ProductosDataSource) : ViewModel() {
    val productos_in_homeLiveData = datasource.getProductosList()

    class HomeViewModelFactory(private val context:Context):ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
                @Suppress("UNCHEKED_CAST")
                return HomeViewModel(
                    datasource = ProductosDataSource.getProductosDataSource(context.resources)
                ) as T
            }
            throw IllegalArgumentException("Unknonw ViewModel class")
        }
    }
}