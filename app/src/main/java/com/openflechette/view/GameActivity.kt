package com.openflechette.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.openflechette.R
import com.openflechette.adapter.PlayerScoreAdapter
import com.openflechette.model.GameSettings
import com.openflechette.viewmodel.GameViewModel

class GameActivity : AppCompatActivity() {
    private val viewModel: GameViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        
        // Récupérer les paramètres de la partie
        val settings = intent.getSerializableExtra("GAME_SETTINGS") as? GameSettings
        if (settings != null) {
            viewModel.initializeGame(settings)
        } else {
            // Par défaut
            Toast.makeText(this, "Erreur: paramètres de partie non trouvés", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        setupViews()
        setupObservers()
    }
    
    private fun setupViews() {
        val btnScore1 = findViewById<Button>(R.id.btnScore1)
        val btnScore2 = findViewById<Button>(R.id.btnScore2)
        val btnScore3 = findViewById<Button>(R.id.btnScore3)
        val btnScore4 = findViewById<Button>(R.id.btnScore4)
        val btnScore5 = findViewById<Button>(R.id.btnScore5)
        val btnScore6 = findViewById<Button>(R.id.btnScore6)
        val btnScore7 = findViewById<Button>(R.id.btnScore7)
        val btnScore8 = findViewById<Button>(R.id.btnScore8)
        val btnScore9 = findViewById<Button>(R.id.btnScore9)
        val btnScore10 = findViewById<Button>(R.id.btnScore10)
        val btnScore11 = findViewById<Button>(R.id.btnScore11)
        val btnScore12 = findViewById<Button>(R.id.btnScore12)
        val btnScore13 = findViewById<Button>(R.id.btnScore13)
        val btnScore14 = findViewById<Button>(R.id.btnScore14)
        val btnScore15 = findViewById<Button>(R.id.btnScore15)
        val btnScore16 = findViewById<Button>(R.id.btnScore16)
        val btnScore17 = findViewById<Button>(R.id.btnScore17)
        val btnScore18 = findViewById<Button>(R.id.btnScore18)
        val btnScore19 = findViewById<Button>(R.id.btnScore19)
        val btnScore20 = findViewById<Button>(R.id.btnScore20)
        val btnScore21 = findViewById<Button>(R.id.btnScore21)
        val btnScore22 = findViewById<Button>(R.id.btnScore22)
        val btnScore23 = findViewById<Button>(R.id.btnScore23)
        val btnScore24 = findViewById<Button>(R.id.btnScore24)
        val btnScore25 = findViewById<Button>(R.id.btnScore25)
        val btnScore26 = findViewById<Button>(R.id.btnScore26)
        val btnScore27 = findViewById<Button>(R.id.btnScore27)
        val btnScore28 = findViewById<Button>(R.id.btnScore28)
        val btnScore29 = findViewById<Button>(R.id.btnScore29)
        val btnScore30 = findViewById<Button>(R.id.btnScore30)
        val btnScore31 = findViewById<Button>(R.id.btnScore31)
        val btnScore32 = findViewById<Button>(R.id.btnScore32)
        val btnScore33 = findViewById<Button>(R.id.btnScore33)
        val btnScore34 = findViewById<Button>(R.id.btnScore34)
        val btnScore35 = findViewById<Button>(R.id.btnScore35)
        val btnScore36 = findViewById<Button>(R.id.btnScore36)
        val btnScore37 = findViewById<Button>(R.id.btnScore37)
        val btnScore38 = findViewById<Button>(R.id.btnScore38)
        val btnScore39 = findViewById<Button>(R.id.btnScore39)
        val btnScore40 = findViewById<Button>(R.id.btnScore40)
        val btnScore41 = findViewById<Button>(R.id.btnScore41)
        val btnScore42 = findViewById<Button>(R.id.btnScore42)
        val btnScore43 = findViewById<Button>(R.id.btnScore43)
        val btnScore44 = findViewById<Button>(R.id.btnScore44)
        val btnScore45 = findViewById<Button>(R.id.btnScore45)
        val btnScore46 = findViewById<Button>(R.id.btnScore46)
        val btnScore47 = findViewById<Button>(R.id.btnScore47)
        val btnScore48 = findViewById<Button>(R.id.btnScore48)
        val btnScore49 = findViewById<Button>(R.id.btnScore49)
        val btnScore50 = findViewById<Button>(R.id.btnScore50)
        val btnScore51 = findViewById<Button>(R.id.btnScore51)
        val btnScore52 = findViewById<Button>(R.id.btnScore52)
        val btnScore53 = findViewById<Button>(R.id.btnScore53)
        val btnScore54 = findViewById<Button>(R.id.btnScore54)
        val btnScore55 = findViewById<Button>(R.id.btnScore55)
        val btnScore56 = findViewById<Button>(R.id.btnScore56)
        val btnScore57 = findViewById<Button>(R.id.btnScore57)
        val btnScore58 = findViewById<Button>(R.id.btnScore58)
        val btnScore59 = findViewById<Button>(R.id.btnScore59)
        val btnScore60 = findViewById<Button>(R.id.btnScore60)
        val btnMiss = findViewById<Button>(R.id.btnMiss)
        val btnStartGame = findViewById<Button>(R.id.btnStartGame)
        val btnUndo = findViewById<Button>(R.id.btnUndo)
        val btnEndGame = findViewById<Button>(R.id.btnEndGame)
        val recyclerViewPlayers = findViewById<RecyclerView>(R.id.recyclerViewPlayers)
        
        recyclerViewPlayers.layoutManager = LinearLayoutManager(this)
        
        // Configuration des boutons de score
        val scoreButtons = mapOf(
            btnScore1 to 1,
            btnScore2 to 2,
            btnScore3 to 3,
            btnScore4 to 4,
            btnScore5 to 5,
            btnScore6 to 6,
            btnScore7 to 7,
            btnScore8 to 8,
            btnScore9 to 9,
            btnScore10 to 10,
            btnScore11 to 11,
            btnScore12 to 12,
            btnScore13 to 13,
            btnScore14 to 14,
            btnScore15 to 15,
            btnScore16 to 16,
            btnScore17 to 17,
            btnScore18 to 18,
            btnScore19 to 19,
            btnScore20 to 20,
            btnScore21 to 21,
            btnScore22 to 22,
            btnScore23 to 23,
            btnScore24 to 24,
            btnScore25 to 25,
            btnScore26 to 26,
            btnScore27 to 27,
            btnScore28 to 28,
            btnScore29 to 29,
            btnScore30 to 30,
            btnScore31 to 31,
            btnScore32 to 32,
            btnScore33 to 33,
            btnScore34 to 34,
            btnScore35 to 35,
            btnScore36 to 36,
            btnScore37 to 37,
            btnScore38 to 38,
            btnScore39 to 39,
            btnScore40 to 40,
            btnScore41 to 41,
            btnScore42 to 42,
            btnScore43 to 43,
            btnScore44 to 44,
            btnScore45 to 45,
            btnScore46 to 46,
            btnScore47 to 47,
            btnScore48 to 48,
            btnScore49 to 49,
            btnScore50 to 50,
            btnScore51 to 51,
            btnScore52 to 52,
            btnScore53 to 53,
            btnScore54 to 54,
            btnScore55 to 55,
            btnScore56 to 56,
            btnScore57 to 57,
            btnScore58 to 58,
            btnScore59 to 59,
            btnScore60 to 60
        )
        
        // Les doubles sont: 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 40, 50, 60
        val doubles = setOf(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 40, 50, 60)
        
        scoreButtons.forEach { (button, score) ->
            button.setOnClickListener {
                addScore(score, doubles.contains(score))
            }
        }
        
        btnMiss.setOnClickListener { addScore(0) }
        
        btnStartGame.setOnClickListener {
            viewModel.startGame()
        }
        
        btnUndo.setOnClickListener {
            viewModel.undoLastScore()
        }
        
        btnEndGame.setOnClickListener {
            // Terminer la partie et afficher les résultats
            val intent = Intent(this, GameResultsActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun addScore(score: Int, isDouble: Boolean = false) {
        if (!viewModel.isGameStarted.value!!) {
            Toast.makeText(this, "Veuillez d'abord démarrer la partie", Toast.LENGTH_SHORT).show()
            return
        }
        
        val currentPlayer = viewModel.currentPlayer.value
        if (currentPlayer?.isBot == true) {
            // C'est au tour d'un bot, on ne peut pas ajouter de score manuellement
            Toast.makeText(this, "C'est au tour de ${currentPlayer.name} (bot)", Toast.LENGTH_SHORT).show()
            return
        }
        
        viewModel.addScore(score, isDouble)
        
        // Si c'est un bot, jouer son tour après
        if (viewModel.isGameFinished.value == false) {
            val nextPlayerIndex = viewModel.currentPlayerIndex.value ?: 0
            val players = viewModel.players.value ?: emptyList()
            val nextPlayer = if (nextPlayerIndex < players.size) players[nextPlayerIndex] else null
            if (nextPlayer?.isBot == true) {
                // Attendre un peu avant de jouer le tour du bot
                findViewById<android.view.View>(android.R.id.content).postDelayed({
                    viewModel.playBotTurn()
                }, 1000)
            }
        }
    }
    
    private fun setupObservers() {
        viewModel.isGameStarted.observe(this) { isStarted ->
            findViewById<Button>(R.id.btnStartGame).isEnabled = !isStarted
            findViewById<Button>(R.id.btnStartGame).text = if (isStarted) "Partie en cours" else "Démarrer"
        }
        
        viewModel.isGameFinished.observe(this) { isFinished ->
            if (isFinished) {
                val intent = Intent(this, GameResultsActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        
        viewModel.currentPlayer.observe(this) { player ->
            player?.let {
                findViewById<TextView>(R.id.textViewCurrentPlayer).text = "Joueur: ${it.name}${if (it.isBot) " (Bot)" else ""}"
            }
        }
        
        viewModel.remainingPoints.observe(this) { remaining ->
            findViewById<TextView>(R.id.textViewRemainingPoints).text = "Points restants: $remaining"
        }
        
        viewModel.currentThrow.observe(this) { throwNum ->
            findViewById<TextView>(R.id.textViewCurrentThrow).text = "Lancer: $throwNum/3"
        }
        
        viewModel.players.observe(this) { players ->
            val adapter = PlayerScoreAdapter(players) { player, score ->
                // Gérer la modification du score
            }
            findViewById<RecyclerView>(R.id.recyclerViewPlayers).adapter = adapter
            adapter.currentPlayer = viewModel.currentPlayer.value
        }
        
        viewModel.winner.observe(this) { winner ->
            winner?.let {
                Toast.makeText(this, "${it.name} a gagné la partie!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
