package com.danilo.furtado.theweatherapp.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String) {
    if (url.isEmpty()) return
    Picasso.get()
        .load(url)
        .into(this)
}
