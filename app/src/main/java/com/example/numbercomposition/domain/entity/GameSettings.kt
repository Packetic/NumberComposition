package com.example.numbercomposition.domain.entity

import java.io.Serializable

data class GameSettings(
    val maxSumValue: Int,
    val minNumOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
) : Serializable