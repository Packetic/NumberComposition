package com.example.numbercomposition.domain.entity

data class GameSettings(
    val maxSumValue: Int,
    val minNumOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
)