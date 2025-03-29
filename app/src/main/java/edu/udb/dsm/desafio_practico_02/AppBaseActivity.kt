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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // check authentication
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser === null) {
            switchTo(LoginActivity::class)
            return
        }

        // user is authenticated
        supportActionBar?.setTitle(this.activityTitle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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