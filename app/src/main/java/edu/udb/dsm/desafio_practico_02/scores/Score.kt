package edu.udb.dsm.desafio_practico_02.scores

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
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

        fun get(uid: String): Task<DataSnapshot> {
            return ref().child(uid).get()
        }

        fun remove(uid: String): Task<Void> {
            return ref().child(uid).removeValue()
        }
    }

    fun store(uid: String? = null): Task<Void> {
        return if (uid !== null) ref().child(uid).setValue(this)
        else ref().push().setValue(this)
    }
}
