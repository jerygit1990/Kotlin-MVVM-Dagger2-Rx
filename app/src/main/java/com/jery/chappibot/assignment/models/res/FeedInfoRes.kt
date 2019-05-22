package com.jery.chappibot.assignment.models.res

import com.google.gson.annotations.SerializedName

data class FeedInfoRes (
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("published_date")
    var pubDate: String,
    @SerializedName("content_type")
    var contentType: String,
    @SerializedName("origin_url")
    var originUrl: String,
    @SerializedName("images")
    var imageRes: List<ImageRes>,
    @SerializedName("content")
    var content: ContentRes
) {
    fun isAllowedType(): Boolean {
        return isTypeArticle() || isTypeStoryOrGallery() || isTypeVideo()
    }

    fun isTypeVideo(): Boolean {
        return contentType == "video"
    }

    fun isTypeArticle(): Boolean {
        return contentType == "article"
    }

    fun isTypeStoryOrGallery(): Boolean {
        return contentType == "story" ||
                contentType == "gallery"
    }

    fun durationFormat(): String {
        if (isTypeVideo()) {
            var totalSecs = content.duration
            var hours = totalSecs / 3600
            var minutes = totalSecs / 60 % 60
            var seconds = totalSecs % 60

            return String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
        return ""
    }
}