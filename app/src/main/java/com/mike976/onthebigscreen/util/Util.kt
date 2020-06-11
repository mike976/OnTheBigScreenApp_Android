package com.mike976.onthebigscreen.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.onthebigscreen.R

fun getProgressDrawable(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

//image view extension method
fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .fallback(R.drawable.ic_baseline_broken_image_24)
        .error(R.drawable.ic_baseline_broken_image_24)

        //.fallback(ColorDrawable(R.color.colorPrimary.toInt()))

    Glide.with(this.context)                    //this = Imageview we are extending here
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}