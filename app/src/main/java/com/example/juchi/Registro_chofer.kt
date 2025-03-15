package com.example.juchi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Registro_chofer : AppCompatActivity() {
    lateinit var nombre: EditText
    lateinit var apellido: EditText
    lateinit var edad: EditText
    lateinit var correoelectronico: EditText
    lateinit var contrasena: EditText
    lateinit var numtelefono: EditText
    lateinit var folio: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_chofer)
        nombre= findViewById(R.id.nombrec)
        apellido = findViewById(R.id.apellidoc)
        edad = findViewById(R.id.edadc)
        correoelectronico = findViewById(R.id.correoc)
        contrasena = findViewById(R.id.contrasenac)
        numtelefono = findViewById(R.id.numc)
        folio = findViewById(R.id.foliocirculacion)
        val atras: Button = findViewById(R.id.btnatras) as Button
        val registro: Button = findViewById(R.id.btnregistrarse) as Button
        atras.setOnClickListener {
            val intent = Intent(this@Registro_chofer, FrmAcceso::class.java)
            startActivity(intent)
        }
        registro.setOnClickListener {
            if (testDatachofer()) {
                var datos = choferClass(nombre.text.toString(), apellido.text.toString(), edad.text.toString(), correoelectronico.text.toString(),
                    contrasena.text.toString(), numtelefono.text.toString(), folio.text.toString())
                var SQLManager = SQLManager(this)
                var response = SQLManager.addchofer(this, datos)
                if (response){
                    Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Los datos no se pudieron guardar", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"Todos los datos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun testDatachofer(): Boolean {
        var response = true
        if (nombre.text.isEmpty()||apellido.text.isEmpty()||edad.text.isEmpty()||correoelectronico.text.isEmpty()
            ||contrasena.text.isEmpty()||numtelefono.text.isEmpty()||folio.text.isEmpty()){
            response=false
        }
        return response
    }
}