package com.source.sportivnyy.model.data.repository

import android.content.res.Resources
import com.source.sportivnyy.R
import com.source.sportivnyy.model.data.Comentario
import java.util.*
const val DEFAULT_PHOTO = R.drawable.ic_baseline_account_circle_24_black
fun comentariosList(resources: Resources):List<Comentario> {
    return listOf(
        Comentario(
            id = 1,
            author = "Juan",
            author_photo = DEFAULT_PHOTO,
            comment_text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc commodo, elit eu sollicitudin ornare, leo lorem lacinia ligula, vel imperdiet lectus ex id tortor. Aliquam eu aliquam quam. Cras sed tellus luctus dui molestie aliquet eu at leo. Vivamus id odio dapibus, porta ante id, hendrerit tortor. Proin nec vulputate nisl. Cras a ultricies velit. Duis maximus leo sit amet tellus porttitor, in posuere risus blandit. Pellentesque quis lectus lectus. Praesent in purus ut diam bibendum efficitur et eu augue.",
            rate = 5,
            date = Date().toString()
        ),
        Comentario(
            id = 2,
            author = "Carlos",
            author_photo = DEFAULT_PHOTO,
            comment_text = "Es un buen producto",
            rate = 5,
            date = Date().toString()
        ),
        Comentario(
            id = 3,
            author = "David",
            author_photo = DEFAULT_PHOTO,
            comment_text = "No me gusto :(",
            rate = 1,
            date = Date().toString()
        )
    )
}