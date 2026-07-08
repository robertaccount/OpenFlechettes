package com.openflechette.view

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.openflechette.adapter.PlayerAdapter
import com.openflechette.model.GameSettings
import com.openflechette.viewmodel.GameSetupViewModel

class GameSetupActivity : AppCompatActivity() {
    private val viewModel: GameSetupViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_setup)
        
        setupViews()
        setupObservers()
    }
    
    private fun setupViews() {
        val spinnerGameMode = findViewById<Spinner>(R.id.spinnerGameMode)
        val spinnerCheckIn = findViewById<Spinner>(R.id.spinnerCheckIn)
        val spinnerCheckOut = findViewById<Spinner>(R.id.spinnerCheckOut)
        val spinnerLegs = findViewById<Spinner>(R.id.spinnerLegs)
        val editTextPlayerName = findViewById<EditText>(R.id.editTextPlayerName)
        val checkBoxIsBot = findViewById<CheckBox>(R.id.checkBoxIsBot)
        val btnAddPlayer = findViewById<Button>(R.id.btnAddPlayer)
        val btnStartGame = findViewById<Button>(R.id.btnStartGame)
        val recyclerViewPlayers = findViewById<RecyclerView>(R.id.recyclerViewPlayers)
        
        // Configuration du spinner pour le mode de jeu
        val gameModes = GameSettings.GameMode.values().map { it.displayName }
        val gameModeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, gameModes)
        gameModeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGameMode.adapter = gameModeAdapter
        
        // Configuration du spinner pour Check In
        val checkInModes = GameSettings.CheckIn.values().map { it.displayName }
        val checkInAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, checkInModes)
        checkInAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCheckIn.adapter = checkInAdapter
        
        // Configuration du spinner pour Check Out
        val checkOutModes = GameSettings.CheckOut.values().map { it.displayName }
        val checkOutAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, checkOutModes)
        checkOutAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCheckOut.adapter = checkOutAdapter
        
        // Configuration du spinner pour le nombre de manches
        val legsOptions = (1..10).map { it.toString() }
        val legsAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, legsOptions)
        legsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLegs.adapter = legsAdapter
        
        recyclerViewPlayers.layoutManager = LinearLayoutManager(this)
        
        btnAddPlayer.setOnClickListener {
            viewModel.addPlayer()
        }
        
        btnStartGame.setOnClickListener {
            if (viewModel.canStartGame()) {
                val settings = viewModel.createGameSettings()
                val intent = Intent(this, GameActivity::class.java).apply {
                    putExtra("GAME_SETTINGS", settings)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Veuillez ajouter au moins 2 joueurs", Toast.LENGTH_SHORT).show()
            }
        }
        
        // Listeners pour les spinners
        spinnerGameMode.setOnItemSelectedListener { _, _, position, _ ->
            viewModel.setGameMode(GameSettings.GameMode.values()[position])
        }
        
        spinnerCheckIn.setOnItemSelectedListener { _, _, position, _ ->
            viewModel.setCheckIn(GameSettings.CheckIn.values()[position])
        }
        
        spinnerCheckOut.setOnItemSelectedListener { _, _, position, _ ->
            viewModel.setCheckOut(GameSettings.CheckOut.values()[position])
        }
        
        spinnerLegs.setOnItemSelectedListener { _, _, position, _ ->
            viewModel.setLegs(position + 1)
        }
        
        editTextPlayerName.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                viewModel.setPlayerName(s?.toString() ?: "")
            }
        })
        
        checkBoxIsBot.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setIsBot(isChecked)
        }
    }
    
    private fun setupObservers() {
        viewModel.players.observe(this) { players ->
            val adapter = PlayerAdapter(players, { player ->
                viewModel.removePlayer(player)
            }, { player, isChecked ->
                // Gérer le changement de type de joueur
            })
            findViewById<RecyclerView>(R.id.recyclerViewPlayers).adapter = adapter
        }
        
        viewModel.playerName.observe(this) { name ->
            findViewById<EditText>(R.id.editTextPlayerName).setText(name)
        }
        
        viewModel.isBot.observe(this) { isBot ->
            findViewById<CheckBox>(R.id.checkBoxIsBot).isChecked = isBot
        }
    }
}
