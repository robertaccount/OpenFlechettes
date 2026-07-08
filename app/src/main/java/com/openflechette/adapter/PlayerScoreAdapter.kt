package com.openflechette.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.openflechette.R
import com.openflechette.model.Player

class PlayerScoreAdapter(
    private val players: List<Player>,
    private val onScoreClick: (Player, Int) -> Unit
) : RecyclerView.Adapter<PlayerScoreAdapter.PlayerScoreViewHolder>() {
    
    inner class PlayerScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewPlayerName: TextView = itemView.findViewById(R.id.textViewPlayerName)
        private val textViewCurrentScore: TextView = itemView.findViewById(R.id.textViewCurrentScore)
        private val textViewTotalScore: TextView = itemView.findViewById(R.id.textViewTotalScore)
        private val textViewAverage: TextView = itemView.findViewById(R.id.textViewAverage)
        
        fun bind(player: Player) {
            textViewPlayerName.text = player.name
            textViewCurrentScore.text = player.currentScore.toString()
            textViewTotalScore.text = "Total: ${player.totalScore}"
            textViewAverage.text = "Moyenne: ${"%.2f".format(player.averageScore)}"
            
            // Mettre en évidence le joueur actuel
            if (player == currentPlayer) {
                itemView.setBackgroundColor(Color.parseColor("#FFEBEE"))
            } else {
                itemView.setBackgroundColor(Color.TRANSPARENT)
            }
            
            itemView.setOnClickListener {
                // Permettre de modifier le score
            }
        }
    }
    
    var currentPlayer: Player? = null
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player_score, parent, false)
        return PlayerScoreViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: PlayerScoreViewHolder, position: Int) {
        holder.bind(players[position])
    }
    
    override fun getItemCount(): Int = players.size
}
