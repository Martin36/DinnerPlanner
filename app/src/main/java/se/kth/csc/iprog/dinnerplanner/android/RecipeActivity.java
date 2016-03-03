package se.kth.csc.iprog.dinnerplanner.android;

import android.os.Bundle;
import android.app.Activity;

import se.kth.csc.iprog.dinnerplanner.android.view.RecipeView;
import se.kth.csc.iprog.dinnerplanner.android.view.RecipeViewController;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

public class RecipeActivity extends Activity {
  public static String PACKAGE_NAME;
  DinnerModel model;
  RecipeView view;
  RecipeViewController controller;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe);
    PACKAGE_NAME = getApplicationContext().getPackageName();

    model = ((DinnerPlannerApplication) this.getApplication()).getModel();
    view = new RecipeView(findViewById(R.id.recipe_view), model);
    controller = new RecipeViewController(model, view);


  }

}
