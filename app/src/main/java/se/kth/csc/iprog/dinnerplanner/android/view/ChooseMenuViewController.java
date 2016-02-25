package se.kth.csc.iprog.dinnerplanner.android.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.Iterator;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.android.RecipeActivity;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class ChooseMenuViewController implements View.OnClickListener {
  DinnerModel model;
  ChooseMenuView view;

  public ChooseMenuViewController(final DinnerModel model, ChooseMenuView view){
    this.model = model;
    this.view = view;

    for(ImageButton imageButton: this.view.buttons){
      imageButton.setOnClickListener(this);
    }
    view.createButton.setOnClickListener(this);
    //Add the functionallity to the drop down list
    view.nrGuests.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String guests = adapterView.getItemAtPosition(i).toString();
        int nrGuests = Integer.parseInt(guests);
        model.setNumberOfGuests(nrGuests);
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
  }


  @Override
  public void onClick(View view) {
    if(view == this.view.createButton){
      Intent intent = new Intent();
      intent.setClass(view.getContext(), RecipeActivity.class);
      view.getContext().startActivity(intent);
    }
    else {
      int index = this.view.buttons.indexOf(view);
      Set<Dish> dishes = model.getDishes();
      Dish d = null;
      for (Iterator<Dish> it = dishes.iterator(); it.hasNext(); ) {
        d = it.next();
        if (d.getName() == this.view.dishNames.get(index)) {
          //Inflate the popup
          LinearLayout viewGroup = (LinearLayout) view.findViewById(R.id.popup);
          LayoutInflater layoutInflater = (LayoutInflater) view.getContext()
              .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

          //Create the popup
          PopupWindow popup = new PopupWindow(view);
          popup.setContentView(layout);
          popup.setWidth(1100);
          popup.setHeight(1500);
          popup.setFocusable(true);


          //Displaying the popup at specified location
          popup.showAtLocation(layout, Gravity.CENTER,0,0);
          //The set cannot be modified while using the iterator
          //So the dish needs to be added outside the iteration
          break;
        }
      }
      model.addDishToMenu(d);
    }
  }
}
