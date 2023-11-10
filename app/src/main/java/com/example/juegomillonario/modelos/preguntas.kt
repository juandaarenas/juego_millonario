package com.example.juegomillonario.modelos

data class preguntas(var id:String,var pregunta:String,var respuesta:String, var a:String,var b:String,var c:String,var d:String) {
    constructor():this("","","","","","","")
}