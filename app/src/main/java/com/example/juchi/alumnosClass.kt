package com.example.juchi

class alumnosClass {
    var nombre: String=""
    var correoelectronico: String=""
    var contrasena: String=""
    var numtelefono: String=""
    var matricula: String=""

    constructor(
        matricula: String,
        nombre: String,
        correoelectronico: String,
        contrasena: String,
        numtelefono: String,

    ) {
        this.matricula = matricula
        this.nombre = nombre
        this.correoelectronico = correoelectronico
        this.contrasena = contrasena
        this.numtelefono = numtelefono
    }
}