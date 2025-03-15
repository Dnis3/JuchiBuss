package com.example.juchi

class alumnosClass {
    var nombre: String=""
    var apellido: String=""
    var edad : String=""
    var correoelectronico: String=""
    var contrasena: String=""
    var numtelefono: String=""
    var matricula: String=""

    constructor(
        nombre: String,
        apellido: String,
        edad: String,
        correoelectronico: String,
        contrasena: String,
        numtelefono: String,
        matricula: String
    ) {
        this.nombre = nombre
        this.apellido = apellido
        this.edad = edad
        this.correoelectronico = correoelectronico
        this.contrasena = contrasena
        this.numtelefono = numtelefono
        this.matricula = matricula
    }
}