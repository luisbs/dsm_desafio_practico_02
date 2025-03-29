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
    protected open var guestActivity: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // check authentication
        val user = FirebaseAuth.getInstance().currentUser
        if (user === null) {
            if (!guestActivity) switchTo(LoginActivity::class)
        } else if (guestActivity) switchTo(MainActivity::class)

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