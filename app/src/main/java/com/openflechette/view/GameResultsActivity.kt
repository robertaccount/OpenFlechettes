package com.openflechette.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.openflechette.adapter.ResultsAdapter
import com.openflechette.viewmodel.GameViewModel

class GameResultsActivity : AppCompatActivity() {
    private val viewModel: GameViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_results)
        
        val btnNewGame = findViewById<Button>(R.id.btnNewGame)
        val btnMainMenu = findViewById<Button>(R.id.btnMainMenu)
        val recyclerViewResults = findViewById<RecyclerView>(R.id.recyclerViewResults)
        val textViewWinner = findViewById<TextView>(R.id.textViewWinner)
        
        recyclerViewResults.layoutManager = LinearLayoutManager(this)
        
        btnNewGame.setOnClickListener {
            finish()
        }
        
        btnMainMenu.setOnClickListener {
            finishAffinity()
        }
        
        setupObservers()
    }
    
    private fun setupObservers() {
        viewModel.players.observe(this) { players ->
            val adapter = ResultsAdapter(viewModel.getPlayerStats())
            findViewById<RecyclerView>(R.id.recyclerViewResults).adapter = adapter
        }
        
        viewModel.winner.observe(this) { winner ->
            winner?.let {
                findViewById<TextView>(R.id.textViewWinner).text = "Gagnant: ${it.name}"
            }
        }
    }
}
