package com.source.sportivnyy.model.data

import androidx.annotation.DrawableRes
import com.google.firebase.firestore.DocumentReference
import com.google.firestore.v1.MapValue
import java.io.Serializable
const val PRODUCTO_KEY_FOR_NAME = "name"
const val PRODUCTO_KEY_FOR_IMG = "imgurl"
const val PRODUCTO_KEY_FOR_PRECIO = "precio"
const val PRODUCTO_KEY_FOR_RESUME = "resume_descripcion"
const val PRODUCTO_KEY_FOR_DESCRIPCION = "descripcion"
const val PRODUCTO_KEY_FOR_COMENTARIOS = "comentarios"
const val COMENTARIO_KEY_FOR_AUTHOR = "author_comment"
const val COMENTARIO_KEY_FOR_IMG_AUTHOR = "image_author"
const val COMENTARIO_KEY_FOR_DATE = "date"
const val COMENTARIO_KEY_FOR_RATE = "rate"
const val COMENTARIO_KEY_FOR_TEXT = "text_comment"
class Producto: Serializable {
    lateinit var name : String
    lateinit var imgurl : String
    var precio : Long? = null
    lateinit var resume_descripcion : String
    lateinit var descripcion : String
    lateinit var comentarios : List<Map<String,Any>>
    /*keys for comentarios:
    * author_comment -> string
    * image_author -> string
    * date ->string
    * rate ->number
    * text_comment -> string*/
}