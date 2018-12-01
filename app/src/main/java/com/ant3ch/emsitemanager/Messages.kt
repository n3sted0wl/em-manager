package com.ant3ch.emsitemanager

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_messages.*

class Messages : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fabRefreshMessages.setOnClickListener {
            etUserMessage.setText("Refreshed message")
            Toast.makeText(this, "Messages refreshed", Toast.LENGTH_SHORT).show()
        }
    }

}
