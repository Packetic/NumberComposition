package com.example.numbercomposition.domain.entity

import java.io.Serializable

data class GameResult(
    val winner: Boolean,
    val numOfRightAnswers: Int,
    val numOfQuestions: Int,
    val gameSettings: GameSettings
) : Serializable