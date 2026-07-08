package com.openflechette.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val btnNewGame = findViewById<Button>(R.id.btnNewGame)
        val btnContinueGame = findViewById<Button>(R.id.btnContinueGame)
        val btnStatistics = findViewById<Button>(R.id.btnStatistics)
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        
        btnNewGame.setOnClickListener {
            val intent = Intent(this, GameSetupActivity::class.java)
            startActivity(intent)
        }
        
        btnContinueGame.setOnClickListener {
            // Pour l'instant, on lance une nouvelle partie
            val intent = Intent(this, GameSetupActivity::class.java)
            startActivity(intent)
        }
        
        btnStatistics.setOnClickListener {
            // À implémenter plus tard
        }
        
        btnSettings.setOnClickListener {
            // À implémenter plus tard
        }
    }
}
