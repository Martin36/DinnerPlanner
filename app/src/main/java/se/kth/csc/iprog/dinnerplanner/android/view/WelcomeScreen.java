package se.kth.csc.iprog.dinnerplanner.android.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import se.kth.csc.iprog.dinnerplanner.android.MainActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;


public class WelcomeScreen extends MainActivity{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome_screen);

  }
}
