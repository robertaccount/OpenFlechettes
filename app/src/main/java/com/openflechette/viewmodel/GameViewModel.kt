package com.openflechette.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openflechette.model.GameSettings
import com.openflechette.model.GameState
import com.openflechette.model.Player

class GameViewModel : ViewModel() {
    private val _gameState = MutableLiveData<GameState?>(null)
    val gameState: LiveData<GameState?> = _gameState
    
    private val _gameSettings = MutableLiveData<GameSettings?>(null)
    val gameSettings: LiveData<GameSettings?> = _gameSettings
    
    private val _players = MutableLiveData<List<Player>>(emptyList())
    val players: LiveData<List<Player>> = _players
    
    private val _currentPlayer = MutableLiveData<Player?>(null)
    val currentPlayer: LiveData<Player?> = _currentPlayer
    
    private val _currentPlayerIndex = MutableLiveData<Int>(0)
    val currentPlayerIndex: LiveData<Int> = _currentPlayerIndex
    
    private val _remainingPoints = MutableLiveData<Int>(0)
    val remainingPoints: LiveData<Int> = _remainingPoints
    
    private val _currentThrow = MutableLiveData<Int>(1)
    val currentThrow: LiveData<Int> = _currentThrow
    
    private val _isGameStarted = MutableLiveData<Boolean>(false)
    val isGameStarted: LiveData<Boolean> = _isGameStarted
    
    private val _isGameFinished = MutableLiveData<Boolean>(false)
    val isGameFinished: LiveData<Boolean> = _isGameFinished
    
    private val _winner = MutableLiveData<Player?>(null)
    val winner: LiveData<Player?> = _winner
    
    fun initializeGame(settings: GameSettings) {
        _gameSettings.value = settings
        _players.value = settings.players
        _gameState.value = GameState(settings, settings.players.map { it.copy() })
        updateDerivedValues()
    }
    
    fun startGame() {
        _gameState.value?.let { state ->
            _gameState.value = state.startGame()
            updateDerivedValues()
        }
    }
    
    fun addScore(score: Int, isDouble: Boolean = false) {
        _gameState.value?.let { state ->
            _gameState.value = state.addScore(score, isDouble)
            updateDerivedValues()
        }
    }
    
    fun playBotTurn() {
        _gameState.value?.let { state ->
            _gameState.value = state.playBotTurn()
            updateDerivedValues()
        }
    }
    
    fun undoLastScore() {
        _gameState.value?.let { state ->
            _gameState.value = state.undoLastScore()
            updateDerivedValues()
        }
    }
    
    private fun updateDerivedValues() {
        _gameState.value?.let { state ->
            _players.value = state.players
            _currentPlayer.value = state.currentPlayer
            _currentPlayerIndex.value = state.currentPlayerIndex
            _remainingPoints.value = state.remainingPoints
            _currentThrow.value = state.currentThrow
            _isGameStarted.value = state.isGameStarted
            _isGameFinished.value = state.isGameFinished
            _winner.value = state.winner
        }
    }
    
    fun getPlayerStats(): List<PlayerStats> {
        return _players.value?.map { player ->
            PlayerStats(
                player.name,
                player.averageScore,
                player.doublesAccuracy,
                player.totalScore,
                player.throwsCount,
                player.doublesSuccess,
                player.doublesCount
            )
        } ?: emptyList()
    }
}

data class PlayerStats(
    val name: String,
    val averageScore: Double,
    val doublesAccuracy: Double,
    val totalScore: Int,
    val throwsCount: Int,
    val doublesSuccess: Int,
    val doublesCount: Int
)
