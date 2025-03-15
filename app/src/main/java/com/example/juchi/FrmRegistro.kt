package com.example.juchi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FrmRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frm_registro)
        val estudiante: Button = findViewById(R.id.btnatras) as Button
        val chofer: Button = findViewById(R.id.btnregistrarse) as Button
        estudiante.setOnClickListener {
            val intent = Intent(this@FrmRegistro, Registro_estudiante::class.java)
            startActivity(intent)
        }
        chofer.setOnClickListener {
            val intent = Intent(this@FrmRegistro, Registro_chofer::class.java)
            startActivity(intent)
        }
    }
}