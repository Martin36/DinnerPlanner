package se.kth.csc.iprog.dinnerplanner.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import se.kth.csc.iprog.dinnerplanner.android.view.ChooseMenu;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

public class ChooseMenuActivity extends Activity {
  public static String PACKAGE_NAME;
  DinnerModel model;
  ChooseMenu viewClass;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_menu);
    model = ((DinnerPlannerApplication) this.getApplication()).getModel();
    PACKAGE_NAME = getApplicationContext().getPackageName();
    View view = findViewById(R.id.choose_menu_activity);
    viewClass = new ChooseMenu(view, model);
  }

}
