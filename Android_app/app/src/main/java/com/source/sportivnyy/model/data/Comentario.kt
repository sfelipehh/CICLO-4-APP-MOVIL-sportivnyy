package com.source.sportivnyy.model.data

import  androidx.annotation.DrawableRes
import java.util.*

data class Comentario (
    val id:Long,
    val author:String,
    @DrawableRes
    val author_photo:Int?,
    val comment_text:String?,
    val rate:Int,
    val date:String
        )