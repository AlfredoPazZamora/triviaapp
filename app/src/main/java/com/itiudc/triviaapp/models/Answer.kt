package com.itiudc.triviaapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Answer(
    val isCorrectAnswer: Boolean,
    val answerText: String
) : Parcelable {
}