package com.example.juchi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Registro_estudiante : AppCompatActivity() {
    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var edad: EditText
    private lateinit var correoelectronico: EditText
    private lateinit var contrasena: EditText
    private lateinit var numtelefono: EditText
    private lateinit var matricula: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_estudiante)

        // Inicialización de los EditText
        nombre = findViewById(R.id.NombreE)
        apellido = findViewById(R.id.ApellidoE)
        edad = findViewById(R.id.EdadE)
        correoelectronico = findViewById(R.id.CoreoE)
        contrasena = findViewById(R.id.ContrasenaE)
        numtelefono = findViewById(R.id.NumE)
        matricula = findViewById(R.id.MatriculaE)

        val atras: Button = findViewById(R.id.btnatras)
        val registro: Button = findViewById(R.id.btnregistrarse)

        atras.setOnClickListener {
            val intent = Intent(this@Registro_estudiante, FrmAcceso::class.java)
            startActivity(intent)
            finish()
        }

        registro.setOnClickListener {
            if (validarCampos()) {
                val correo = correoelectronico.text.toString()

                val sqlManager = SQLManager(this)

                // Verificar si el usuario ya existe
                if (sqlManager.verificarAlumno(correo)) {
                    Toast.makeText(this, "Error: El correo que proporcionó ya está registrado", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Si no existe, registrar al usuario
                val datos = alumnosClass(
                    nombre.text.toString(),
                    apellido.text.toString(),
                    edad.text.toString(),
                    correo,
                    contrasena.text.toString(),
                    numtelefono.text.toString(),
                    matricula.text.toString()
                )

                try {
                    val response = sqlManager.addalumno(this, datos)
                    if (response) {
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        limpiarCampos()
                        finish()
                    } else {
                        Toast.makeText(this, "Error: No se pudo registrar el usuario", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Error en el registro: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validarCampos(): Boolean {
        return !(nombre.text.isEmpty() || apellido.text.isEmpty() || edad.text.isEmpty()
                || correoelectronico.text.isEmpty() || contrasena.text.isEmpty()
                || numtelefono.text.isEmpty() || matricula.text.isEmpty())
    }

    private fun limpiarCampos() {
        nombre.text.clear()
        apellido.text.clear()
        edad.text.clear()
        correoelectronico.text.clear()
        contrasena.text.clear()
        numtelefono.text.clear()
        matricula.text.clear()
    }
}
