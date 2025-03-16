package com.example.juchi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.HideReturnsTransformationMethod
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class Registro_estudiante : AppCompatActivity() {
    private lateinit var nombre: EditText
    private lateinit var correoelectronico: EditText
    private lateinit var contrasena: EditText
    private lateinit var confirmarcontra: EditText
    private lateinit var numtelefono: EditText
    private lateinit var matriculae: EditText
    private lateinit var ojoContraseña: ImageView
    private lateinit var inicio: TextView

    private var esVisible = false  // Estado de visibilidad de la contraseña

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_estudiante)

        // Inicialización de los EditText
        nombre = findViewById(R.id.NombreE)
        correoelectronico = findViewById(R.id.CorreoE)
        contrasena = findViewById(R.id.PasswordE)
        confirmarcontra = findViewById(R.id.Confirmarcontra) // Nuevo campo agregado
        numtelefono = findViewById(R.id.Telefono)
        matriculae = findViewById(R.id.MatriculaE)
        ojoContraseña = findViewById(R.id.ojoPassword) // ID del icono de ojo
        inicio = findViewById(R.id.iriniciosesion)

        val registro: Button = findViewById(R.id.registrochofer)

        inicio.setOnClickListener {
            val intent = Intent(this@Registro_estudiante, Acceso::class.java)
            startActivity(intent)
            finish()
        }

        // Agregar funcionalidad al ojito
        ojoContraseña.setOnClickListener {
            alternarVisibilidadContraseña()
        }

        registro.setOnClickListener {
            if (validarCampos()) {
                val matricula = matriculae.text.toString()
                val sqlManager = SQLManager(this)

                try {
                    // Verificar si la matrícula ya está registrada
                    if (sqlManager.verificarAlumno(matricula)) {
                        Toast.makeText(this, "Error: La matrícula ya está registrada", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    // Registrar al usuario
                    val datos = alumnosClass(
                        matricula,
                        nombre.text.toString(),
                        correoelectronico.text.toString(),
                        contrasena.text.toString(),
                        numtelefono.text.toString()
                    )

                    val response = sqlManager.addalumno(this, datos)
                    if (response) {
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Registro_estudiante, Bienvenida::class.java)
                        startActivity(intent)
                        limpiarCampos()
                        finish()
                    } else {
                        Toast.makeText(this, "Error: No se pudo registrar", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Error en la base de datos: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            }
        }
    }

    private fun validarCampos(): Boolean {
        val matricula = matriculae.text.toString().trim()
        val nombreText = nombre.text.toString().trim()
        val correo = correoelectronico.text.toString().trim()
        val password = contrasena.text.toString().trim()
        val confirmarPassword = confirmarcontra.text.toString().trim()
        val telefono = numtelefono.text.toString().trim()

        if (matricula.isEmpty() || nombreText.isEmpty() || correo.isEmpty() || password.isEmpty() || confirmarPassword.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (telefono.length != 10 || !telefono.matches(Regex("\\d{10}"))) {
            Toast.makeText(this, "Número de teléfono inválido. Debe tener 10 dígitos", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmarPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun limpiarCampos() {
        nombre.text.clear()
        correoelectronico.text.clear()
        contrasena.text.clear()
        confirmarcontra.text.clear()
        numtelefono.text.clear()
        matriculae.text.clear()
    }

    private fun alternarVisibilidadContraseña() {
        if (esVisible) {
            // Ocultar contraseña
            contrasena.transformationMethod = PasswordTransformationMethod.getInstance()
            //confirmarcontra.transformationMethod = PasswordTransformationMethod.getInstance()
            ojoContraseña.setImageResource(R.drawable.ic_ojo_cerrado) // Ícono de ojo cerrado
        } else {
            // Mostrar contraseña
            contrasena.transformationMethod = HideReturnsTransformationMethod.getInstance()
            //confirmarcontra.transformationMethod = HideReturnsTransformationMethod.getInstance()
            ojoContraseña.setImageResource(R.drawable.ic_ojo_abierto) // Ícono de ojo abierto
        }
        esVisible = !esVisible
        contrasena.setSelection(contrasena.text.length) // Mantiene el cursor al final
        confirmarcontra.setSelection(confirmarcontra.text.length)
    }
}
