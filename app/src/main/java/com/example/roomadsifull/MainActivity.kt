package com.example.roomadsifull

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomadsifull.adapter.GenderAdapter
import com.example.roomadsifull.database.AppDatabase
import com.example.roomadsifull.databinding.ActivityMainBinding
import com.example.roomadsifull.helper.Util
import com.example.roomadsifull.models.Gender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    fun edit(gender: Gender){

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnShow.setOnClickListener {
            var gender = binding.spinner.selectedItem as Gender
            Toast.makeText(this@MainActivity, "${gender.name}", Toast.LENGTH_SHORT).show()
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var list = AppDatabase.GetDataBase(this@MainActivity).genderDAO().getAll()


                list = AppDatabase.GetDataBase(this@MainActivity).genderDAO().getAll()
                var count = AppDatabase.GetDataBase().genderDAO().getAll().size

                runOnUiThread {
                    binding.recycle.layoutManager  = LinearLayoutManager(this@MainActivity)
                    binding.recycle.adapter = GenderAdapter(list.toMutableList()){ gender ->
                        gender.name = "Update"
                        Util.gender = gender
                        AppDatabase.GetDataBase().genderDAO().update(gender)

                    }
                    var adapter = object : ArrayAdapter<Gender>(
                        this@MainActivity,
                        android.R.layout.simple_spinner_dropdown_item,
                        list
                    ){
                        override fun getItemId(position: Int): Long {
                            return  getItem(position)?.id ?: 0
                        }
                    }
                    binding.spinner.adapter = adapter

                    binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            position: Int,
                            id: Long
                        ) {
                            Toast.makeText(this@MainActivity, "$position $id ", Toast.LENGTH_SHORT).show()
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {

                        }
                    }
                    Toast.makeText(this@MainActivity, "$count", Toast.LENGTH_SHORT).show()
                }
            }catch (e: SQLiteConstraintException){
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "ya existe un registro con el mismo nombre", Toast.LENGTH_SHORT).show()
                }
            }catch (e:Exception){
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }
            }


        }

    }
    fun setNumer(vararg num : Int){

    }
}
