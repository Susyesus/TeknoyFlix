package cit.app.teknoyflix

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterPage : Activity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        val fullNameInput = findViewById<EditText>(R.id.FullName)
        val emailInput = findViewById<EditText>(R.id.Email)
        val passwordInput = findViewById<EditText>(R.id.Password)
        val confirmPasswordInput = findViewById<EditText>(R.id.ConfirmPassword)
        val signUpButton = findViewById<Button>(R.id.SignUp_button)
        val loginButton = findViewById<Button>(R.id.Login_redirect)

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        signUpButton.setOnClickListener {
            val fullName = fullNameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val confirmPassword = confirmPasswordInput.text.toString().trim()

            val savedEmail = sharedPreferences.getString("UserEmail", "")

            // ✅ Input validation
            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email == savedEmail) {
                Toast.makeText(this, "Email is already taken. Please use a different email.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Save user credentials
            editor.putString("UserFullName", fullName)
            editor.putString("UserEmail", email)
            editor.putString("UserPassword", password)
            editor.apply()

            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()

            // ✅ Redirect to Login page
            startActivity(Intent(this, Login::class.java))
            finish()
        }

        // ✅ Navigate back to Login page
        loginButton.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}