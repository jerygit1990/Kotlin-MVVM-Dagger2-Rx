package com.jery.chappibot.assignment.configs

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoader {
    companion object{
        fun loadImage(url: String, context: Context, imageView: ImageView) {
//            Log.d("ImageLoader", "load image: " + url)
            Glide.with(context).load(url).into(imageView)
        }
    }
}