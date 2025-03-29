package edu.udb.dsm.desafio_practico_02

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlin.reflect.KClass

abstract class AppBaseActivity : AppCompatActivity() {
    protected abstract val activityTitle: Int

    // authentication
    protected open var guestActivity = false
    protected lateinit var auth: FirebaseAuth
    private var authStateListener = FirebaseAuth.AuthStateListener { auth ->
        if (auth.currentUser === null) {
            if (!guestActivity) switchTo(LoginActivity::class)
        } else if (guestActivity) switchTo(MainActivity::class)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setTitle(this.activityTitle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()
    }

    override fun onResume() {
        super.onResume()
        auth.addAuthStateListener(authStateListener)
    }

    override fun onPause() {
        super.onPause()
        auth.addAuthStateListener(authStateListener)
    }

    protected fun notify(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun notify(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun switchTo(cls: KClass<*>) {
        startActivity(Intent(this, cls.java))
        finish()
    }

    protected fun clickListener(viewId: Int, listener: OnClickListener) {
        findViewById<View>(viewId).setOnClickListener(listener)
    }
}