package com.example.juchi

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.telephony.SmsManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class frmChofer : AppCompatActivity() {
    // Definición de campos y botones
    lateinit var transporte: EditText
    lateinit var tmpretraso: EditText
    lateinit var motivo: EditText
    lateinit var radioGroup: RadioGroup
    lateinit var retraso: RadioButton
    lateinit var cancelacion: RadioButton
    lateinit var enviar: Button
    lateinit var regresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frm_chofer)

        // Inicializar vistas
        transporte = findViewById(R.id.transporte)
        tmpretraso = findViewById(R.id.tmpretraso)
        motivo = findViewById(R.id.motivo)
        radioGroup = findViewById(R.id.RadioGroup)
        retraso = findViewById(R.id.retraso)
        cancelacion = findViewById(R.id.cancelacon)
        enviar = findViewById(R.id.btnenviar)
        regresar = findViewById(R.id.btnregresar)

        // Configurar el RadioGroup para limpiar campos al cambiar de opción
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.retraso -> motivo.text.clear() // Limpiar campo Motivo si selecciona Retraso
                R.id.cancelacon -> tmpretraso.text.clear() // Limpiar campo Tiempo de Retraso si selecciona Cancelación
            }
        }

        // Botón Enviar
        enviar.setOnClickListener {
            val reporteSeleccionado = when (radioGroup.checkedRadioButtonId) {
                R.id.retraso -> "Retraso"
                R.id.cancelacon -> "Cancelación"
                else -> null
            }

            if (reporteSeleccionado != null && validateFields(reporteSeleccionado)) {
                val datos = reportesClass(
                    transporte.text.toString(),
                    tmpretraso.text.toString(),
                    motivo.text.toString(),
                    if (retraso.isChecked) "S" else "N",
                    if (cancelacion.isChecked) "S" else "N"
                )

                val sqlManager = SQLManager(this)
                val response = sqlManager.addreportes(this, datos)

                if (response) {
                    enviarNotificaciones(transporte.text.toString(), tmpretraso.text.toString())
                    Toast.makeText(this, "Reporte enviado exitosamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al guardar el reporte", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Complete los campos requeridos según la selección", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón Regresar
        regresar.setOnClickListener {
            val intent = Intent(this@frmChofer, FrmAcceso::class.java)
            startActivity(intent)
        }

        // Solicitar permisos de SMS
        solicitarPermisos()
    }

    // Función para validar los campos
    private fun validateFields(reporteSeleccionado: String): Boolean {
        if (transporte.text.isEmpty()) {
            Toast.makeText(this, "El campo Transporte es obligatorio", Toast.LENGTH_SHORT).show()
            return false
        }

        return when (reporteSeleccionado) {
            "Retraso" -> {
                if (tmpretraso.text.isEmpty()) {
                    Toast.makeText(this, "El campo Tiempo de Retraso es obligatorio", Toast.LENGTH_SHORT).show()
                    false
                } else {
                    true
                }
            }
            "Cancelación" -> {
                if (motivo.text.isEmpty()) {
                    Toast.makeText(this, "El campo Motivo es obligatorio", Toast.LENGTH_SHORT).show()
                    false
                } else {
                    true
                }
            }
            else -> false
        }
    }

    // Función para enviar notificaciones
    private fun enviarNotificaciones(transporte: String, tiempoRetraso: String) {
        val db = SQLManager(this).readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT numtelefono, correoelectronico FROM alumnos", null)

        if (cursor.count == 0) {
            Toast.makeText(this, "No hay alumnos registrados para notificar", Toast.LENGTH_SHORT).show()
            cursor.close()
            db.close()
            return
        }

        while (cursor.moveToNext()) {
            val numeroTelefono = cursor.getString(0) // Número de teléfono
            val correoElectronico = cursor.getString(1) // Correo electrónico

            // Enviar SMS
            if (!numeroTelefono.isNullOrEmpty()) {
                try {
                    val smsManager = SmsManager.getDefault()
                    val mensaje = "El transporte $transporte tiene un retraso de $tiempoRetraso."
                    smsManager.sendTextMessage(numeroTelefono, null, mensaje, null, null)
                    Toast.makeText(this, "SMS enviado a $numeroTelefono", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error al enviar SMS a $numeroTelefono", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            // Implementación para correo (opcional)
            if (!correoElectronico.isNullOrEmpty()) {
                // Aquí puedes implementar el envío de correos electrónicos usando JavaMail o similar
                println("Correo pendiente para $correoElectronico: El transporte $transporte tiene un retraso de $tiempoRetraso.")
            }
        }

        cursor.close()
        db.close()
    }

    // Solicitar permisos en tiempo de ejecución
    private fun solicitarPermisos() {
        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.SEND_SMS), 1)
        }
    }
}