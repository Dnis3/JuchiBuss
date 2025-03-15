package com.example.juchi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import kotlin.math.roundToInt

class Transporte : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transporte)
        val cardenas: ImageButton = findViewById(R.id.cardenas)
        val comalcalco: ImageButton = findViewById(R.id.comalcalco)
        val cunduacan: ImageButton = findViewById(R.id.cunduacan)
        val paraiso: ImageButton = findViewById(R.id.paraiso)
        val jalpa: ImageButton = findViewById(R.id.jalpa)
        val villahermosa: ImageButton = findViewById(R.id.villahermosa)
        val juchi: ImageButton = findViewById(R.id.juchi)

        cardenas.setOnClickListener {
            val salidatransporte = intent.getStringExtra("salida")
            val llegadatransporte = intent.getStringExtra("Llegada")
            val intent = Intent(this@Transporte, pag_ticket::class.java)
            intent.putExtra("Destino", "Cardenas" )
            intent.putExtra("precio", 37 )
            intent.putExtra("notransporte",  40 )
            intent.putExtra("salida", salidatransporte)
            intent.putExtra("llegada", llegadatransporte)
            startActivity(intent)
        }
        comalcalco.setOnClickListener {
            val salidatransporte = intent.getStringExtra("salida")
            val llegadatransporte = intent.getStringExtra("Llegada")
            val intent = Intent(this@Transporte, pag_ticket::class.java)
            intent.putExtra("Destino", "Comalcalco" )
            intent.putExtra("precio", 17 )
            intent.putExtra("notransporte", 72 )
            intent.putExtra("salida", salidatransporte)
            intent.putExtra("llegada", llegadatransporte)
            startActivity(intent)
        }
        cunduacan.setOnClickListener {
            val salidatransporte = intent.getStringExtra("salida")
            val llegadatransporte = intent.getStringExtra("Llegada")
            val intent = Intent(this@Transporte, pag_ticket::class.java)
            intent.putExtra("Destino", "Cunduacán" )
            intent.putExtra("precio", 10 )
            intent.putExtra("notransporte", 324 )
            intent.putExtra("salida", salidatransporte)
            intent.putExtra("llegada", llegadatransporte)
            startActivity(intent)
        }
        paraiso.setOnClickListener {
            val salidatransporte = intent.getStringExtra("salida")
            val llegadatransporte = intent.getStringExtra("Llegada")
            val intent = Intent(this@Transporte, pag_ticket::class.java)
            intent.putExtra("Destino", "Paraíso" )
            intent.putExtra("precio", 22 )
            intent.putExtra("notransporte", 34 )
            intent.putExtra("salida", salidatransporte)
            intent.putExtra("llegada", llegadatransporte)
            startActivity(intent)
        }
        jalpa.setOnClickListener {
            val salidatransporte = intent.getStringExtra("salida")
            val llegadatransporte = intent.getStringExtra("Llegada")
            val intent = Intent(this@Transporte, pag_ticket::class.java)
            intent.putExtra("Destino", "Jalpa" )
            intent.putExtra("precio", 15 )
            intent.putExtra("notransporte", 3 )
            intent.putExtra("salida", salidatransporte)
            intent.putExtra("llegada", llegadatransporte)
            startActivity(intent)
        }
        villahermosa.setOnClickListener {
            val salidatransporte = intent.getStringExtra("salida")
            val llegadatransporte = intent.getStringExtra("Llegada")
            val intent = Intent(this@Transporte, pag_ticket::class.java)
            intent.putExtra("Destino", "Villahermosa" )
            intent.putExtra("precio", 25 )
            intent.putExtra("notransporte", 45 )
            intent.putExtra("salida", salidatransporte)
            intent.putExtra("llegada", llegadatransporte)
            startActivity(intent)
        }
        juchi.setOnClickListener {
            val salidatransporte = intent.getStringExtra("salida")
            val llegadatransporte = intent.getStringExtra("Llegada")
            val intent = Intent(this@Transporte, pag_ticket::class.java)
            intent.putExtra("Destino", "Villahermosa" )
            intent.putExtra("precio", 0 )
            intent.putExtra("notransporte", 12 )
            intent.putExtra("salida", salidatransporte)
            intent.putExtra("llegada", llegadatransporte)
            startActivity(intent)
        }
    }
}