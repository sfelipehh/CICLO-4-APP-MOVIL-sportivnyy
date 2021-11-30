package com.source.sportivnyy.model.data.repository

import android.content.res.Resources
import com.source.sportivnyy.R
import com.source.sportivnyy.model.data.Producto

fun productosList(resources: Resources): List<Producto> {
    return listOf(
        Producto(
            id=1,
            name="Raqueta",
            image=R.drawable.ic_baseline_account_circle_24,
            descripcion="Una raqueta",
            precio=100
        ),
        Producto(
            id = 2,
            name = "Balón",
            image = R.drawable.ic_baseline_home_24,
            descripcion = "Un balón",
            precio = 20
        ),
        Producto(
            id = 3,
            name = "Pelota de tenis",
            image = R.drawable.ic_baseline_home_24,
            descripcion = "Una pelota de tenis",
            precio = 1000
        )
    )
}