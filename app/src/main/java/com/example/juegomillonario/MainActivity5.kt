package com.example.juegomillonario

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.juegomillonario.databinding.ActivityMain4Binding
import com.example.juegomillonario.databinding.ActivityMain5Binding
import com.example.juegomillonario.modelos.preguntas
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity5 : AppCompatActivity() {
    lateinit var binding: ActivityMain5Binding
    lateinit var fireDatabase: FirebaseDatabase
    lateinit var preguntaRef: DatabaseReference
    lateinit var lista:MutableList<preguntas>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)
        fireDatabase = FirebaseDatabase.getInstance()
        preguntaRef = fireDatabase.getReference("datos")
        var intentos=3
        var dinero = 0
        var aciertos=0
        preguntaRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var Coleccion:MutableList<preguntas> = mutableListOf()
                snapshot.children.forEach {
                    var datos=it.getValue(preguntas::class.java)
                    Coleccion.add(preguntas(datos!!.id,datos!!.pregunta,datos!!.respuesta,datos!!.a,datos!!.b,datos!!.c,datos!!.d))
                }
                var count = Random.nextInt(0,Coleccion.size)
                binding.apply {
                    textPregunta.text =Coleccion[count].pregunta
                    textOpcionA.text = Coleccion[count].a
                    textOpcionB.text = Coleccion[count].b
                    textOpcionC.text = Coleccion[count].c
                    textOpcionD.text = Coleccion[count].d
                    textOpcionA.setOnClickListener {
                        if (Coleccion[count].a==Coleccion[count].respuesta){
                            dinero = 12500
                            dinero+=dinero
                            aciertos++
                            textGanado.text="Haz ganado: $ $dinero COP"
                            Toast.makeText(this@MainActivity5, "Acertaste la pregunta", Toast.LENGTH_SHORT).show()
                            textOpcionA.animate().setStartDelay(1000).setDuration(3000).withEndAction {
                                count = Random.nextInt(0,Coleccion.size)
                                textPregunta.text =Coleccion[count].pregunta
                                textOpcionA.text = Coleccion[count].a
                                textOpcionB.text = Coleccion[count].b
                                textOpcionC.text = Coleccion[count].c
                                textOpcionD.text = Coleccion[count].d
                            }.start()
                        }else{
                            intentos--
                            if (intentos==0){
                                if(aciertos==0){
                                    dinero=0
                                }
                                AlertDialog.Builder(binding.root.context)
                                    .setMessage("Felicidades has ganado $ $dinero COP Preguntas acertadas: ${aciertos}")
                                    .setPositiveButton("Ok") { view, b ->
                                        startActivity(Intent(this@MainActivity5,MainActivity2::class.java))
                                    }.create().show()
                            }else{
                                textOpcionA.animate().setStartDelay(1000).setDuration(3000).withEndAction {
                                    count = Random.nextInt(0,Coleccion.size)
                                    textPregunta.text =Coleccion[count].pregunta
                                    textOpcionA.text = Coleccion[count].a
                                    textOpcionB.text = Coleccion[count].b
                                    textOpcionC.text = Coleccion[count].c
                                    textOpcionD.text = Coleccion[count].d
                                }.start()
                            }
                        }
                    }
                    textOpcionB.setOnClickListener {
                        if (Coleccion[count].b==Coleccion[count].respuesta){
                            dinero+=dinero
                            aciertos++
                            textGanado.text="Haz ganado: $ $dinero COP"
                            textOpcionA.animate().setStartDelay(1000).setDuration(3000).withEndAction {
                                count = Random.nextInt(0,Coleccion.size)
                                textPregunta.text =Coleccion[count].pregunta
                                textOpcionA.text = Coleccion[count].a
                                textOpcionB.text = Coleccion[count].b
                                textOpcionC.text = Coleccion[count].c
                                textOpcionD.text = Coleccion[count].d
                            }.start()
                        }else{
                            intentos--
                            if (intentos==0){
                                AlertDialog.Builder(binding.root.context)
                                    .setMessage("Felicidades has ganado $ $dinero COP Preguntas acertadas: ${aciertos}")
                                    .setPositiveButton("Ok") { view, b ->
                                        startActivity(Intent(this@MainActivity5,MainActivity2::class.java))
                                    }.create().show()
                            }
                            Toast.makeText(this@MainActivity5, "Perdiste te quedan $intentos intentos", Toast.LENGTH_SHORT).show()
                            textOpcionA.animate().setStartDelay(1000).setDuration(3000).withEndAction {
                                    count = Random.nextInt(0,Coleccion.size)
                                    textPregunta.text =Coleccion[count].pregunta
                                    textOpcionA.text = Coleccion[count].a
                                    textOpcionB.text = Coleccion[count].b
                                    textOpcionC.text = Coleccion[count].c
                                    textOpcionD.text = Coleccion[count].d
                            }.start()

                        }
                    }
                    textOpcionC.setOnClickListener {
                        if (Coleccion[count].c==Coleccion[count].respuesta){
                            dinero+=dinero
                            aciertos++
                            textGanado.text="Haz ganado: $ $dinero COP"
                            textOpcionA.animate().setStartDelay(1000).setDuration(3000).withEndAction {
                                count = Random.nextInt(0,Coleccion.size)
                                textPregunta.text =Coleccion[count].pregunta
                                textOpcionA.text = Coleccion[count].a
                                textOpcionB.text = Coleccion[count].b
                                textOpcionC.text = Coleccion[count].c
                                textOpcionD.text = Coleccion[count].d
                            }.start()
                        }else{
                            intentos--
                            if (intentos==0){
                                if(aciertos==0){
                                    dinero=0
                                }
                                AlertDialog.Builder(binding.root.context)
                                    .setMessage("Felicidades has ganado $ $dinero COP Preguntas acertadas: ${aciertos}")
                                    .setPositiveButton("Ok") { view, b ->
                                        startActivity(Intent(this@MainActivity5,MainActivity2::class.java))
                                    }.create().show()
                            }else{
                                textOpcionA.animate().setStartDelay(1000).setDuration(3000).withEndAction {
                                    count = Random.nextInt(0,Coleccion.size)
                                    textPregunta.text =Coleccion[count].pregunta
                                    textOpcionA.text = Coleccion[count].a
                                    textOpcionB.text = Coleccion[count].b
                                    textOpcionC.text = Coleccion[count].c
                                    textOpcionD.text = Coleccion[count].d
                                }.start()
                            }
                        }
                    }
                    textOpcionD.setOnClickListener {
                        if (Coleccion[count].d==Coleccion[count].respuesta){
                            dinero+=dinero
                            aciertos++
                            textGanado.text="Haz ganado: $ $dinero COP"
                            textOpcionA.animate().setStartDelay(1000).setDuration(3000).withEndAction {
                                count = Random.nextInt(0,Coleccion.size)
                                textPregunta.text =Coleccion[count].pregunta
                                textOpcionA.text = Coleccion[count].a
                                textOpcionB.text = Coleccion[count].b
                                textOpcionC.text = Coleccion[count].c
                                textOpcionD.text = Coleccion[count].d
                            }.start()
                        }else{
                            intentos--
                            if (intentos==0){
                                if(aciertos==0){
                                    dinero=0
                                }
                                AlertDialog.Builder(binding.root.context)
                                    .setMessage("Felicidades has ganado $ $dinero COP Preguntas acertadas: ${aciertos}")
                                    .setPositiveButton("Ok") { view, b ->
                                        startActivity(Intent(this@MainActivity5,MainActivity2::class.java))
                                    }.create().show()
                            }else{
                                textOpcionA.animate().setStartDelay(1000).setDuration(3000).withEndAction {
                                    count = Random.nextInt(0,Coleccion.size)
                                    textPregunta.text =Coleccion[count].pregunta
                                    textOpcionA.text = Coleccion[count].a
                                    textOpcionB.text = Coleccion[count].b
                                    textOpcionC.text = Coleccion[count].c
                                    textOpcionD.text = Coleccion[count].d
                                }.start()
                            }
                        }
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        pantalla_Completa()
        }
    fun pantalla_Completa(){
        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.systemBars())
    }
    fun traerPreguntas(){
        preguntaRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var Coleccion:MutableList<preguntas> = mutableListOf()

                snapshot.children.forEach {
                    var datos=it.getValue(preguntas::class.java)

                    Coleccion.add(preguntas(datos!!.id,datos!!.pregunta,datos!!.respuesta,datos!!.a,datos!!.b,datos!!.c,datos!!.d))

                }
                var count = Random.nextInt(0,Coleccion.size)

                binding.apply {
                    textPregunta.text =Coleccion[count].pregunta
                    textOpcionA.text = Coleccion[count].a
                    textOpcionB.text = Coleccion[count].b
                    textOpcionC.text = Coleccion[count].c
                    textOpcionD.text = Coleccion[count].d

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    fun play() {
        binding.apply {
            lista= mutableListOf(preguntas("id","pregunta","respuesta","a","b","c","d"))
            preguntaRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach { snap ->
                        var preg = snap.getValue(preguntas::class.java)
                        var datos = preguntas(
                            preg!!.id,
                            preg!!.pregunta,
                            preg!!.respuesta,
                            preg!!.a,
                            preg!!.b,
                            preg!!.c,
                            preg!!.d
                        )
                        lista.add(datos)
                    }
                    textPregunta.text=lista[1].pregunta
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
    fun questios(count:Int): Int {
        binding.apply {
            textPregunta.text = lista[count].pregunta
            textOpcionA.text = lista[count].a
            textOpcionB.text = lista[count].b
            textOpcionC.text = lista[count].c
            textOpcionD.text = lista[count].d
            return count
        }
    }
}