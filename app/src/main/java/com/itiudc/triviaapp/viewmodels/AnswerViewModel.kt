package com.itiudc.triviaapp.viewmodels

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itiudc.triviaapp.R
import com.itiudc.triviaapp.models.Answer

class AnswerViewModel : ViewModel(){

    private val _answers: MutableLiveData<Int> = MutableLiveData()
    val answers: LiveData<Int>
        get() = _answers

    private val _counterIncorrectAnswers: MutableLiveData<Int> = MutableLiveData()
    val counterIncorrectAnswers: LiveData<Int>
        get() = _counterIncorrectAnswers

    private fun getAnswers(stringResource: Resources, currentAnswer: Int){
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

        val answersList =  mutableListOf(
            Answer(false, incorrectAnswerText1),
            Answer(false, incorrectAnswerText2),
            Answer(false, incorrectAnswerText3)
        )

        answersList.add(
            randomNumber, Answer(true, correctAnswerText)
        )
    }
}