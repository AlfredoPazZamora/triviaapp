package com.itiudc.triviaapp.activities

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.itiudc.triviaapp.R
import com.itiudc.triviaapp.adapters.AnswerListAdapter
import com.itiudc.triviaapp.databinding.ActivityMainBinding
import com.itiudc.triviaapp.models.Answer

class MainActivity : AppCompatActivity() {


    val currentAnswer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val stringResource: Resources = resources
        //Show random question to strings
        val questionText = stringResource.getStringArray(R.array.questions)[currentAnswer]

        binding.textQuestion.text = questionText.toString()

        //Show Answers
        val correctAnswerText =
            stringResource.getStringArray(R.array.correct_answers)[currentAnswer]



        var incorrectAnswerText1 = ""
        var incorrectAnswerText2 = ""
        var incorrectAnswerText3 = ""
        when (currentAnswer) {
            0 -> {
                incorrectAnswerText1 =
                    stringResource.getStringArray(R.array.incorrect_answer_question1)[0]
                incorrectAnswerText2 =
                    stringResource.getStringArray(R.array.incorrect_answer_question1)[1]
                incorrectAnswerText3 =
                    stringResource.getStringArray(R.array.incorrect_answer_question1)[2]
            }
            1 -> {
                incorrectAnswerText1 =
                    stringResource.getStringArray(R.array.incorrect_answer_question2)[0]
                incorrectAnswerText2 =
                    stringResource.getStringArray(R.array.incorrect_answer_question2)[1]
                incorrectAnswerText3 =
                    stringResource.getStringArray(R.array.incorrect_answer_question2)[2]
            }
            2 -> {
                incorrectAnswerText1 =
                    stringResource.getStringArray(R.array.incorrect_answer_question3)[0]
                incorrectAnswerText2 =
                    stringResource.getStringArray(R.array.incorrect_answer_question3)[1]
                incorrectAnswerText3 =
                    stringResource.getStringArray(R.array.incorrect_answer_question3)[2]
            }

        }

        val randomNumber = (0..2).random()

        var answersList =  mutableListOf(
            Answer(false, incorrectAnswerText1),
            Answer(false, incorrectAnswerText2),
            Answer(false, incorrectAnswerText3)
        )

        answersList.add(
            randomNumber,Answer(true, correctAnswerText)
        )


        val answerListAdapter = AnswerListAdapter(answersList)
        binding.answersList.layoutManager = LinearLayoutManager(this)
        binding.answersList.adapter = answerListAdapter

        answerListAdapter.onAnswerClick = {
            Log.i("edg", "Is correct answer: ${it.isCorrectAnswer}")
        }

    }


}