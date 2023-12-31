package com.isettozeur.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var totalQuestionsTextView: TextView? = null
    private var questionTextView: TextView? = null
    private var ansA: Button? = null
    private var ansB: Button? = null
    private var ansC: Button? = null
    private var ansD: Button? = null
    private var submitBtn: Button? = null
    private var score = 0
    private val totalQuestion = QuestionAnswer.question.size

    private var currentQuestionIndex = 0
    private var selectedAnswer = ""
    private var userName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if user name is provided, if not, start NameInputActivity
        userName = intent.getStringExtra("USER_NAME").toString()
        if (userName.isNullOrEmpty()) {
            val intent = Intent(this, NameInputActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        totalQuestionsTextView = findViewById(R.id.total_question)
        questionTextView = findViewById(R.id.question)
        ansA = findViewById(R.id.ans_A)
        ansB = findViewById(R.id.ans_B)
        ansC = findViewById(R.id.ans_C)
        ansD = findViewById(R.id.ans_D)
        submitBtn = findViewById(R.id.submit_btn)

        ansA?.setOnClickListener(this)
        ansB?.setOnClickListener(this)
        ansC?.setOnClickListener(this)
        ansD?.setOnClickListener(this)
        submitBtn?.setOnClickListener(this)

        totalQuestionsTextView?.text = getString(R.string.total_questions, totalQuestion)
        loadNewQuestion()
    }

    override fun onClick(view: View) {
        resetButtonColors()

        when (view.id) {
            R.id.submit_btn -> {
                if (selectedAnswer == QuestionAnswer.correctAnswers.getOrNull(currentQuestionIndex)) {
                    score++
                }
                currentQuestionIndex++
                loadNewQuestion()
            }
            else -> {
                selectedAnswer = (view as Button).text.toString()
                view.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
            }

        }
    }

    private fun resetButtonColors() {
        val tealColor = ContextCompat.getColor(this, R.color.teal_200)

        ansA?.setBackgroundColor(tealColor)
        ansB?.setBackgroundColor(tealColor)
        ansC?.setBackgroundColor(tealColor)
        ansD?.setBackgroundColor(tealColor)
    }


    private fun loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion) {
            finishQuiz()
            return
        }
        questionTextView?.text = QuestionAnswer.question.getOrNull(currentQuestionIndex)
        val choices = QuestionAnswer.choices.getOrNull(currentQuestionIndex)
        ansA?.text = choices?.getOrNull(0)
        ansB?.text = choices?.getOrNull(1)
        ansC?.text = choices?.getOrNull(2)
        ansD?.text = choices?.getOrNull(3)
    }

    private fun finishQuiz() {
        val passStatus = if (score > totalQuestion * 0.60) "Passed" else "Failed"
        val message = "Bonjour $userName, votre score est de $score sur $totalQuestion"

        AlertDialog.Builder(this)
            .setTitle(passStatus)
            .setMessage(message)
            .setPositiveButton("Recommencer") { _, _ -> restartQuiz() }
            .setCancelable(false)
            .show()
    }

    private fun restartQuiz() {
        score = 0
        currentQuestionIndex = 0
        loadNewQuestion()
    }
}
