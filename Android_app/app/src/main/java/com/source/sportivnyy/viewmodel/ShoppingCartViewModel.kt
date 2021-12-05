package com.source.sportivnyy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.source.sportivnyy.model.data.Carrito
import com.source.sportivnyy.model.data.Producto
import com.source.sportivnyy.model.network.CallbackCarrito
import com.source.sportivnyy.model.network.FirebaseServices
import java.lang.Exception

class ShoppingCartViewModel : ViewModel(){
    val firestoreService = FirebaseServices.FirestoreService()
    val userId = FirebaseServices.FirebaseAuthService().firebaseAuth.currentUser!!.uid
    val listProductos_Carrito : MutableLiveData<List<Producto>> = MutableLiveData()
    val resumeForSubtotal : MutableLiveData<List<Pair<String,Long>>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getCarritoFromFirebase()
    }
    fun getCarritoFromFirebase(){
        firestoreService.getCarrito(userId = userId, callback =  object : CallbackCarrito<Carrito>{
            override fun onSuccessCarrito(resultCarrito: Carrito?) {
                listProductos_Carrito.postValue(resultCarrito!!.carrito)
                getInfoForSubtotal(resultCarrito.carrito)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }
    fun getInfoForSubtotal(list_productos : List<Producto>){
        var list_pairs = ArrayList<Pair<String,Long>>()
            for (producto in list_productos){
                if (producto != null){
                val pair = Pair(producto.name,producto.precio!!)
                list_pairs.add(pair)}
            }
        resumeForSubtotal.postValue(list_pairs)
    }
    fun processFinished(){
        isLoading.value=true
    }
}