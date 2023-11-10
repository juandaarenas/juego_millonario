package com.example.juegomillonario.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.juegomillonario.databinding.ActivityMain4Binding
import com.example.juegomillonario.databinding.ItemrecyclerBinding
import com.example.juegomillonario.databinding.UpdatepreguntaBinding
import com.example.juegomillonario.modelos.preguntas
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class recyAdarter(var list: MutableList<preguntas>):
RecyclerView.Adapter<recyAdarter.PreguntaHolder>(){

    lateinit var fireDatabase: FirebaseDatabase
    lateinit var preguntaRef: DatabaseReference

    inner class PreguntaHolder(var binding: ItemrecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreguntaHolder {
        return PreguntaHolder(ItemrecyclerBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PreguntaHolder, position: Int) {
        fireDatabase = FirebaseDatabase.getInstance()
        preguntaRef = fireDatabase.getReference("datos")
        var pregunt = list[position];
        var binding = holder.binding
        pregunt.apply {
            binding.apply {
                txtName.text = pregunta
                textIDPregunta.text=id
                btnDelete.setOnClickListener {
                    AlertDialog.Builder(binding.root.context)
                        .setMessage("¿Desea eliminar la pregunta de ID: $id?")
                        .setPositiveButton("Ok") { view, b ->
                            preguntaRef.child(id).removeValue()
                            list.remove(pregunt)
                            this@recyAdarter.notifyItemRemoved(position)
                            Toast.makeText(binding.root.context, "Usted ha eliminado ha $id", Toast.LENGTH_SHORT).show()
                        }
                        .setNegativeButton("Cancel") { _, _ ->
                            Toast.makeText(binding.root.context, "Usted ha cancelado la eliminacion", Toast.LENGTH_SHORT).show()
                        }.create().show()
                }
                btnEdit.setOnClickListener {
                    var vinding: UpdatepreguntaBinding
                    vinding = UpdatepreguntaBinding.inflate(LayoutInflater.from(binding.root.context))
                    var alert = AlertDialog.Builder(binding.root.context).setView(vinding.root).create().show()
                    vinding.apply {
                        textpregunta.setText(pregunta)
                        textrespuesta.setText(respuesta)
                        textOpcA.setText(a)
                        textOpcB.setText(b)
                        textOpcC.setText(c)
                        textOpcD.setText(d)
                        btnUpdate.setOnClickListener {
                            var newQuest = HashMap<String, Any>()
                            newQuest["pregunta"] = textpregunta.text.toString()
                            var newResp = HashMap<String, Any>()
                            newResp["respuesta"] = textrespuesta.text.toString()
                            var newOpcA = HashMap<String, Any>()
                            newOpcA["a"] = textOpcA.text.toString()
                            var newOpcB = HashMap<String, Any>()
                            newOpcB["b"] = textOpcB.text.toString()
                            var newOpcC = HashMap<String, Any>()
                            newOpcC["c"] = textOpcC.text.toString()
                            var newOpcD = HashMap<String, Any>()
                            newOpcD["d"] = textOpcD.text.toString()
                            preguntaRef.child(id).updateChildren(newQuest)
                            preguntaRef.child(id).updateChildren(newResp)
                            preguntaRef.child(id).updateChildren(newOpcA)
                            preguntaRef.child(id).updateChildren(newOpcB)
                            preguntaRef.child(id).updateChildren(newOpcC)
                            preguntaRef.child(id).updateChildren(newOpcD)
                            Toast.makeText(binding.root.context, "actualizado", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}