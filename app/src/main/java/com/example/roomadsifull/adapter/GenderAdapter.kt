package com.example.roomadsifull.adapter

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.roomadsifull.database.AppDatabase
import com.example.roomadsifull.databinding.ItemGenderBinding
import com.example.roomadsifull.models.Gender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenderAdapter(var list: MutableList<Gender>, var edit : (gender: Gender) -> Unit) :
    RecyclerView.Adapter<GenderAdapter.GenderHolder>() {
    inner class GenderHolder(var binding: ItemGenderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenderHolder {
        return GenderHolder(ItemGenderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GenderHolder, position: Int) {

        var gender = list[position];
        var binding = holder.binding
        gender.apply {
            binding.apply {
                txtName.text = name
                btnEdit.setOnClickListener {
                    edit.invoke(gender)
                }
                btnDelete.setOnClickListener {
                    AlertDialog.Builder(binding.root.context)
                        .setMessage("¿Desea eliminar el genero $name?")
                        .setPositiveButton("Si") { view, b ->
                            CoroutineScope(Dispatchers.IO).launch {
                                AppDatabase.GetDataBase(binding.root.context).genderDAO().delete(gender)
                                (binding.root.context as Activity).runOnUiThread {
                                    list.remove(gender)
                                    this@GenderAdapter.notifyItemRemoved(position)
                                }
                            }


                        }
                        .setNegativeButton("No") { _, _ ->
                            Toast.makeText(binding.root.context, "se cancelo", Toast.LENGTH_SHORT)
                                .show()
                        }.create().show()
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}