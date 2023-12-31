package com.isettozeur.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NameInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_input)

        val nameInput = findViewById<EditText>(R.id.name_input)
        val startQuizButton = findViewById<Button>(R.id.start_quiz_button)

        startQuizButton.setOnClickListener {
            val userName = nameInput.text.toString()

            val intent = Intent(this@NameInputActivity, MainActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            startActivity(intent)
            finish()
        }
    }
}
