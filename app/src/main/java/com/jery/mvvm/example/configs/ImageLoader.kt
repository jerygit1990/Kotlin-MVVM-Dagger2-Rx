package com.jery.mvvm.example.configs

import android.content.Context
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