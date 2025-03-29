package edu.udb.dsm.desafio_practico_02

import android.os.Bundle

class MainActivity : AppBaseActivity() {
    override val activityLayout = R.layout.activity_main
    override val activityTitle = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // early execution for when the app is launched
        authStateListener.onAuthStateChanged(auth)
    }
}