package edu.udb.dsm.desafio_practico_02

import android.os.Bundle
import android.widget.EditText
import edu.udb.dsm.desafio_practico_02.db.entities.User
import edu.udb.dsm.desafio_practico_02.db.repositories.UserRepository

class SignupActivity : AppBaseActivity() {
    override val activityTitle = R.string.auth_signup_label
    override var guestActivity = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val aliasInput = findViewById<EditText>(R.id.auth_alias)
        val emailInput = findViewById<EditText>(R.id.auth_email)
        val passInput = findViewById<EditText>(R.id.auth_pass)
        val checkInput = findViewById<EditText>(R.id.auth_pass_check)

        clickListener(R.id.auth_login) { switchTo(LoginActivity::class) }
        clickListener(R.id.auth_signup) {
            val error = when {
                aliasInput.text.isEmpty() -> R.string.auth_alias_err
                emailInput.text.isEmpty() -> R.string.auth_email_err
                passInput.text.isEmpty() -> R.string.auth_pass_err
                !passInput.text.equals(checkInput.text) -> R.string.auth_check_err
                else -> -1
            }

            if (error != -1) notify(error)
            else authenticate(
                aliasInput.text.toString(), //
                emailInput.text.toString(), //
                passInput.text.toString()
            )
        }
    }

    private fun authenticate(alias: String, email: String, pass: String) {
        // authenticate
        auth.createUserWithEmailAndPassword(email, pass) //
            .addOnFailureListener(::failureListener) //
            .addOnSuccessListener { res ->
                // store new user
                UserRepository.store(res.user!!.uid, User(alias, email))
                    .addOnFailureListener(::failureListener) //
                    .addOnSuccessListener {
                        // success
                        notify(R.string.auth_welcome)
                        switchTo(MainActivity::class)
                    }
            }
    }
}