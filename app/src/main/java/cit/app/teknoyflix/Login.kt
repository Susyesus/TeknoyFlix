package cit.app.teknoyflix

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameInput = findViewById<EditText>(R.id.Email)
        val emailInput = findViewById<EditText>(R.id.Email)
        val passwordInput = findViewById<EditText>(R.id.Password)
        val loginButton = findViewById<Button>(R.id.Login_button)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // Validation logic
            if (isValidUsername(username) && isValidEmail(email) && password.isNotEmpty()) {
                if (isCorrectPassword(password)) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    // Navigate to LandingActivity
                    val intent = Intent(this, LandingPage::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Invalid username or password.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid username or password.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Helper function to validate username
    private fun isValidUsername(username: String): Boolean {
        val correctUser = "admin@gmail.com"
        return username == correctUser
    }

    // Helper function to validate email format
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Helper function to check if the password is correct (for demonstration purposes)
    private fun isCorrectPassword(password: String): Boolean {
        val correctPassword = "123"
        return password == correctPassword
    }
}