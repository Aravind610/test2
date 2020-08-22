package com.aravind.aravind_systemtest2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.elgindy.loginandregisterwithroom.group_activity

class MainActivity : AppCompatActivity() {
    var btnindividual: Button? = null
    var btngroup: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initlizeview()
    }

    fun initlizeview() {
        btnindividual = findViewById(R.id.individual)
        btngroup = findViewById(R.id.group)
        btnindividual?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, individual_activity::class.java)
            startActivity(intent)
        })
        btngroup?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, group_activity::class.java)
            startActivity(intent)
        })
    }
}