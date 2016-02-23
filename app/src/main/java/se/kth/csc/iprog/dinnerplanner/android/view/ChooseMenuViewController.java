package se.kth.csc.iprog.dinnerplanner.android.view;

import android.view.View;
import android.widget.ImageButton;

import java.util.Iterator;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class ChooseMenuViewController implements View.OnClickListener {
  DinnerModel model;
  ChooseMenuView view;

  public ChooseMenuViewController(DinnerModel model, ChooseMenuView view){
    this.model = model;
    this.view = view;
    for(ImageButton imageButton: this.view.buttons){
      imageButton.setOnClickListener(this);
    }
  }


  @Override
  public void onClick(View view) {
    int index = this.view.buttons.indexOf(view);
    Set<Dish> dishes = model.getDishes();
    Dish d = null;
    for(Iterator<Dish> it = dishes.iterator(); it.hasNext();){
      d = it.next();
      if(d.getName() == this.view.dishNames.get(index)) {
        //The set cannot be modified while using the iterator
        //So the dish needs to be added outside the iteration
        break;
      }
    }
    model.addDishToMenu(d);

  }
}
