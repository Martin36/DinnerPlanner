package se.kth.csc.iprog.dinnerplanner.android.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.android.ChooseMenuActivity;
import se.kth.csc.iprog.dinnerplanner.android.MainActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

/**
 * Created by martin on 2016-02-12.
 */
public class ChooseMenuView implements Observer{

  DinnerModel model;
  View view;
  public final ArrayList<ImageButton> buttons; //TODO: Create list of buttons.
  public final ArrayList<String> dishNames;
  LinearLayout starterRow, mainCourseRow, dessertRow;
  Button createButton;
  Spinner nrGuests;
  TextView totalCost;


  public ChooseMenuView(View view, DinnerModel model){
    this.view = view;
    this.model = model;
    model.addObserver(this);
    totalCost = (TextView) view.findViewById(R.id.total_cost_choose_menu);
    buttons = new ArrayList<ImageButton>();
    dishNames = new ArrayList<String>();
    createButton = (Button) view.findViewById(R.id.create_menu_button);
    //Code to create the dropdown for choosing nr of guests
    nrGuests = (Spinner)view.findViewById(R.id.spinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.participants,
        android.R.layout.simple_spinner_dropdown_item);
    //Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //Apply the adapter to the spinner
    nrGuests.setAdapter(adapter);
    //Instantiate the inflater
    LayoutInflater inflater = (LayoutInflater)view.getContext().getSystemService
        (Context.LAYOUT_INFLATER_SERVICE);
    HorizontalScrollView currentRowOfDishType = null;
    for(int i = 1; i <= 3; i++) {
      switch (i) {
        case 1:
          currentRowOfDishType = (HorizontalScrollView) view.findViewById(R.id.starters);
          break;
        case 2:
          currentRowOfDishType = (HorizontalScrollView) view.findViewById(R.id.main_courses);
          break;
        case 3:
          currentRowOfDishType = (HorizontalScrollView) view.findViewById(R.id.desserts);
          break;
        default:
          break;
      }
      Set<Dish> dishes = model.getDishesOfType(i);    //Since i = "the dish type" we can use it here
      LinearLayout tempLayout = new LinearLayout(view.getContext());
      tempLayout.setOrientation(LinearLayout.HORIZONTAL);

      if(!dishes.isEmpty()) {   //We don't want to add the dish type if there is non of it or it doesn't fit
        //Need to extract iterator to be able to excess elements in set
        for (Iterator<Dish> it = dishes.iterator(); it.hasNext(); ) {
          //The current dish
          Dish d = it.next();
          //Inflate the button into a linear layout
          View dishFrame = inflater.inflate(R.layout.course_images_layout, currentRowOfDishType, false);
          ImageButton imageButton = (ImageButton) dishFrame.findViewById(R.id.button);
          buttons.add(imageButton);   //Add button to list to be able to keep track
          dishNames.add(d.getName());
          String im = d.getImage();
          im = im.replace(".jpg", "");    //Need to remove the ".jpg" from image name
          //The ID for the drawable picture
          int imageID = view.getResources().getIdentifier(im, "drawable", ChooseMenuActivity.PACKAGE_NAME);
          //Add images to buttons
          imageButton.setImageResource(imageID);

          //Set the name of the dish
          //Retrieves the current text view where the name of the dish is put
          TextView textView = (TextView) dishFrame.findViewById(R.id.name);
          textView.setText(d.getName());

          //Add things to linear layout
          tempLayout.addView(dishFrame);
       }
      }
      currentRowOfDishType.addView(tempLayout);
      switch (i) {
        case 1:
          starterRow = tempLayout;
          break;
        case 2:
          mainCourseRow = tempLayout;
          break;
        case 3:
          dessertRow = tempLayout;
          break;
        default:
          break;
      }
    }
    update(model,"Dish Added To Menu");
  }

  @Override
  public void update(Observable observable, Object o) {
    if(o.toString().equalsIgnoreCase("Number of Guests Changed")){
      nrGuests.setSelection(model.getNumberOfGuests()-1);
    }
    if(o.toString().equalsIgnoreCase("Dish Added To Menu")){
      for (ImageButton imageButton : this.buttons){
        imageButton.setAlpha(1f);
      }
      for (Dish d : model.getFullMenu()){
        int index = this.dishNames.indexOf(d.getName());
        this.buttons.get(index).setAlpha(0.5f);
      }
    }
    changeTotalCost();

  }

  private void changeTotalCost(){
    NumberFormat nf = new DecimalFormat("##.##");
    totalCost.setText("Total Cost: " + nf.format(model.getTotalMenuPrice()) + "kr");
  }
}
