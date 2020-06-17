package com.mike976.onthebigscreen.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.onthebigscreen.R
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import kotlin.math.absoluteValue


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
        .fallback(R.drawable.media_placeholder)
        .error(R.drawable.media_placeholder)

        //.fallback(ColorDrawable(R.color.colorPrimary.toInt()))

    Glide.with(this.context)                    //this = Imageview we are extending here
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}

fun TextView.applyRoundedCorners(fillColor: String?) {

    val radius = resources.getDimension(R.dimen.corner_radius)
    val shapeAppearanceModel: ShapeAppearanceModel = ShapeAppearanceModel()
        .toBuilder()
        .setAllCorners(CornerFamily.ROUNDED, radius.toFloat())
        .build()

    val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)
    if(fillColor != null) {
        shapeDrawable.fillColor = ColorStateList.valueOf(Color.parseColor(fillColor))
    }

    ViewCompat.setBackground(this, shapeDrawable)
}
