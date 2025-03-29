package edu.udb.dsm.desafio_practico_02

import android.os.Bundle
import android.widget.EditText

class LoginActivity : AppBaseActivity() {
    override val activityLayout = R.layout.activity_login
    override val activityTitle = R.string.auth_login_label
    override var guestActivity = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            else authenticate(
                emailInput.text.toString().trim(), //
                passInput.text.toString().trim()
            )
        }
    }

    private fun authenticate(email: String, pass: String) {
        // authenticate
        auth.signInWithEmailAndPassword(email, pass)
            .addOnFailureListener(::failureListener) //
            .addOnSuccessListener {
                // success
                notify(R.string.auth_welcome)
                switchTo(MainActivity::class)
            }
    }
}