package com.example.numbercomposition.domain.entity

data class GameResult(
    val winner: Boolean,
    val numOfRightAnswers: Int,
    val numOfQuestions: Int,
    val gameSettings: GameSettings
)