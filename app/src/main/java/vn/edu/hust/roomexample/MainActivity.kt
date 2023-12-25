package vn.edu.hust.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.edu.hust.roomexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val colorDao = ColorDatabase.getInstance(application).colorDao()

        binding.buttonInsert.setOnClickListener {
            val name = binding.editName.text.toString()
            val hex = binding.editHex.text.toString()
            val newColor = Color(name = name, hex = hex)
            lifecycleScope.launch(Dispatchers.IO) {
                val result = colorDao.insert(newColor)
                Log.v("TAG", "${result[0]}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity,
                        "New record inserted: ${result[0]}",
                        Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.buttonUpdate.setOnClickListener {
            val id = binding.editId.text.toString().toInt()
            val name = binding.editName.text.toString()
            val hex = binding.editHex.text.toString()
            val color = Color(_id = id, name = name, hex = hex)

            lifecycleScope.launch(Dispatchers.IO) {
                val result = colorDao.update(color)
                withContext(Dispatchers.Main) {
                    if (result > 0) {
                        Toast.makeText(this@MainActivity,
                            "Num record updated: ${result}",
                            Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@MainActivity,
                            "Update failed",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.buttonDelete.setOnClickListener {
            val id = binding.editId.text.toString().toInt()
            val color = Color(_id = id, name = "", hex = "")

            lifecycleScope.launch(Dispatchers.IO) {
                val result = colorDao.delete(color)
                withContext(Dispatchers.Main) {
                    if (result > 0) {
                        Toast.makeText(this@MainActivity,
                            "Delete successfully",
                            Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@MainActivity,
                            "Delete failed",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.buttonGetAll.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val result = colorDao.getAllColors()
                for (color in result) {
                    Log.v("TAG", "${color._id} - ${color.name} - ${color.hex}")
                }
            }
        }
    }
}