package se.kth.csc.iprog.dinnerplanner.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import se.kth.csc.iprog.dinnerplanner.android.view.ChooseMenuView;
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
    model = ((DinnerPlannerApplication) this.getApplication()).getModel();
   // model = new DinnerModel();

    //Makes a request to change title bar
    this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    //Sets the screen to view the correct content
    setContentView(R.layout.choose_menu_view);
//    setContentView(R.layout.recipe_view);
    //Create static variable for package reference
    PACKAGE_NAME = getApplicationContext().getPackageName();

    View view = findViewById(R.id.choose_menu);
//    View view = findViewById(R.id.recipe_view);
    ChooseMenuView chooseMenu = new ChooseMenuView(view, model);
//    RecipeView recipeView = new RecipeView(view, model);
    //Sets the title bar
    this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
  }

}
