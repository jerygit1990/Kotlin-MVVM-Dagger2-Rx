package com.jery.chappibot.assignment.models.res

import com.google.gson.annotations.SerializedName

data class ContentRes (
    @SerializedName("preview_image")
    var previewImage: ImageRes,
    @SerializedName("duration")
    var duration: Long

)