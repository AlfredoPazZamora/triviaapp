package com.itiudc.triviaapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.itiudc.triviaapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var inputName = findViewById<EditText>(R.id.input_name)
        var buttonStart = findViewById<Button>(R.id.button_start)

        buttonStart.setOnClickListener(){
            when{
                inputName.text.isEmpty() ->
                    Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show()

                else -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}