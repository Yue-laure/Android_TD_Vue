package fr.gobelins.dmi1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ComputeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compute_activity)

        val operation = intent.getStringExtra("operation") ?: "ADD"


    }
}