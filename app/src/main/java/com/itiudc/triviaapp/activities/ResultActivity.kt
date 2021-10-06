package com.itiudc.triviaapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.itiudc.triviaapp.R
import com.itiudc.triviaapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    companion object{
        const val NAME_KEY_RESULT = "player_name"
        const val SCORE_KEY = "score"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val binding = DataBindingUtil.setContentView<ActivityResultBinding>(this, R.layout.activity_result)
        val bundle: Bundle? = intent.extras

        if (bundle != null){
            val name = bundle.getString(NAME_KEY_RESULT)
            val score = bundle.getString(SCORE_KEY)


            binding.playerName.text = name.toString()
            binding.score.text = score.toString()
        }

        binding.playAgain.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}