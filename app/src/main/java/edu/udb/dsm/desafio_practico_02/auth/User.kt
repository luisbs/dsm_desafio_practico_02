package edu.udb.dsm.desafio_practico_02.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class User(
    val alias: String,
    val email: String
) {
    companion object {
        private fun ref(): DatabaseReference {
            return FirebaseDatabase.getInstance().getReference("users")
        }
    }

    fun store(uid: String): Task<Void> {
        return ref().child(uid).setValue(this)
    }
}
