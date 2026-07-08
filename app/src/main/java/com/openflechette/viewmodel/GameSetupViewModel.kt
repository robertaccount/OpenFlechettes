package com.openflechette.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openflechette.model.GameSettings
import com.openflechette.model.Player

class GameSetupViewModel : ViewModel() {
    private val _gameMode = MutableLiveData<GameSettings.GameMode>(GameSettings.GameMode.POINTS_501)
    val gameMode: LiveData<GameSettings.GameMode> = _gameMode
    
    private val _checkIn = MutableLiveData<GameSettings.CheckIn>(GameSettings.CheckIn.STRAIGHT_IN)
    val checkIn: LiveData<GameSettings.CheckIn> = _checkIn
    
    private val _checkOut = MutableLiveData<GameSettings.CheckOut>(GameSettings.CheckOut.DOUBLE_OUT)
    val checkOut: LiveData<GameSettings.CheckOut> = _checkOut
    
    private val _legs = MutableLiveData<Int>(1)
    val legs: LiveData<Int> = _legs
    
    private val _players = MutableLiveData<MutableList<Player>>(mutableListOf())
    val players: LiveData<List<Player>> = _players
    
    private val _playerName = MutableLiveData<String>("")
    val playerName: LiveData<String> = _playerName
    
    private val _isBot = MutableLiveData<Boolean>(false)
    val isBot: LiveData<Boolean> = _isBot
    
    fun setGameMode(mode: GameSettings.GameMode) {
        _gameMode.value = mode
    }
    
    fun setCheckIn(checkIn: GameSettings.CheckIn) {
        _checkIn.value = checkIn
    }
    
    fun setCheckOut(checkOut: GameSettings.CheckOut) {
        _checkOut.value = checkOut
    }
    
    fun setLegs(legs: Int) {
        _legs.value = legs
    }
    
    fun setPlayerName(name: String) {
        _playerName.value = name
    }
    
    fun setIsBot(isBot: Boolean) {
        _isBot.value = isBot
    }
    
    fun addPlayer() {
        val name = _playerName.value?.trim()
        if (name.isNullOrEmpty()) return
        
        val isBot = _isBot.value ?: false
        val newPlayer = Player(
            id = System.currentTimeMillis().toString(),
            name = name,
            isBot = isBot
        )
        
        val currentPlayers = _players.value ?: mutableListOf()
        currentPlayers.add(newPlayer)
        _players.value = currentPlayers
        
        // Réinitialiser pour le prochain joueur
        _playerName.value = ""
        _isBot.value = false
    }
    
    fun removePlayer(player: Player) {
        val currentPlayers = _players.value ?: mutableListOf()
        currentPlayers.remove(player)
        _players.value = currentPlayers
    }
    
    fun createGameSettings(): GameSettings {
        return GameSettings(
            gameMode = _gameMode.value ?: GameSettings.GameMode.POINTS_501,
            checkIn = _checkIn.value ?: GameSettings.CheckIn.STRAIGHT_IN,
            checkOut = _checkOut.value ?: GameSettings.CheckOut.DOUBLE_OUT,
            legs = _legs.value ?: 1,
            players = _players.value ?: emptyList()
        )
    }
    
    fun canStartGame(): Boolean {
        return (_players.value?.size ?: 0) >= 2
    }
}
