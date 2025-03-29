package edu.udb.dsm.desafio_practico_02.scores

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class Score(
    val name: String,
    val lastName: String,
    val grade: String,
    val subject: String,
    val score: Double
) {
    companion object {
        fun ref(): DatabaseReference {
            return FirebaseDatabase.getInstance().getReference("scores")
        }

        fun remove(uid: String): Task<Void> {
            return ref().child(uid).removeValue()
        }
    }

    fun store(uid: String): Task<Void> {
        return ref().child(uid).setValue(this)
    }
}
