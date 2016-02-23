package se.kth.csc.iprog.dinnerplanner.android.view;


import android.view.View;

import java.util.Iterator;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

public class RecipeViewController implements View.OnClickListener {
  DinnerModel model;
  RecipeView view;

  public RecipeViewController(DinnerModel model, RecipeView view){
    this.model = model;
    this.view = view;
    this.view.ingredientsButton.setOnClickListener(this);
    this.view.starterButton.setOnClickListener(this);
    this.view.mainCourseButton.setOnClickListener(this);
    this.view.dessertButton.setOnClickListener(this);

  }
  @Override
  public void onClick(View view) {
    if(view == this.view.ingredientsButton){
      Set<Ingredient> ingredients = model.getAllIngredients();
      StringBuilder stringBuilder = new StringBuilder();

      for(Iterator<Ingredient> it = ingredients.iterator(); it.hasNext();){
        Ingredient i = it.next();
        stringBuilder.append(i.getName() + "\t");
        stringBuilder.append(i.getQuantity());
        stringBuilder.append(i.getUnit() + "\n");
      }
      model.setDescription(stringBuilder.toString());
      model.setDescriptionTitle("Ingredients");
    }
    if(view == this.view.starterButton){
      model.setDescription(model.getSelectedDish(Dish.STARTER).getDescription());
      model.setDescriptionTitle("Starter");
    }
    if(view == this.view.mainCourseButton){
      model.setDescription(model.getSelectedDish(Dish.MAIN).getDescription());
      model.setDescriptionTitle("Main Course");

    }
    if(view == this.view.dessertButton){
      model.setDescription(model.getSelectedDish(Dish.DESERT).getDescription());
      model.setDescriptionTitle("Dessert");
    }
    if(view == this.view.backButton){
      //TODO: Change to previous screen by switching activity.
    }

  }
}
