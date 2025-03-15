package com.example.juchi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Horario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horario)
        val rg: Button = findViewById(R.id.rg2) as Button
        val hr1: Button = findViewById(R.id.hr1) as Button
        val hr2: Button = findViewById(R.id.hr2) as Button
        val hr3: Button = findViewById(R.id.hr3) as Button
        val hr4: Button = findViewById(R.id.hr4) as Button
        val hr5: Button = findViewById(R.id.hr5) as Button
        val hr6: Button = findViewById(R.id.hr6) as Button
        val hr7: Button = findViewById(R.id.hr7) as Button
        val hr8: Button = findViewById(R.id.hr8) as Button
        val hr9: Button = findViewById(R.id.hr9) as Button
        val hr10: Button = findViewById(R.id.hr10) as Button
        val hr11: Button = findViewById(R.id.hr11) as Button
        val hr12: Button = findViewById(R.id.hr12) as Button
        rg.setOnClickListener {
            val intent = Intent(this@Horario, Bienvenida::class.java)
            startActivity(intent)
        }
        hr1.setOnClickListener {
            val intent = Intent(this@Horario, Transporte::class.java)
            intent.putExtra("salida", "7:50 a.m")
            intent.putExtra("Llegada", "8:50 a.m")
            startActivity(intent)
        }
        hr2.setOnClickListener {
            val intent = Intent(this@Horario, Transporte::class.java)
            intent.putExtra("salida", "08:30 p.m" )
            intent.putExtra("Llegada", "09:47 p.m")
            startActivity(intent)
        }
        hr3.setOnClickListener {
            val intent = Intent(this@Horario, Transporte::class.java)
            intent.putExtra("salida", "9:50 a.m" )
            intent.putExtra("Llegada", "10:50 a.m")
            startActivity(intent)
        }
        hr4.setOnClickListener {
            val intent = Intent(this@Horario, Transporte::class.java)
            intent.putExtra("salida", "10:25 a.m" )
            intent.putExtra("Llegada", "12:00 a.m")
            startActivity(intent)
        }
        hr5.setOnClickListener {
            val intent = Intent(this@Horario, Transporte::class.java)
            intent.putExtra("salida", "11:00 a.m" )
            intent.putExtra("Llegada", "11:50 a.m")
            startActivity(intent)
        }
        hr6.setOnClickListener {
            val intent = Intent(this@Horario, Transporte::class.java)
            intent.putExtra("salida", "11:10 a.m" )
            intent.putExtra("Llegada", "12:50 p.m")
            startActivity(intent)
        }
        hr7.setOnClickListener {
            val intent = Intent(this@Horario, Transporte::class.java)
            intent.putExtra("salida", "12:10 p.m" )
            intent.putExtra("Llegada", "01:50 p.m")
            startActivity(intent)
        }
        hr8.setOnClickListener {
            val intent = Intent(this@Horario, Transporte::class.java)
            intent.putExtra("salida", "01:10 p.m" )
            intent.putExtra("Llegada", "03:00 p.m")
            startActivity(intent)
        }
        hr9.setOnClickListener {
            val intent = Intent(this@Horario, Transporte::class.java)
            intent.putExtra("salida", "02:40 p.m" )
            intent.putExtra("Llegada", "04:57 p.m")
            startActivity(intent)
        }
        hr10.setOnClickListener {
            val intent = Intent(this@Horario, Transporte::class.java)
            intent.putExtra("salida", "03:40 p.m" )
            intent.putExtra("Llegada", "04:57 p.m")
            startActivity(intent)
        }
        hr11.setOnClickListener {
            val intent = Intent(this@Horario, Transporte::class.java)
            intent.putExtra("salida", "04:40 p.m" )
            intent.putExtra("Llegada", "06:07 p.m")
            startActivity(intent)
        }
        hr12.setOnClickListener {
            val intent = Intent(this@Horario, Transporte::class.java)
            intent.putExtra("salida", "05:40 p.m" )
            intent.putExtra("Llegada", "06:57 p.m")
            startActivity(intent)
        }
    }
}