package com.source.sportivnyy.model.data

import androidx.annotation.DrawableRes

data class Producto (
    val id:Long,
    val name:String,
    @DrawableRes
    val image:Int,
    val descripcion:String,
    val precio:Long
        )