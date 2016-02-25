package se.kth.csc.iprog.dinnerplanner.android.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;

import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.android.RecipeActivity;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class RecipeView implements Observer{

  DinnerModel model;
  View view;
  public ImageButton ingredientsButton, starterButton,
              mainCourseButton, dessertButton;
  public Button backButton;

  public RecipeView(View view, DinnerModel model){
    this.view = view;
    this.model = model;
    model.addObserver(this);    //Notify the model that this class now observes it.
  //  buttons = new ArrayList<ImageButton>();
    ingredientsButton = (ImageButton) view.findViewById(R.id.ingredient_button);
    starterButton = (ImageButton) view.findViewById(R.id.starter_button);
    mainCourseButton = (ImageButton) view.findViewById(R.id.main_course_button);
    dessertButton = (ImageButton) view.findViewById(R.id.dessert_button);
    backButton = (Button) view.findViewById(R.id.back_button);
    update(model, null);
  }
  //This method is called every time the notifyObservers method is called in the model class
  @Override
  public void update(Observable observable, Object o) {
    TextView cost = (TextView) view.findViewById(R.id.cost);
    NumberFormat nf = new DecimalFormat("##.##");
    cost.setText("Total Cost: " + nf.format(model.getTotalMenuPrice()) + "kr");
    TextView description = (TextView) view.findViewById(R.id.description);
    description.setText(model.getDescription());
    TextView ingAmount = (TextView) view.findViewById(R.id.amount);
    ingAmount.setText(model.getIngredientAmount());
    TextView descriptionTitle = (TextView) view.findViewById(R.id.description_title);
    descriptionTitle.setText(model.getDescriptionTitle());
    //Set the dish images and names
    ImageButton imageButton = null;
    TextView textView = null;
    for(int i = 1; i <= 3; i++) {
      Dish d = model.getSelectedDish(i);
      switch (i) {
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
      if (d != null) {
        //Need to check if dish is null in case all three dishes isn't selected
        String im = d.getImage();
        //Need to remove the ".jpg" from image name
        im = im.replace(".jpg", "");

        //The ID for the drawable picture
        int imageID = view.getResources().getIdentifier(im, "drawable", RecipeActivity.PACKAGE_NAME);
        //Add images to buttons
        imageButton.setImageResource(imageID);
        textView.setText(d.getName());
      }
      else{
        imageButton.setEnabled(false);
      }
    }

  }
}
