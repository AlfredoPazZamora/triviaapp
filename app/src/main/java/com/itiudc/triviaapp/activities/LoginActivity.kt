package com.itiudc.triviaapp.activities

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.itiudc.triviaapp.R
import com.itiudc.triviaapp.databinding.ActivityLoginBinding
import com.itiudc.triviaapp.databinding.ActivityResultBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        val stringResource: Resources = resources

        val inputName = binding.inputName
        val buttonStart = binding.buttonStart

        val nameText = stringResource.getString(R.string.player_name, inputName.toString())

        buttonStart.setOnClickListener(){
            Log.i("edg", "Name: $nameText")
            when{
                inputName.text.isEmpty() ->
                    Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show()

                else -> {
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(MainActivity.NAME_KEY, inputName.text.toString())
                    }

                    startActivity(intent)

                }
            }
        }
    }
}