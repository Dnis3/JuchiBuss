package com.example.juchi

import android.content.ContentValues
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.OutputStream

class pag_ticket : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag_ticket)

        // Recuperar datos de la Intent
        val lugardestino = intent.getStringExtra("Destino")
        val numtransporte = intent.getIntExtra("notransporte", 0)
        val salidatransporte = intent.getStringExtra("salida")
        val llegadatransporte = intent.getStringExtra("llegada")
        val preciotransporte = intent.getIntExtra("precio", 0)

        // Definir las vistas
        var salir: Button = findViewById(R.id.btnsalir)
        var destino: TextView = findViewById(R.id.destino)
        var imprimir: Button = findViewById(R.id.btnImprimir)
        var precio: TextView = findViewById(R.id.precio)
        var num: TextView = findViewById(R.id.no_transporte)
        var salida: TextView = findViewById(R.id.salida)
        var llegada: TextView = findViewById(R.id.llegada)
        var editar: Button = findViewById(R.id.btnEditar)

        // Asignar valores iniciales
        destino.text = lugardestino.toString()
        precio.text = "$" + preciotransporte.toString() + ".00 mxn"
        num.text = "0" + numtransporte.toString()
        salida.text = salidatransporte.toString()
        llegada.text = llegadatransporte.toString()

        // Configurar el botón de imprimir (generar PDF)
        imprimir.setOnClickListener {
            // Crear un documento PDF
            val document = Document()

            // Usar MediaStore para guardar el archivo en la carpeta de Descargas
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, "ticket.pdf") // Nombre del archivo
                put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf") // Tipo MIME
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS) // Carpeta de descargas
            }

            val resolver = contentResolver
            val uri = resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)

            if (uri != null) {
                // Abrir OutputStream para escribir en el archivo
                val outputStream: OutputStream? = resolver.openOutputStream(uri)

                if (outputStream != null) {
                    // Crear el PDF
                    PdfWriter.getInstance(document, outputStream)
                    document.open()
                    document.add(Paragraph("Destino: $lugardestino"))
                    document.add(Paragraph("Número de transporte: $numtransporte"))
                    document.add(Paragraph("Salida: $salidatransporte"))
                    document.add(Paragraph("Llegada: $llegadatransporte"))
                    document.add(Paragraph("Precio: $$preciotransporte.00 mx (pesos mexicanos)"))
                    document.close()

                    // Notificar al usuario que se ha descargado el archivo
                    showToast("Ticket descargado como ticket.pdf en la carpeta Descargas")
                } else {
                    showToast("Error al acceder al almacenamiento")
                }
            } else {
                showToast("Error al crear el archivo")
            }
        }

        // Acción del botón "Editar"
        editar.setOnClickListener {
            // Crear un AlertDialog con el layout dialog_edit_ticket
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_edit_ticket, null)

            // Configurar los Spinners para seleccionar el destino y la salida
            val destinoSpinner: Spinner = view.findViewById(R.id.spinnerDestino)
            val salidaSpinner: Spinner = view.findViewById(R.id.spinnerSalida)

            // Configurar los datos para los Spinners
            val destinos = arrayOf("Cárdenas", "Comalcalco", "Cunduacán", "Paraíso", "Jalpa")
            val salidas = arrayOf(
                "7:50 a.m", "08:30 p.m", "9:50 a.m", "10:25 a.m", "11:00 a.m", "12:10 p.m",
                "01:10 p.m", "02:40 p.m", "03:40 p.m", "04:40 p.m", "05:40 p.m"
            )

            val destinoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, destinos)
            destinoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            destinoSpinner.adapter = destinoAdapter

            val salidaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, salidas)
            salidaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            salidaSpinner.adapter = salidaAdapter

            // Establecer el layout para el AlertDialog
            builder.setView(view)
                .setCancelable(false)
                .setPositiveButton("Aceptar") { dialog, _ ->
                    // Cuando el usuario elige una opción, actualizar el TextView
                    destino.text = destinoSpinner.selectedItem.toString()
                    salida.text = salidaSpinner.selectedItem.toString()
                    dialog.dismiss()
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }

            // Mostrar el AlertDialog
            builder.create().show()
        }

        // Acción del botón "Salir"
        salir.setOnClickListener {
            val sharedPreferences = getSharedPreferences("TicketData", MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
            finishAffinity()
            System.exit(0)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
