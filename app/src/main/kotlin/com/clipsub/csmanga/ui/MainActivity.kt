package com.clipsub.csmanga.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.clipsub.csmanga.R

class MainActivity : AppCompatActivity() {
  private val IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/"
  private lateinit var button : Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    button = findViewById(R.id.btn_test)
    button.setOnClickListener { v -> Toast.makeText(this, "Test Test", Toast.LENGTH_LONG).show() }
  }
}
