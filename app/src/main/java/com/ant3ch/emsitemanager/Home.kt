package com.ant3ch.emsitemanager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnGoToMessages.setOnClickListener {
            val intentToGoToMessages = Intent(this, Messages::class.java)
            startActivity(intentToGoToMessages)
        }
    }
}
