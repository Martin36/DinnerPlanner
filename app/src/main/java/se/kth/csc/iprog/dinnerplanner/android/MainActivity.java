package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import se.kth.csc.iprog.dinnerplanner.android.view.ChooseMenu;
import se.kth.csc.iprog.dinnerplanner.android.view.ExampleView;
import se.kth.csc.iprog.dinnerplanner.android.view.WelcomeScreen;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

/**
 * Parent class for making the custom title bar
 */
public class MainActivity extends Activity {
  public static String PACKAGE_NAME;
  DinnerModel model;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    model = new DinnerModel();

    //Makes a request to change title bar
    this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    //Sets the screen to view the correct content
//    setContentView(R.layout.activity_welcome_screen);
    setContentView(R.layout.activity_choose_menu);
    //Create static variable for package reference
    PACKAGE_NAME = getApplicationContext().getPackageName();

    View view = findViewById(R.id.choose_menu);
    ChooseMenu chooseMenu = new ChooseMenu(view, model);

    //Sets the title bar
    this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
  }

}
