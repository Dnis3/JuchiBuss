package com.example.juchi

class choferClass {
    var nombrechofer: String=""
    var apellidochofer: String=""
    var edadchofer: String=""
    var correoelectronicochofer: String=""
    var contrasenachofer: String=""
    var numtelefonochofer: String=""
    var folio: String=""

    constructor(
        nombrechofer: String,
        apellidochofer: String,
        edadchofer: String,
        correoelectronicochofer: String,
        contrasenachofer: String,
        numtelefonochofer: String,
        folio: String
    ) {
        this.nombrechofer = nombrechofer
        this.apellidochofer = apellidochofer
        this.edadchofer = edadchofer
        this.correoelectronicochofer = correoelectronicochofer
        this.contrasenachofer = contrasenachofer
        this.numtelefonochofer = numtelefonochofer
        this.folio = folio
    }
}