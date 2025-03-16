package com.example.juchi

class choferClass {
    var nombrechofer: String=""
    var correoelectronicochofer: String=""
    var contrasenachofer: String=""
    var numtelefonochofer: String=""
    var folio: String=""

    constructor(
        folio: String,
        nombrechofer: String,
        correoelectronicochofer: String,
        contrasenachofer: String,
        numtelefonochofer: String,

    ) {
        this.folio = folio
        this.nombrechofer = nombrechofer
        this.correoelectronicochofer = correoelectronicochofer
        this.contrasenachofer = contrasenachofer
        this.numtelefonochofer = numtelefonochofer

    }
}