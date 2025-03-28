package edu.udb.dsm.desafio_practico_02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.setTitle(R.string.auth_signup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val aliasInput = findViewById<EditText>(R.id.auth_alias)
        val emailInput = findViewById<EditText>(R.id.auth_email)
        val passInput = findViewById<EditText>(R.id.auth_pass)
        val checkInput = findViewById<EditText>(R.id.auth_pass_check)

        findViewById<TextView>(R.id.auth_login).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        findViewById<Button>(R.id.auth_signup).setOnClickListener {
            val error = when {
                aliasInput.text.isEmpty() -> R.string.auth_alias_err
                emailInput.text.isEmpty() -> R.string.auth_email_err
                passInput.text.isEmpty() -> R.string.auth_pass_err
                !passInput.text.equals(checkInput.text) -> R.string.auth_check_err
                else -> -1
            }

            if (error != -1) Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            else {
//                val alias = aliasInput.text.toString()
                val email = emailInput.text.toString()
                val pass = passInput.text.toString()

                val auth = FirebaseAuth.getInstance()
                auth.createUserWithEmailAndPassword(email, pass)
                    .addOnFailureListener { err ->
                        Toast.makeText(this, err.message, Toast.LENGTH_SHORT).show()
                    }.addOnSuccessListener { res ->
                        Toast.makeText(this, res.user?.uid, Toast.LENGTH_SHORT).show()

//                        val db = FirebaseDatabase.getInstance().getReference("users")
//                        val user = DbUser(res.user?.uid, alias, email)

                        startActivity(Intent(this, MainActivity::class.java))
                    }
            }
        }
    }
}