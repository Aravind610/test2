package com.aravind.aravind_systemtest2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.aravind.aravind_systemtest2.Data.UserDataBase

class LoginActivity: AppCompatActivity() {

    var editTextEmail: EditText? = null
    var editTextPassword: EditText? = null
    var buttonLogin: Button? = null
    var textViewRegister: TextView? = null
    var db: UserDao? = null
    var dataBase: UserDataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        textViewRegister = findViewById(R.id.textViewRegister)
        dataBase = Room.databaseBuilder(this, UserDataBase::class.java, "mi-database.db")
                .allowMainThreadQueries()
                .build()
        db = dataBase!!.userDao
        textViewRegister?.setOnClickListener(View.OnClickListener { startActivity(Intent(this@LoginActivity, RegisterActivity::class.java)) })
        buttonLogin?.setOnClickListener(View.OnClickListener {
            val email = editTextEmail?.getText().toString().trim { it <= ' ' }
            val password = editTextPassword?.getText().toString().trim { it <= ' ' }
            val user = db!!.getUser(email, password)
            if (user != null) {
                val i = Intent(this@LoginActivity, MainActivity::class.java)
                i.putExtra("User", user)
                startActivity(i)
                finish()
            } else {
                Toast.makeText(this@LoginActivity, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show()
            }
        })
    }
}