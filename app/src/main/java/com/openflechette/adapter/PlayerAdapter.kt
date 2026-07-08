package com.openflechette.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.openflechette.model.Player

class PlayerAdapter(
    private val players: List<Player>,
    private val onDeleteClick: (Player) -> Unit,
    private val onBotChange: (Player, Boolean) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    
    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewPlayerName: TextView = itemView.findViewById(R.id.textViewPlayerName)
        private val textViewPlayerType: TextView = itemView.findViewById(R.id.textViewPlayerType)
        private val checkBoxIsBot: CheckBox = itemView.findViewById(R.id.checkBoxIsBot)
        private val buttonDelete: Button = itemView.findViewById(R.id.buttonDelete)
        
        fun bind(player: Player) {
            textViewPlayerName.text = player.name
            textViewPlayerType.text = if (player.isBot) "Bot" else "Humain"
            
            buttonDelete.setOnClickListener {
                onDeleteClick(player)
            }
            
            checkBoxIsBot.isChecked = player.isBot
            checkBoxIsBot.setOnCheckedChangeListener { _, isChecked ->
                onBotChange(player, isChecked)
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(players[position])
    }
    
    override fun getItemCount(): Int = players.size
}
