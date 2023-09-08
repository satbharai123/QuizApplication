package com.example.quizapplication
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val questions = listOf(
        "What is the capital of France?",
        "What is the largest planet in our solar system?",
        "Which gas do plants absorb for photosynthesis?"
    )

    private val answers = listOf(
        "Paris",
        "Jupiter",
        "Carbon dioxide"
    )

    private var currentQuestionIndex = 0
    private var score = 0

    private lateinit var questionTextView: TextView
    private lateinit var answersRadioGroup: RadioGroup
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.questionTextView)
        answersRadioGroup = findViewById(R.id.answersRadioGroup)
        resultTextView = findViewById(R.id.resultTextView)

        displayQuestion()

        findViewById<Button>(R.id.submitButton).setOnClickListener {
            checkAnswer()
        }
    }

    private fun displayQuestion() {
        questionTextView.text = questions[currentQuestionIndex]

        // Clear previous radio buttons
        answersRadioGroup.removeAllViews()

        // Add radio buttons for answer choices
        for (answer in answers.shuffled()) {
            val radioButton = RadioButton(this)
            radioButton.text = answer
            answersRadioGroup.addView(radioButton)
        }
    }

    private fun checkAnswer() {
        val selectedRadioButton = findViewById<RadioButton>(
            answersRadioGroup.checkedRadioButtonId
        )

        if (selectedRadioButton != null) {
            val selectedAnswer = selectedRadioButton.text.toString()
            val correctAnswer = answers[currentQuestionIndex]

            if (selectedAnswer == correctAnswer) {
                score++
            }

            currentQuestionIndex++

            if (currentQuestionIndex < questions.size) {
                displayQuestion()
            } else {
                displayResult()
            }
        }
    }

    private fun displayResult() {
        questionTextView.visibility = View.GONE
        answersRadioGroup.visibility = View.GONE
        findViewById<Button>(R.id.submitButton).visibility = View.GONE
        resultTextView.visibility = View.VISIBLE
        resultTextView.text = "Your score: $score/${questions.size}"
    }
}
