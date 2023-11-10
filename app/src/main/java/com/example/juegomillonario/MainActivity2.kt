package com.example.juegomillonario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.juegomillonario.databinding.ActivityMain2Binding
import com.example.juegomillonario.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        pantalla_Completa()
        binding.apply {
            btnagregar.setOnClickListener {
                startActivity(Intent(this@MainActivity2,MainActivity3::class.java))
            }
            verPreguntas.setOnClickListener {
                startActivity(Intent(this@MainActivity2,MainActivity4::class.java))
            }
            play.setOnClickListener {
                startActivity(Intent(this@MainActivity2,MainActivity5::class.java))
            }
        }
    }
    fun pantalla_Completa(){
        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.systemBars())
    }
}