package com.source.sportivnyy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentReference
import com.source.sportivnyy.model.data.Producto
import com.source.sportivnyy.model.network.CallbackProductos
import com.source.sportivnyy.model.network.FirebaseServices
import java.lang.Exception

class HomeViewModel : ViewModel() {
    val firestoreService = FirebaseServices.FirestoreService()
    var listProducts : MutableLiveData<List<Producto>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getProductsFromFirebase()
    }
    fun getProductsFromFirebase(){
        firestoreService.getProductos(object : CallbackProductos<List<Producto>>{
            override fun onSuccessProductos(resultProductos : List<Producto>?) {
                listProducts.postValue(resultProductos!!)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }
    fun processFinished(){
        isLoading.value=true
    }
}