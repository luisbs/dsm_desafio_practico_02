package edu.udb.dsm.desafio_practico_02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppBaseActivity() {
    override val activityTitle = R.string.auth_login_label
    override var guestActivity = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.auth_email)
        val passInput = findViewById<EditText>(R.id.auth_pass)

        clickListener(R.id.auth_signup) { switchTo(SignupActivity::class) }
        clickListener(R.id.auth_login) {
            val error = when {
                emailInput.text.isEmpty() -> R.string.auth_email_err
                passInput.text.isEmpty() -> R.string.auth_pass_err
                else -> -1
            }

            if (error != -1) notify(error)
            else {
                val email = emailInput.text.toString()
                val pass = passInput.text.toString()

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, pass)
                    .addOnFailureListener { err -> notify(err.message) }
                    .addOnSuccessListener { res ->
                        notify(res.user?.uid)

                        switchTo(MainActivity::class)
                    }
            }
        }
    }
}