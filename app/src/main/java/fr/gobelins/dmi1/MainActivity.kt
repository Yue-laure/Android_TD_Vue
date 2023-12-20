package fr.gobelins.dmi1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity()  {
    private lateinit var btnHomeCompute: Button
    private lateinit var btnSOS: Button
    private lateinit var btnMessage: Button
    private lateinit var btnGoogle: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnHomeCompute = findViewById(R.id.btn_home_compute)
        btnSOS = findViewById(R.id.btn_sos)
        btnMessage=findViewById(R.id.btn_message)
        btnGoogle =findViewById(R.id.btn_Google)


        btnHomeCompute.setOnClickListener {
            val intent = Intent(this, ComputeActivity::class.java)
            intent.putExtra("operation", "ADD")
            startActivity(intent)
        }

        btnSOS.setOnClickListener{
            // Vérifier la permission d'appel téléphonique
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Lancer l'application d'appel avec le numéro SOS
                val phoneNumber = "+330787029013"
                val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
                startActivity(callIntent)
            } else {
                // Demander la permission d'appel téléphonique si elle n'est pas accordée
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    CALL_PERMISSION_REQUEST_CODE.toInt()
                )
            }
        }

        btnMessage.setOnClickListener{
            partagerContenu()
        }

        btnGoogle.setOnClickListener{
            lancerItineraire()
        }

    }
    companion object {
        private const val CALL_PERMISSION_REQUEST_CODE = 330787029013
    }
    private fun partagerContenu() {
        val contenuPartage = "Hello :) c est Yue de partager un message."

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, contenuPartage)

        startActivity(Intent.createChooser(intent, "Partager via"))
    }

    private fun lancerItineraire() {
        // Coordonnées des MIAGE
        val currentLatitude =  43.61748602724971
        val currentLongitude = 7.064384415346031

        // Coordonnées des Papeteries Gobelins
        val destinationLatitude = 46.2276
        val destinationLongitude = 2.2137

        val uri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=$currentLatitude,$currentLongitude&destination=$destinationLatitude,$destinationLongitude")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps") // Utiliser Google Maps pour l'itinéraire

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            // Si Google Maps n'est pas installé, afficher un message à l'utilisateur
            Toast.makeText(this, "please installer Google！！", Toast.LENGTH_SHORT).show()
        }
    }
}