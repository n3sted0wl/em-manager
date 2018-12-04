package com.ant3ch.emsitemanager

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.message_row.view.*
import okhttp3.*
import java.io.IOException

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
        holder?.view?.tv_MessageContent?.text = messageToBind.Message
        holder?.view?.tv_ReceivedDate?.text = messageToBind.DateString
        holder?.view?.swt_IsRespondedTo?.isChecked = messageToBind.IsRespondedTo

        // bind events
        holder?.view?.swt_IsRespondedTo?.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked) {
                markMessageRespondedTo(messageToBind.ID.toString())
            } else {
                markMessageNotRespondedTo(messageToBind.ID.toString())
            }
        }
    }

    private fun markMessageRespondedTo(id: String) {
        println("Attempting to mark a message as responded to through the API")
        val url = "http://api.elisemauterer.com/ContactMessages/MarkAsResponded/" + id
        val client = OkHttpClient()
        var postBody = FormBody.Builder().add("unnecessary", "unnecessary").build()
        val request = Request.Builder().url(url).method("POST", postBody).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                println("markMessageRespondedTo() succeeded")
            }

            override fun onFailure(call: Call, e: IOException) {
                println("markMessageRespondedTo() failed")
            }

        })
    }

    private fun markMessageNotRespondedTo(id: String) {
        println("Attempting to mark a message as not responded to through the API")
        val url = "http://api.elisemauterer.com/ContactMessages/MarkAsNotResponded/" + id
        val client = OkHttpClient()
        var postBody = FormBody.Builder().add("unnecessary", "unnecessary").build()
        val request = Request.Builder().url(url).method("POST", postBody).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                println("markMessageNotRespondedTo() succeeded")
            }

            override fun onFailure(call: Call, e: IOException) {
                println("markMessageNotRespondedTo() failed")
            }
        })
    }

}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {}