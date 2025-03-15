package com.example.juchi

class reportesClass {
    var transporte: String=""
    var tiemporetraso: String=""
    var motivo : String=""
    var retraso: String=""
    var cancelación: String=""

    constructor(
        transporte: String,
        tiemporetraso: String,
        motivo: String,
        retraso: String,
        cancelación: String
    ) {
        this.transporte = transporte
        this.tiemporetraso = tiemporetraso
        this.motivo = motivo
        this.retraso = retraso
        this.cancelación = cancelación
    }
}