package se.kth.csc.iprog.dinnerplanner.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import se.kth.csc.iprog.dinnerplanner.android.view.ChooseMenuView;
import se.kth.csc.iprog.dinnerplanner.android.view.ChooseMenuViewController;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
/*
TODO Fix horizontal scrolls
 */
public class ChooseMenuActivity extends Activity {
  public static String PACKAGE_NAME;
  DinnerModel model;
  ChooseMenuView view;
  ChooseMenuViewController controller;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_menu);
    model = ((DinnerPlannerApplication) this.getApplication()).getModel();
    PACKAGE_NAME = getApplicationContext().getPackageName();
    view = new ChooseMenuView(findViewById(R.id.choose_menu_activity), model);
    controller = new ChooseMenuViewController(model, view);
  }

}
