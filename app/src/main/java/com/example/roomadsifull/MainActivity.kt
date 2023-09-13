package com.example.roomadsifull

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.roomadsifull.database.AppDatabase
import com.example.roomadsifull.models.Gender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  setNumer(1,2,3,4,5,6,7,8)

        CoroutineScope(Dispatchers.IO).launch {

          var count = AppDatabase.GetDataBase(this@MainActivity)
              .genderDAO()
              .insert(Gender(0, "Otros"))
            runOnUiThread {
                Toast.makeText(this@MainActivity, "$count", Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun setNumer(vararg num : Int){

    }
}