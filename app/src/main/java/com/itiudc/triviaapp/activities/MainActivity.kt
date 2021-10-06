package com.itiudc.triviaapp.activities

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.itiudc.triviaapp.R
import com.itiudc.triviaapp.adapters.AnswerListAdapter
import com.itiudc.triviaapp.databinding.ActivityMainBinding
import com.itiudc.triviaapp.models.Answer
import com.itiudc.triviaapp.viewmodels.AnswerViewModel

class MainActivity : AppCompatActivity() {


    private var currentAnswer: Int = 0
    var counterCorrect: Int = 0
    private var counterIncorrect: Int = 0
    lateinit var name: String

    private lateinit var viewModel: AnswerViewModel
    private lateinit var answersList: MutableList<Answer>
    private lateinit var answerListAdapter: AnswerListAdapter

    companion object{
        const val NAME_KEY = "player_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val stringResource: Resources = resources
        viewModel = ViewModelProvider(this).get(AnswerViewModel::class.java)

        val bundle: Bundle? = intent.extras

        if (bundle != null){
            val nameToLogin = bundle.getString(NAME_KEY)
            binding.playerName.text = nameToLogin

        }

        showQuestion(stringResource, binding)
        updateProgress(binding)


    }


    private fun updateProgress(binding: ActivityMainBinding) {

        binding.infoProgress.text = "${currentAnswer + 1} /3"
        binding.numberQuestion.text = "${currentAnswer + 1}"
    }

    fun showQuestion(stringResource: Resources, binding: ActivityMainBinding) {
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

        answersList =  mutableListOf(
            Answer(false, incorrectAnswerText1),
            Answer(false, incorrectAnswerText2),
            Answer(false, incorrectAnswerText3)
        )

        answersList.add(
            randomNumber,Answer(true, correctAnswerText)
        )

        answerListAdapter = AnswerListAdapter(answersList)
        binding.answersList.layoutManager = LinearLayoutManager(this)
        binding.answersList.adapter = answerListAdapter

        answerListAdapter.onAnswerClick = {
            Log.i("edg", "Is correct answer: ${it.isCorrectAnswer}")
            if(it.isCorrectAnswer){
                counterCorrect++
            }else{
                counterIncorrect++
            }

            if (currentAnswer < 2){
                currentAnswer++
            }else{
                val scoreText = stringResource.getString(R.string.result_score, counterCorrect.toString())
                val nameText = binding.playerName.text
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra(ResultActivity.SCORE_KEY, scoreText)
                    putExtra(ResultActivity.NAME_KEY_RESULT, nameText)

                }
                startActivity(intent)
                Log.i("edg", "Name: $name")
            }
            showQuestion(stringResource, binding)
            updateProgress(binding)
        }

        for(value in answersList){
            Log.i("edg", "respuestas ${value.answerText}")
        }

    }
}