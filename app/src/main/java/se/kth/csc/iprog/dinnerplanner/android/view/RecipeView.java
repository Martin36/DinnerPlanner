package se.kth.csc.iprog.dinnerplanner.android.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.android.MainActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class RecipeView{

  DinnerModel model;
  View view;

  public RecipeView(View view, DinnerModel model){
    this.view = view;
    this.model = model;
    int totalCost = 0;    //The total cost to print
    int currentDishType;
    ImageButton imageButton = null;
    TextView textView = null;
    for(int i = 1; i <= 3; i++){
      Dish d = model.getSelectedDish(i);
      switch (i){
        case 1:
          imageButton = (ImageButton) view.findViewById(R.id.starter_button);
          textView = (TextView) view.findViewById(R.id.starter);
          break;
        case 2:
          imageButton = (ImageButton) view.findViewById(R.id.main_course_button);
          textView = (TextView) view.findViewById(R.id.main_course);
          break;
        case 3:
          imageButton = (ImageButton) view.findViewById(R.id.dessert_button);
          textView = (TextView) view.findViewById(R.id.dessert);
          break;
      }
      //Need to check if dish is null in case all three dishes isn't selected
      if(d != null){
        String im = d.getImage();
        //Need to remove the ".jpg" from image name
        im = im.replace(".jpg", "");

        //The ID for the drawable picture
        int imageID = view.getResources().getIdentifier(im, "drawable", MainActivity.PACKAGE_NAME);
        //Add images to buttons
        imageButton.setImageResource(imageID);
        textView.setText(d.getName());
      }
    }
  }
}