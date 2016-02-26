package se.kth.csc.iprog.dinnerplanner.android.view;

import android.content.Intent;
import android.view.View;

import se.kth.csc.iprog.dinnerplanner.android.ChooseMenuActivity;
import se.kth.csc.iprog.dinnerplanner.android.RecipeActivity;

/**
 * Created by jonas on 2016-02-26.
 */
public class WelcomeScreenViewController implements View.OnClickListener{
  WelcomeScreenView view;

  public WelcomeScreenViewController(WelcomeScreenView view){
    this.view = view;
    this.view.createButton.setOnClickListener(this);

  }

  @Override
  public void onClick(View view) {
    Intent intent = new Intent();
    intent.setClass(view.getContext(), ChooseMenuActivity.class);
    view.getContext().startActivity(intent);

  }
}
