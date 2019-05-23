package com.jery.mvvm.example.uis.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jery.mvvm.example.R
import com.jery.mvvm.example.configs.ImageLoader
import com.jery.mvvm.example.models.res.FeedInfoRes
import kotlinx.android.synthetic.main.feed_article_item.view.*
import kotlinx.android.synthetic.main.feed_article_item.view.txt_date
import kotlinx.android.synthetic.main.feed_article_item.view.txt_title
import kotlinx.android.synthetic.main.feed_story_gallery_item.view.*
import kotlinx.android.synthetic.main.feed_video_item.view.*
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri

class NewsFeedAdapter(private var context: Context): RecyclerView.Adapter<NewsFeedAdapter.FeedBaseHolder>() {
    var feeds: List<FeedInfoRes> = ArrayList()
    private var inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedBaseHolder {
        if(viewType == FeedBaseHolder.FEED_TYPE_VIDEO) {
            return FeedVideoHolder(inflater.inflate(R.layout.feed_video_item, parent, false))
        }
        if(viewType == FeedBaseHolder.FEED_STORY_GALLERY) {
            return FeedGalleryStoryHolder(inflater.inflate(R.layout.feed_story_gallery_item, parent, false))
        }
        return FeedArticleHolder(inflater.inflate(R.layout.feed_article_item, parent, false))
    }

    fun updateFeeds(feeds: List<FeedInfoRes>) {
        this.feeds = feeds
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val feed = feeds[position]
        if(feed.isTypeVideo()) {
            return FeedBaseHolder.FEED_TYPE_VIDEO
        }
        if(feed.isTypeStoryOrGallery()) {
            return FeedBaseHolder.FEED_STORY_GALLERY
        }
        return FeedBaseHolder.FEED_TYPE_ARTICLE
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    override fun onBindViewHolder(holder: FeedBaseHolder, position: Int) {
        holder.bindFeed(feed = feeds[position])
    }

    class FeedGalleryStoryHolder(itemView: View): FeedBaseHolder(itemView) {
        override fun bindFeed(feed: FeedInfoRes) {

            // thumbnails
            var layoutThumbs = itemView.layout_thumbs
            if (feed.imageRes == null || feed.imageRes.isEmpty()) {
                layoutThumbs.visibility = View.GONE
            } else {
                layoutThumbs.visibility = View.VISIBLE

                // load image from url
                for (i in 0 until feed.imageRes.size) {
                    if(i >= layoutThumbs.childCount) {
                        break
                    }

                    layoutThumbs.getChildAt(i).visibility = View.VISIBLE
                    ImageLoader.loadImage(feed.imageRes[i].href, itemView.context,
                        layoutThumbs.getChildAt(i) as ImageView
                    )
                }
                // hide image view if images size less than image views
                if(feed.imageRes.size < layoutThumbs.childCount) {
                    for (i in feed.imageRes.size until layoutThumbs.childCount) {
                        layoutThumbs.getChildAt(i).visibility = View.GONE
                    }
                }
            }
            // end thumbnails

            itemView.txt_title.text = feed.title
            itemView.txt_date.text = feed.pubDate

            itemView.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(feed.originUrl))
                startActivity(itemView.context, browserIntent, null)
            }
        }
    }

    class FeedVideoHolder(itemView: View): FeedBaseHolder(itemView) {
        override fun bindFeed(feed: FeedInfoRes) {
            ImageLoader.loadImage(feed.content.previewImage.href, itemView.context, itemView.img_video_preview)
            itemView.txt_date.text = feed.pubDate
            itemView.txt_duration.text = feed.durationFormat()

            itemView.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(feed.originUrl))
                startActivity(itemView.context, browserIntent, null)
            }
        }
    }

    class FeedArticleHolder(itemView: View): FeedBaseHolder(itemView) {
        override fun bindFeed(feed: FeedInfoRes) {
            itemView.txt_title.text = feed.title
            itemView.txt_description.text = feed.description
            itemView.txt_date.text = feed.pubDate

            itemView.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(feed.originUrl))
                startActivity(itemView.context, browserIntent, null)
            }
        }
    }

    abstract class FeedBaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            const val FEED_TYPE_VIDEO = 0
            const val FEED_TYPE_ARTICLE = 1
            const val FEED_STORY_GALLERY = 2
        }
        init {
        }

        abstract fun bindFeed(feed: FeedInfoRes)
    }
}