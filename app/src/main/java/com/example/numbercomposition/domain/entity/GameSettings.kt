package com.example.numbercomposition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val maxSumValue: Int,
    val minNumOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
) : Parcelable {
    val minNumOfRightAnswersString: String
        get() = minNumOfRightAnswers.toString()

    val minPercentOfRightAnswersString: String
        get() = minPercentOfRightAnswers.toString()
}