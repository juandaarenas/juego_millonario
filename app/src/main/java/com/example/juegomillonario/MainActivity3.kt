package com.example.juegomillonario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.juegomillonario.databinding.ActivityMain3Binding
import com.example.juegomillonario.databinding.ActivityMainBinding
import com.example.juegomillonario.modelos.preguntas
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding
    lateinit var fireDatabase: FirebaseDatabase
    lateinit var  preguntaRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        fireDatabase = FirebaseDatabase.getInstance()
        preguntaRef = fireDatabase.getReference("datos")
        pantalla_Completa()
        binding.apply {
            guardar.setOnClickListener {
                create()
            }
            regresar.setOnClickListener {
                startActivity(Intent(this@MainActivity3,MainActivity2::class.java))
            }
        }
    }
    fun create() {
        binding.apply {
            try {
                var id = textId.text.toString()
                var pregunt = textpregunta.text.toString()
                var respuest = textrespuesta.text.toString()
                var opcionA = textOpcA.text.toString()
                var opcionB = textOpcB.text.toString()
                var opcionC = textOpcC.text.toString()
                var opcionD = textOpcD.text.toString()
                preguntaRef.child(id).setValue(preguntas(id,pregunt,respuest,opcionA,opcionB,opcionC,opcionD))
                Toast.makeText(this@MainActivity3, "se guardo exitosamente", Toast.LENGTH_SHORT).show()
                textId.setText("");
                textpregunta.setText("");
                textrespuesta.setText("");
                textOpcA.setText("");
                textOpcB.setText("");
                textOpcC.setText("");
                textOpcD.setText("");
            }catch (e:Exception){
                Toast.makeText(this@MainActivity3, "No se ha guardado ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun pantalla_Completa(){
        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.systemBars())
    }
}