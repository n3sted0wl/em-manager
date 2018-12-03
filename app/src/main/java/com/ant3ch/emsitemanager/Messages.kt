package com.ant3ch.emsitemanager

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_messages.*
import okhttp3.*
import java.io.IOException

class Messages : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        // set up the main recycler view
        recyclerView_messages.layoutManager = LinearLayoutManager(this)
        fetchMessagesData()

        fab_refreshMessages.setOnClickListener{
            fetchMessagesData()
        }
    }

    private fun fetchMessagesData() {
        println("Attempting to consume API")
        val url = "http://api.elisemauterer.com/ContactMessages/Feed"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response?.body()?.string()
                println(responseBody)

                val gson = GsonBuilder().create()
                val messageFeed = gson.fromJson(responseBody, MessageFeed::class.java)
                runOnUiThread { recyclerView_messages.adapter = MessagesAdapter(messageFeed) }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("fetchMessagesData() failed")
            }

        })
    }
}

class MessageFeed(val messages: List<CustomerMessage>)

class CustomerMessage(
    val ID: Int,
    val Message: String,
    val ContactName: String,
    val DateString: String,
    val IsRespondedTo: Boolean)