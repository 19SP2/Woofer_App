package com.example.wooferapp

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

class MainActivity : AppCompatActivity() {

    private lateinit var popupInstructions: ImageButton
    private lateinit var article1btn : Button
    private lateinit var article2btn : Button
    private lateinit var article3btn : Button
    private lateinit var profilebtn : Button
    private lateinit var nameText : TextView
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        popupInstructions = findViewById(R.id.informationButton) //on click listener for popup view - instructions screen
        popupInstructions.setOnClickListener{
            showPopupInstruct()
        }

        nameText = findViewById(R.id.inputName)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val textName = sharedPreferences.getString("name", "")
        nameText.text = textName

        article1btn = findViewById(R.id.articleButton1)
        article1btn.setOnClickListener{
            val intent = Intent(this, Article1::class.java)
            startActivity(intent)
        }

        article2btn = findViewById(R.id.articleButton2)
        article2btn.setOnClickListener{
            val intent = Intent(this, Article2::class.java)
            startActivity(intent)
        }

        article3btn = findViewById(R.id.articleButton3)
        article3btn.setOnClickListener{
            val intent = Intent(this, Article3::class.java)
            startActivity(intent)
        }

        profilebtn = findViewById(R.id.profilebutton)
        profilebtn.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
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