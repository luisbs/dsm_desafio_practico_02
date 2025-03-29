package edu.udb.dsm.desafio_practico_02.scores

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import edu.udb.dsm.desafio_practico_02.AppBaseActivity
import edu.udb.dsm.desafio_practico_02.R
import edu.udb.dsm.desafio_practico_02.auth.LoginActivity

class ListScoresActivity : AppBaseActivity() {
    override val activityLayout = R.layout.activity_list_scores
    override val activityTitle = R.string.app_name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // early execution for when the app is launched
        authStateListener.onAuthStateChanged(auth)
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser === null) return

        val options = FirebaseRecyclerOptions.Builder<Score>() //
            .setQuery(Score.ref(), Score::class.java) //
            .setLifecycleOwner(this).build()

        val adapter = object : FirebaseRecyclerAdapter<Score, ScoreHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreHolder {
                return ScoreHolder.make(parent)
            }

            override fun onBindViewHolder(holder: ScoreHolder, position: Int, m: Score) {
                holder.line1Txt.text = getString(R.string.score_line1, m.grade, m.name, m.lastName)
                holder.line2Txt.text = getString(R.string.score_line2, m.subject, m.score)
//                holder.editBtn.setOnClickListener {  }
                holder.deleteBtn.setOnClickListener {
                    Score.remove(this.getRef(position).key!!) //
                        .addOnFailureListener(::failureListener)
                        .addOnSuccessListener { notify(R.string.score_deleted) }
                }
            }
        }

        val scoresList: RecyclerView = findViewById(R.id.scores_list)
        scoresList.layoutManager = LinearLayoutManager(this)
        scoresList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add -> switchTo(CreateScoreActivity::class)
            R.id.menu_exit -> {
                FirebaseAuth.getInstance().signOut() //
                    .also { switchTo(LoginActivity::class, R.string.auth_bye) }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}