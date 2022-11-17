package com.example.numbercomposition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val winner: Boolean,
    val numOfRightAnswers: Int,
    val numOfQuestions: Int,
    val gameSettings: GameSettings
) : Parcelable