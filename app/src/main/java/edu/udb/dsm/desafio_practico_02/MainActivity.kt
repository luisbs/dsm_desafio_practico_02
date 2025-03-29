package edu.udb.dsm.desafio_practico_02

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppBaseActivity() {
    override val activityLayout = R.layout.activity_main
    override val activityTitle = R.string.app_name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // early execution for when the app is launched
        authStateListener.onAuthStateChanged(auth)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_exit -> {
                FirebaseAuth.getInstance().signOut() //
                    .also { switchTo(LoginActivity::class, R.string.auth_bye) }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}