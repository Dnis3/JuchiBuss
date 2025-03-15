package com.example.juchi
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Accesochofer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accesochofer)

        val continuar: Button = findViewById(R.id.continuar) as Button

        val nombre: EditText = findViewById(R.id.usu) as EditText
        val contrasena: EditText = findViewById(R.id.con) as EditText

        continuar.setOnClickListener {

            if (usuarioExiste(nombre.text.toString(), contrasena.text.toString())) {
                val intent = Intent(this@Accesochofer, frmChofer::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun usuarioExiste(nombre: String, contraseña: String): Boolean {
        val db = SQLManager(this)
        val manager = db.readableDatabase
        val query = "SELECT * FROM chofer WHERE nombre = ? AND contraseña = ?"
        val selectionArgs = arrayOf(nombre, contraseña)
        val cursor = manager.rawQuery(query, selectionArgs)
        val usuarioExiste = cursor.count > 0
        cursor.close()
        db.close()
        return usuarioExiste
    }
}