package cit.app.teknoyflix

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button


class AboutPage : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_page)
        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this,Settings::class.java)
            startActivity(intent)
        }
    }
}