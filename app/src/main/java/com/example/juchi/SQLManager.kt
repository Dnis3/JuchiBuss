package com.example.juchi

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLManager(context: Context) : SQLiteOpenHelper(context, "usuarios.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            "CREATE TABLE alumnos(matricula VARCHAR(50) PRIMARY KEY, nombre VARCHAR(70), correoelectronico VARCHAR(50), " +
                    "contraseña VARCHAR(50), numtelefono VARCHAR(10) )"
        )
        db!!.execSQL(
            "CREATE TABLE chofer(folio VARCHAR(50) PRIMARY KEY, nombre VARCHAR(70), correoelectronicochofer VARCHAR(50) , " +
                    "contraseña VARCHAR(50), numtelefonochofer VARCHAR(10))"
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
        val query = "SELECT * FROM alumnos WHERE matricula = ?"
        val cursor = db.rawQuery(query, arrayOf(datos.matricula))

        if (cursor.count > 0) {
            cursor.close()
            db.close()
            return false // El usuario ya existe
        }
        cursor.close()

        val contentValues = ContentValues().apply {
            put("matricula", datos.matricula)
            put("nombre", datos.nombre)
            put("correoelectronico", datos.correoelectronico)
            put("contraseña", datos.contrasena)
            put("numtelefono", datos.numtelefono)

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
        val query = "SELECT * FROM chofer WHERE folio = ?"
        val cursor = db.rawQuery(query, arrayOf(datos.folio))

        if (cursor.count > 0) {
            cursor.close()
            db.close()
            return false // El chofer ya existe
        }
        cursor.close()

        val contentValues = ContentValues().apply {
            put("folio", datos.folio)
            put("nombre", datos.nombrechofer)
            put("correoelectronicochofer", datos.correoelectronicochofer)
            put("contraseña", datos.contrasenachofer)
            put("numtelefonochofer", datos.numtelefonochofer)

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

    fun verificarAlumno(matricula: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM alumnos WHERE matricula = ?"
        val cursor = db.rawQuery(query, arrayOf(matricula))
        val usuarioExiste = cursor.count > 0
        cursor.close()
        db.close()
        return usuarioExiste
    }

    fun verificarChofer(folio: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM chofer WHERE folio = ?"
        val cursor = db.rawQuery(query, arrayOf(folio))
        val usuarioExiste = cursor.count > 0
        cursor.close()
        db.close()
        return usuarioExiste
    }

}
