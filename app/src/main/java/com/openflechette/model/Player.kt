package com.openflechette.model

import java.io.Serializable

/**
 * Représente un joueur (humain ou bot)
 */
data class Player(
    val id: String,
    val name: String,
    val isBot: Boolean = false,
    var currentScore: Int = 0,
    var totalScore: Int = 0,
    var throwsCount: Int = 0,
    var doublesCount: Int = 0,
    var doublesSuccess: Int = 0
) : Serializable {
    // Calcul de la moyenne de score par lancer
    val averageScore: Double
        get() = if (throwsCount > 0) totalScore.toDouble() / throwsCount else 0.0
    
    // Calcul du pourcentage de réussite des doubles
    val doublesAccuracy: Double
        get() = if (doublesCount > 0) (doublesSuccess.toDouble() / doublesCount) * 100 else 0.0
    
    fun resetGameStats() {
        currentScore = 0
        totalScore = 0
        throwsCount = 0
        doublesCount = 0
        doublesSuccess = 0
    }
    
    fun addScore(score: Int, isDouble: Boolean = false) {
        currentScore += score
        totalScore += score
        throwsCount++
        if (isDouble) {
            doublesCount++
            // On considère qu'un double est réussi si le score est pair et > 0
            if (score > 0 && score % 2 == 0) {
                doublesSuccess++
            }
        }
    }
    
    fun subtractScore(score: Int) {
        currentScore -= score
        totalScore -= score
        if (throwsCount > 0) throwsCount--
    }
}
