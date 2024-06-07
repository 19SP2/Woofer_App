package com.example.wooferapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Profile : AppCompatActivity() {

    private lateinit var homebtn : Button
    private lateinit var popupInstructions: ImageButton
    private lateinit var ageText : TextView
    private lateinit var breedText : TextView
    private lateinit var genderText : TextView
    private lateinit var nameText : TextView
    private lateinit var nameText2 : TextView
    private lateinit var edit : Button
    private lateinit var sharedPreferences : SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        popupInstructions = findViewById(R.id.informationButton) //on click listener for popup view - instructions screen
        popupInstructions.setOnClickListener{
            showPopupInstruct()
        }

        homebtn = findViewById(R.id.homebutton)
        homebtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        ageText = findViewById(R.id.age)
        breedText = findViewById(R.id.breed)
        genderText = findViewById(R.id.gender)
        nameText = findViewById(R.id.name)
        nameText2 = findViewById(R.id.inputName)

        edit = findViewById(R.id.button)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val textAge = sharedPreferences.getString("age", "")
        val textBreed = sharedPreferences.getString("breed", "")
        val textGender = sharedPreferences.getString("gender", "")
        val textName = sharedPreferences.getString("name", "")

        nameText.text = textName
        nameText2.text = textName
        ageText.text = textAge
        breedText.text = textBreed
        genderText.text = textGender

        edit.setOnClickListener{
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun showPopupInstruct() { //function to display popup view for instructions
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.activity_instructions, null)

        val width = 1080
        val height = 2220

        val instructWindow = PopupWindow(popupView, width, height, true)
        instructWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0) //location of the popup view

        val closeButton = popupView.findViewById<ImageButton>(R.id.close)
        closeButton.setOnClickListener {
            instructWindow.dismiss() //to close popup view
        }
    }
}