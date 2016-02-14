package se.kth.csc.iprog.dinnerplanner.android.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.Iterator;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.android.MainActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

/**
 * Created by martin on 2016-02-12.
 */
public class ChooseMenu{

  DinnerModel model;
  View view;
/*
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_menu);
    //Create the spinner (drop down) object
    Spinner nrGuests = (Spinner)view.findViewById(R.id.spinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.participants,
        android.R.layout.simple_spinner_dropdown_item);
    //Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //Apply the adapter to the spinner
    nrGuests.setAdapter(adapter);
  }
  */
  public ChooseMenu(View view, DinnerModel model){
    this.view = view;
    this.model = model;
    Spinner nrGuests = (Spinner)view.findViewById(R.id.spinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.participants,
        android.R.layout.simple_spinner_dropdown_item);
    //Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //Apply the adapter to the spinner
    nrGuests.setAdapter(adapter);

    //Counter for the button ID
    int buttonCounter;
    //The row to put the dishes on
    LinearLayout currentRowOfDishType = null;
    //Set the right images to the buttons
    for(int i = 1; i <= 3; i++){
      switch (i){
        case 1:
          currentRowOfDishType = (LinearLayout) view.findViewById(R.id.starters);
          break;
        case 2:
          currentRowOfDishType = (LinearLayout) view.findViewById(R.id.main_courses);
          break;
        case 3:
          currentRowOfDishType = (LinearLayout) view.findViewById(R.id.desserts);
          break;
        default:
          break;
      }

      buttonCounter = 1;
      Set<Dish> dishes = model.getDishesOfType(i);    //Since i = "the dish type" we can use it here
      if(!dishes.isEmpty() && dishes.size() <= 4) {   //We don't want to add the dish type if there is non of it or it doesn't fit
        //Need to extract iterator to be able to excess elements in set
        for (Iterator<Dish> it = dishes.iterator(); it.hasNext(); ) {
          //The current dish
          Dish d = it.next();
          //The "number" of the current button
          String buttonID = "button" + buttonCounter;
          //Get button id
          int resID = currentRowOfDishType.getResources().getIdentifier(buttonID, "id", MainActivity.PACKAGE_NAME);
          ImageButton imageButton = (ImageButton) currentRowOfDishType.findViewById(resID);

          String im = d.getImage();
          //Need to remove the ".jpg" from image name
          im = im.replace(".jpg", "");

          //The ID for the drawable picture
          int imageID = view.getResources().getIdentifier(im, "drawable", MainActivity.PACKAGE_NAME);
          //Add images to buttons
          imageButton.setImageResource(imageID);
        }
      }
    }
  }
}
