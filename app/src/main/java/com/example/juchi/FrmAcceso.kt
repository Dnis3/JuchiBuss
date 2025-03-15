package com.example.juchi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar

class FrmAcceso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frm_acceso)
        val btnestudiante: Button = findViewById(R.id.btnestudiante) as Button
        val btnChofer: Button = findViewById(R.id.btnconductor) as Button
        val btnregistrar: Button=findViewById(R.id.btnregistrarme) as Button
        btnregistrar.setOnClickListener {
            val intent = Intent(this@FrmAcceso, FrmRegistro::class.java)
            startActivity(intent)
        }
        btnestudiante.setOnClickListener {
            val intent = Intent(this@FrmAcceso, Acceso::class.java)
            startActivity(intent)
        }
        btnChofer.setOnClickListener {
            val intent = Intent(this@FrmAcceso, Accesochofer::class.java)
            startActivity(intent)
        }
    }
}