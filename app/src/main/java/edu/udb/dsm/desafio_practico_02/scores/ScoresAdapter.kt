package edu.udb.dsm.desafio_practico_02.scores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import edu.udb.dsm.desafio_practico_02.R

class ScoresAdapter(
    options: FirebaseRecyclerOptions<Score> = FirebaseRecyclerOptions.Builder<Score>() //
        .setQuery(Score.ref(), Score::class.java).build()
) : FirebaseRecyclerAdapter<Score, ScoresAdapter.ScoreView>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreView {
        val view = LayoutInflater.from(parent.context) //
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return ScoreView(parent.context, view)
    }

    override fun onBindViewHolder(holder: ScoreView, position: Int, entry: Score) {
        holder.bind(entry)
    }

    class ScoreView(private val ctx: Context, itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val line1: TextView = itemView.findViewById(android.R.id.text1)
        private val line2: TextView = itemView.findViewById(android.R.id.text2)

        fun bind(i: Score) {
            line1.text = ctx.getString(R.string.score_line1, i.grade, i.name, i.lastName)
            line2.text = ctx.getString(R.string.score_line2, i.subject, i.score)
        }
    }
}
