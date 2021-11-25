package com.source.sportivnyy.model.data.repository

import android.content.res.Resources
import com.source.sportivnyy.R
import com.source.sportivnyy.model.data.ProductoInCarrito

fun productosList(resources: Resources): List<ProductoInCarrito> {
    return listOf(
        ProductoInCarrito(
            id=1,
            name="Raqueta",
            image=R.drawable.ic_baseline_account_circle_24,
            descripcion="Una raqueta",
            precio=100
        ),
        ProductoInCarrito(
            id = 2,
            name = "Balón",
            image = R.drawable.ic_baseline_home_24,
            descripcion = "Un balón",
            precio = 20
        )
    )
}