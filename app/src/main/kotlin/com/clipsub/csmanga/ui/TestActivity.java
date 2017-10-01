package com.clipsub.csmanga.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.clipsub.csmanga.R;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

  private Button button;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);
    button = findViewById(R.id.btn_test);
    button.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {

  }
}
