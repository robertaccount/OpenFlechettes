package com.openflechette.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.openflechette.viewmodel.PlayerStats

class ResultsAdapter(
    private val stats: List<PlayerStats>
) : RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>() {
    
    inner class ResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewPlayerName: TextView = itemView.findViewById(R.id.textViewPlayerName)
        private val textViewAverageScore: TextView = itemView.findViewById(R.id.textViewAverageScore)
        private val textViewDoublesAccuracy: TextView = itemView.findViewById(R.id.textViewDoublesAccuracy)
        private val textViewTotalScore: TextView = itemView.findViewById(R.id.textViewTotalScore)
        private val textViewThrowsCount: TextView = itemView.findViewById(R.id.textViewThrowsCount)
        private val textViewDoublesSuccess: TextView = itemView.findViewById(R.id.textViewDoublesSuccess)
        
        fun bind(stats: PlayerStats) {
            textViewPlayerName.text = stats.name
            textViewAverageScore.text = "Moyenne: ${"%.2f".format(stats.averageScore)}"
            textViewDoublesAccuracy.text = "Précision doubles: ${"%.2f".format(stats.doublesAccuracy)}%"
            textViewTotalScore.text = "Score total: ${stats.totalScore}"
            textViewThrowsCount.text = "Lancers: ${stats.throwsCount}"
            textViewDoublesSuccess.text = "Doubles réussis: ${stats.doublesSuccess}/${stats.doublesCount}"
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ResultsViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.bind(stats[position])
    }
    
    override fun getItemCount(): Int = stats.size
}
