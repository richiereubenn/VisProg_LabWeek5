package com.example.visprog_labweek5.UIState

data class Game(
    val score: Int = 0,
    val chance: Int = 3,
    val gameOver: Boolean = false,
    val random: Int = 0
)
