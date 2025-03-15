package com.example.juchi

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLManager(context: Context) : SQLiteOpenHelper(context, "usuarios.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            "CREATE TABLE alumnos(correoelectronico VARCHAR(50) PRIMARY KEY, nombre VARCHAR(50), apellido VARCHAR(50), " +
                    "edad VARCHAR(50), contraseña VARCHAR(50), numtelefono VARCHAR(50), matricula VARCHAR(50))"
        )
        db!!.execSQL(
            "CREATE TABLE chofer(correoelectronicochofer VARCHAR(50) PRIMARY KEY, nombre VARCHAR(50), apellidochofer VARCHAR(50), " +
                    "edadchofer VARCHAR(50), contraseña VARCHAR(50), numtelefonochofer VARCHAR(50), folio VARCHAR(50))"
        )
        db!!.execSQL(
            "CREATE TABLE reportes(transporte VARCHAR(50) PRIMARY KEY, tiemporetraso VARCHAR(50), " +
                    "motivo VARCHAR(50), retraso VARCHAR(1), cancelación VARCHAR(1))"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS alumnos")
        db!!.execSQL("DROP TABLE IF EXISTS chofer")
        db!!.execSQL("DROP TABLE IF EXISTS reportes")
        onCreate(db)
    }

    fun addalumno(context: Context, datos: alumnosClass): Boolean {
        val db = writableDatabase
        val query = "SELECT * FROM alumnos WHERE correoelectronico = ?"
        val cursor = db.rawQuery(query, arrayOf(datos.correoelectronico))

        if (cursor.count > 0) {
            cursor.close()
            db.close()
            return false // El usuario ya existe
        }
        cursor.close()

        val contentValues = ContentValues().apply {
            put("nombre", datos.nombre)
            put("apellido", datos.apellido)
            put("edad", datos.edad)
            put("correoelectronico", datos.correoelectronico)
            put("contraseña", datos.contrasena)
            put("numtelefono", datos.numtelefono)
            put("matricula", datos.matricula)
        }

        return try {
            db.insert("alumnos", null, contentValues)
            db.close()
            true
        } catch (e: Exception) {
            db.close()
            false
        }
    }

    fun addchofer(context: Context, datos: choferClass): Boolean {
        val db = writableDatabase
        val query = "SELECT * FROM chofer WHERE correoelectronicochofer = ?"
        val cursor = db.rawQuery(query, arrayOf(datos.correoelectronicochofer))

        if (cursor.count > 0) {
            cursor.close()
            db.close()
            return false // El chofer ya existe
        }
        cursor.close()

        val contentValues = ContentValues().apply {
            put("nombre", datos.nombrechofer)
            put("apellidochofer", datos.apellidochofer)
            put("edadchofer", datos.edadchofer)
            put("correoelectronicochofer", datos.correoelectronicochofer)
            put("contraseña", datos.contrasenachofer)
            put("numtelefonochofer", datos.numtelefonochofer)
            put("folio", datos.folio)
        }

        return try {
            db.insert("chofer", null, contentValues)
            db.close()
            true
        } catch (e: Exception) {
            db.close()
            false
        }
    }

    fun addreportes(context: Context, datosreportes: reportesClass): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put("transporte", datosreportes.transporte)
            put("tiemporetraso", datosreportes.tiemporetraso)
            put("motivo", datosreportes.motivo)
            put("retraso", datosreportes.retraso)
            put("cancelación", datosreportes.cancelación)
        }

        return try {
            db.insert("reportes", null, contentValues)
            db.close()
            true
        } catch (e: Exception) {
            db.close()
            false
        }
    }

    fun verificarAlumno(correo: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM alumnos WHERE correoelectronico = ?"
        val cursor = db.rawQuery(query, arrayOf(correo))
        val usuarioExiste = cursor.count > 0
        cursor.close()
        db.close()
        return usuarioExiste
    }

}
