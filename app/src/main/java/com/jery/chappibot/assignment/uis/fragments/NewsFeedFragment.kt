package com.jery.chappibot.assignment.uis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jery.chappibot.assignment.R
import com.jery.chappibot.assignment.uis.adapter.NewsFeedAdapter
import com.jery.chappibot.assignment.viewmodels.NewsFeedViewModel
import kotlinx.android.synthetic.main.fragment_newsfeed.*
import kotlinx.android.synthetic.main.fragment_newsfeed.view.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_error.view.*

class NewsFeedFragment: Fragment() {

    lateinit var viewModel: NewsFeedViewModel
    lateinit var feedAdapter: NewsFeedAdapter

    companion object {
        fun newInstance() : NewsFeedFragment {
            return NewsFeedFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_newsfeed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading()
        viewModel = ViewModelProviders.of(this).get(NewsFeedViewModel::class.java)

        feedAdapter = NewsFeedAdapter(context!!)

        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = feedAdapter

        viewModel.newsfeed.observe(this, Observer {
            showData()
            feedAdapter.updateFeeds(it)
        })

        viewModel.errorMsg.observe(this, Observer {
            showError(it)
        })
    }

    private fun loadFeeds() {
        showLoading()
        viewModel.loadFeeds()
    }

    private fun showLoading() {
        layout_loading.visibility = View.VISIBLE
        layout_error.visibility = View.GONE
        rv.visibility = View.GONE
    }

    private fun showData() {
        rv.visibility = View.VISIBLE
        layout_error.visibility = View.GONE
        layout_loading.visibility = View.GONE
    }

    private fun showError(msg: String) {
        layout_error.visibility = View.VISIBLE
        layout_loading.visibility = View.GONE
        rv.visibility = View.GONE

        layout_error.txt_error.text = msg

        btn_retry.setOnClickListener{
            loadFeeds()
        }
    }
}