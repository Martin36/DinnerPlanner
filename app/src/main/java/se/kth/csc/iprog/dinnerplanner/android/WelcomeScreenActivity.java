package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import se.kth.csc.iprog.dinnerplanner.android.MainActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.android.view.WelcomeScreenView;
import se.kth.csc.iprog.dinnerplanner.android.view.WelcomeScreenViewController;


public class WelcomeScreenActivity extends Activity{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome_screen);
    WelcomeScreenView view = new WelcomeScreenView(findViewById(R.id.welcome_screen));
    WelcomeScreenViewController controller = new WelcomeScreenViewController(view);

  }
}
