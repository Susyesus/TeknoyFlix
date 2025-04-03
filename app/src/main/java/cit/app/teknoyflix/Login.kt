package cit.app.teknoyflix

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.Email)
        val passwordInput = findViewById<EditText>(R.id.Password)
        val loginButton = findViewById<Button>(R.id.Login_button)
        val createAccountButton = findViewById<Button>(R.id.Register_button)

        // Make sure createAccountButton isn't null
        if (createAccountButton == null) {
            Toast.makeText(this, "Error: Create Account Button not found", Toast.LENGTH_SHORT).show()
            return
        }

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("UserEmail", null)
        val savedPassword = sharedPreferences.getString("UserPassword", null)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            // Input validation
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check saved credentials
            if (email == savedEmail && password == savedPassword) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

                // Redirect to LandingPage
                startActivity(Intent(this, LandingPage::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate to RegisterPage
        createAccountButton.setOnClickListener {
            try {
                startActivity(Intent(this, RegisterPage::class.java))
            } catch (e: Exception) {
                Toast.makeText(this, "Error navigating to RegisterPage: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}