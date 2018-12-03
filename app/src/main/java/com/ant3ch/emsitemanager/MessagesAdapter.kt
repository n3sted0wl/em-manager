package com.ant3ch.emsitemanager

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.message_row.view.*

class MessagesAdapter(val messageFeed : MessageFeed) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {
        return messageFeed.messages.count()
    }

    // This function should return the view for each item in the list inside a view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflator = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflator.inflate(R.layout.message_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val messageToBind = messageFeed.messages.get(position)
        holder?.view?.tv_SenderName?.text = messageToBind.ContactName
    }

}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {}