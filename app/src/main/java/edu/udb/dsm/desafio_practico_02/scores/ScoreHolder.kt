package edu.udb.dsm.desafio_practico_02.scores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.udb.dsm.desafio_practico_02.R

class ScoreHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val line1Txt: TextView = itemView.findViewById(R.id.score_line1)
    val line2Txt: TextView = itemView.findViewById(R.id.score_line2)

    companion object {
        fun make(parent: ViewGroup): ScoreHolder {
            val view = LayoutInflater.from(parent.context) //
                .inflate(R.layout.list_score_item, parent, false)
            return ScoreHolder(view)
        }
    }
}
