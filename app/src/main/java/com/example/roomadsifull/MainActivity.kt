package com.example.roomadsifull

import android.database.sqlite.SQLiteConstraintException
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

            try {
//                var count = AppDatabase.GetDataBase(this@MainActivity)
//                    .genderDAO()
//                    .insert(Gender(3, "Otros 2"))
              var gender =   AppDatabase.GetDataBase(this@MainActivity).genderDAO().getAll().get(0)
                // AppDatabase.GetDataBase().genderDAO().delete(gender)

                var count = AppDatabase.GetDataBase().genderDAO().getAll().size
                runOnUiThread {
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