package com.jery.chappibot.assignment.models.res

import com.google.gson.annotations.SerializedName

data class ImageRes (
    @SerializedName("href")
    var href: String,
    @SerializedName("width")
    var width: String,
    @SerializedName("height")
    var height: String
)