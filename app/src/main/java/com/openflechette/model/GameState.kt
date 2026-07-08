package com.openflechette.model

import java.io.Serializable
import kotlin.random.Random

/**
 * État d'une partie en cours
 */
data class GameState(
    val settings: GameSettings,
    val players: List<Player> = emptyList(),
    val currentLeg: Int = 1,
    val currentPlayerIndex: Int = 0,
    val currentThrow: Int = 1,
    val isGameStarted: Boolean = false,
    val isGameFinished: Boolean = false,
    val winner: Player? = null
) : Serializable {
    val currentPlayer: Player
        get() = players[currentPlayerIndex]
    
    val remainingPoints: Int
        get() = settings.gameMode.points - currentPlayer.currentScore
    
    fun startGame(): GameState {
        val newPlayers = players.map { 
            it.copy(currentScore = 0, totalScore = 0, throwsCount = 0, doublesCount = 0, doublesSuccess = 0)
        }
        return this.copy(
            players = newPlayers,
            currentLeg = 1,
            currentPlayerIndex = 0,
            currentThrow = 1,
            isGameStarted = true,
            isGameFinished = false,
            winner = null
        )
    }
    
    fun addScore(score: Int, isDouble: Boolean = false): GameState {
        if (!isGameStarted || isGameFinished) return this
        
        val player = currentPlayer
        val newPlayer = player.copy().apply {
            addScore(score, isDouble)
        }
        
        val newPlayers = players.toMutableList().apply {
            set(currentPlayerIndex, newPlayer)
        }
        
        // Vérifier si le joueur a gagné
        val hasWon = checkWinCondition(newPlayer.currentScore, score, isDouble)
        
        if (hasWon) {
            return this.copy(
                players = newPlayers,
                isGameFinished = true,
                winner = newPlayer
            )
        }
        
        // Passer au joueur suivant ou au lancer suivant
        val nextThrow = if (currentThrow < 3) currentThrow + 1 else 1
        val nextPlayerIndex = if (nextThrow == 1) (currentPlayerIndex + 1) % players.size else currentPlayerIndex
        val nextLeg = if (nextPlayerIndex == 0 && nextThrow == 1 && currentThrow == 3) {
            currentLeg + 1
        } else {
            currentLeg
        }
        
        return this.copy(
            players = newPlayers,
            currentLeg = nextLeg,
            currentPlayerIndex = nextPlayerIndex,
            currentThrow = nextThrow
        )
    }
    
    fun undoLastScore(): GameState {
        if (!isGameStarted || isGameFinished) return this
        
        // Pour simplifier, on ne gère pas l'historique complet
        // On va juste soustraire le dernier score
        val player = currentPlayer
        
        // Trouver le dernier score... Pour l'instant, on va juste réinitialiser
        // Dans une version plus avancée, il faudrait un historique
        return this
    }
    
    private fun checkWinCondition(newScore: Int, lastScore: Int, isDouble: Boolean): Boolean {
        // Vérifier si le joueur a atteint ou dépassé le score cible
        if (newScore >= settings.gameMode.points) {
            // Pour Double Out, le dernier lancer doit être un double
            if (settings.checkOut == GameSettings.CheckOut.DOUBLE_OUT) {
                return isDouble && newScore == settings.gameMode.points
            }
            // Pour Straight Out, il faut exactement atteindre le score
            return newScore == settings.gameMode.points
        }
        return false
    }
    
    fun getBotScore(): Int {
        // Générer un score aléatoire pour un bot
        val possibleScores = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60)
        val doubles = listOf(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 40, 50, 60)
        
        // Les bots ont une certaine intelligence
        val remaining = settings.gameMode.points - currentPlayer.currentScore
        
        // Si on est proche de la fin, essayer de finir
        if (remaining <= 60) {
            // Vérifier si on peut finir avec un double
            if (settings.checkOut == GameSettings.CheckOut.DOUBLE_OUT) {
                val possibleFinish = doubles.filter { it <= remaining && (remaining - it) % 2 == 0 }
                if (possibleFinish.isNotEmpty()) {
                    return possibleFinish.random()
                }
            } else {
                // Straight out - essayer d'atteindre exactement
                if (possibleScores.contains(remaining)) {
                    return remaining
                }
            }
        }
        
        // Sinon, jouer un score aléatoire
        val score = possibleScores.random()
        val isDoubleScore = doubles.contains(score)
        
        // Vérifier que le score ne fait pas dépasser le total
        if (currentPlayer.currentScore + score > settings.gameMode.points) {
            // Trouver un score qui ne fait pas dépasser
            val safeScores = possibleScores.filter { currentPlayer.currentScore + it <= settings.gameMode.points }
            if (safeScores.isNotEmpty()) {
                return safeScores.random()
            }
            return 0
        }
        
        return score
    }
    
    fun playBotTurn(): GameState {
        if (!isGameStarted || isGameFinished) return this
        if (!currentPlayer.isBot) return this
        
        var state = this
        // Les bots jouent 3 fléchettes
        for (i in 1..3) {
            val score = state.getBotScore()
            val isDouble = listOf(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 40, 50, 60).contains(score)
            state = state.addScore(score, isDouble)
            
            // Si le bot a gagné, on arrête
            if (state.isGameFinished) break
            
            // Si on a fini les 3 fléchettes ou si le bot a gagné, on passe au joueur suivant
            if (i == 3 || state.isGameFinished) {
                break
            }
        }
        
        return state
    }
}
