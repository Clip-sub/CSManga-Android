package com.clipsub.csmanga.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.clipsub.csmanga.R

class MainActivity : AppCompatActivity() {
  private val IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}
