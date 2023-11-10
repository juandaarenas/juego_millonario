package com.example.juegomillonario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.juegomillonario.adapters.recyAdarter
import com.example.juegomillonario.databinding.ActivityMain3Binding
import com.example.juegomillonario.databinding.ActivityMain4Binding
import com.example.juegomillonario.modelos.preguntas
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain4Binding
    lateinit var fireDatabase: FirebaseDatabase
    lateinit var preguntaRef: DatabaseReference
    lateinit var listaP: ArrayList<preguntas>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        pantalla_Completa()
        fireDatabase = FirebaseDatabase.getInstance()
        preguntaRef = fireDatabase.getReference("datos")
        binding.apply {
            listaP = arrayListOf<preguntas>()
            recyclear.layoutManager= LinearLayoutManager(this@MainActivity4)
            recyclear.setHasFixedSize(true)
            getPreguntas()
            btnRegresar.setOnClickListener {
                startActivity(Intent(this@MainActivity4,MainActivity2::class.java))
            }
        }
    }
    fun getPreguntas(){
        binding.apply {
            recyclear.visibility = View.GONE
            fireDatabase = FirebaseDatabase.getInstance()
            preguntaRef = fireDatabase.getReference("datos")
            preguntaRef.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    listaP.clear()
                    if (snapshot.exists()){
                        for (PreSnap in snapshot.children){
                            val preData = PreSnap.getValue(preguntas::class.java)
                            listaP.add(preData!!)
                        }
                        val adaptado = recyAdarter(listaP)
                        recyclear.adapter = adaptado
                        recyclear.visibility = View.VISIBLE
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
    fun pantalla_Completa(){
        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.systemBars())
    }
}