package com.source.sportivnyy.model.network

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.source.sportivnyy.model.data.*
import java.io.Serializable


const val PRODUCTOS_COLLECTION_NAME = "productos"
const val CARRITO_COLLECTION_NAME = "users"
class FirebaseServices {
    class FirestoreService{
        lateinit var list :List<Producto>
        lateinit var carrito : Carrito
        val firebaseFirestore = FirebaseFirestore.getInstance()
        fun getProductos(callbackProductos: CallbackProductos<List<Producto>>){
        firebaseFirestore.collection(PRODUCTOS_COLLECTION_NAME)
            .get()
            .addOnSuccessListener{result ->
                for (doc in result){
                    list = result.toObjects(Producto::class.java)
                    break
                }

                callbackProductos.onSuccessProductos(list)
            }
        }

        fun getCarrito(callback: CallbackCarrito<Carrito>, userId:String){
            val documentQuery = firebaseFirestore.collection(CARRITO_COLLECTION_NAME)
                .document(userId)
                documentQuery.get()
                .addOnSuccessListener {
                    result -> carrito = result.toObject(Carrito::class.java)!!
                    callback.onSuccessCarrito(carrito)

                }
        }
        fun productoToData(producto: Producto): Map<String, Serializable?> {
            val data_comentarios = ArrayList<Map<String,Any>>()
            for (comentario in producto.comentarios){
                data_comentarios.add(
                    mapOf(
                        COMENTARIO_KEY_FOR_AUTHOR to comentario[COMENTARIO_KEY_FOR_AUTHOR]!!,
                        COMENTARIO_KEY_FOR_IMG_AUTHOR to comentario[COMENTARIO_KEY_FOR_IMG_AUTHOR]!!,
                        COMENTARIO_KEY_FOR_DATE to comentario[COMENTARIO_KEY_FOR_DATE]!!,
                        COMENTARIO_KEY_FOR_RATE to comentario[COMENTARIO_KEY_FOR_RATE]!!,
                        COMENTARIO_KEY_FOR_TEXT to comentario[COMENTARIO_KEY_FOR_TEXT]!!
                    )
                )
            }
            val data_producto = mapOf(
                PRODUCTO_KEY_FOR_NAME to producto.name,
                PRODUCTO_KEY_FOR_DESCRIPCION to producto.descripcion,
                PRODUCTO_KEY_FOR_IMG to producto.imgurl,
                PRODUCTO_KEY_FOR_RESUME  to producto.resume_descripcion,
                PRODUCTO_KEY_FOR_PRECIO  to producto.precio,
                PRODUCTO_KEY_FOR_COMENTARIOS to data_comentarios
            )
            return data_producto
        }
    }
    class FirebaseAuthService {
        val firebaseAuth = FirebaseAuth.getInstance()
    }

}