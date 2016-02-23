package se.kth.csc.iprog.dinnerplanner.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import se.kth.csc.iprog.dinnerplanner.android.view.ChooseMenuView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
/*
TODO Fix horizontal scrolls
 */
public class ChooseMenuActivity extends Activity {
  public static String PACKAGE_NAME;
  DinnerModel model;
  ChooseMenuView viewClass;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_menu);
    model = ((DinnerPlannerApplication) this.getApplication()).getModel();
    PACKAGE_NAME = getApplicationContext().getPackageName();
    View view = findViewById(R.id.choose_menu_activity);
    viewClass = new ChooseMenuView(view, model);
  }

}
