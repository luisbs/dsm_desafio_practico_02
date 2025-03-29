package edu.udb.dsm.desafio_practico_02.scores

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class Score(
    var name: String? = null,
    var lastName: String? = null,
    var grade: String? = null,
    var subject: String? = null,
    var score: Double? = null,
) {
    companion object {
        fun ref(): DatabaseReference {
            return FirebaseDatabase.getInstance().getReference("scores")
        }

        fun remove(uid: String): Task<Void> {
            return ref().child(uid).removeValue()
        }
    }

    fun store(): Task<Void> {
        return ref().push().setValue(this)
    }
}
