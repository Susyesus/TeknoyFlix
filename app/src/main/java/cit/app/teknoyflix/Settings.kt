package cit.app.teknoyflix

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class Settings: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val settingsListView: ListView = findViewById(R.id.settings_list_view)
        val settingsOptions = resources.getStringArray(R.array.settings_items)


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, settingsOptions)
        settingsListView.adapter = adapter

        settingsListView.setOnItemClickListener { _, _, position, _ ->
            val selectedOption = settingsOptions[position]
            if(selectedOption == "Back"){
                val intent = Intent(this,Profile::class.java)
                startActivity(intent)
            }
        }
    }
}
