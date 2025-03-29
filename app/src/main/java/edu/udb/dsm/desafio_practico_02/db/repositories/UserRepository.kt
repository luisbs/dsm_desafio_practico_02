package edu.udb.dsm.desafio_practico_02.db.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import edu.udb.dsm.desafio_practico_02.db.entities.User

class UserRepository {
    companion object {
        private const val REFERENCE_NAME = "users"

        private fun ref(): DatabaseReference {
            return FirebaseDatabase.getInstance().getReference(REFERENCE_NAME)
        }

        fun store(uid: String, entry: User): Task<Void> {
            return ref().child(uid).setValue(entry)
        }
    }
}