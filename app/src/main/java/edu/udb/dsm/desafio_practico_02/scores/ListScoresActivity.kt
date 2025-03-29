package edu.udb.dsm.desafio_practico_02.scores

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun onResume() {
        super.onResume()
        if (auth.currentUser === null) return

        // is authenticated
        val scoresList = findViewById<RecyclerView>(R.id.scores_list)
        scoresList.layoutManager = LinearLayoutManager(this)
        scoresList.adapter = ScoresAdapter()
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