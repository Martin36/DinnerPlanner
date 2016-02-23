package se.kth.csc.iprog.dinnerplanner.android.view;

import android.view.View;

import se.kth.csc.iprog.dinnerplanner.model.Dish;


public class CourseButton{
  Dish dish;
  View view;
  public  CourseButton(Dish dish, View view){
    this.dish = dish;
    this.view = view;

    //Need to check if dish is null in case all three dishes isn't selected
    String im = dish.getImage();
    //Need to remove the ".jpg" from image name
    im = im.replace(".jpg", "");


  }
}
