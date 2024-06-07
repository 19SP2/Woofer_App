package com.example.wooferapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Settings : AppCompatActivity() {

    private lateinit var editBreed : EditText
    private lateinit var editAge : EditText
    private lateinit var editGender : EditText
    private lateinit var editName : EditText
    private lateinit var save: Button
    private lateinit var clear : Button
    private lateinit var next : Button
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        editAge = findViewById(R.id.ageEditText)
        editBreed = findViewById(R.id.breedEditText)
        editGender = findViewById(R.id.genderEditText)
        editName = findViewById(R.id.nameEditText)

        save = findViewById(R.id.saveButton)
        clear = findViewById(R.id.clearButton)
        next = findViewById(R.id.next)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        save.setOnClickListener{
            val age = editAge.text.toString()
            val breed = editBreed.text.toString()
            val gender = editGender.text.toString()
            val name = editName.text.toString()
            val editor = sharedPreferences.edit()
            editor.putString("age", age)
            editor.putString("breed", breed)
            editor.putString("gender", gender)
            editor.putString("name", name)
            editor.apply()
        }

        clear.setOnClickListener{
            editAge.text.clear()
        }

        next.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}