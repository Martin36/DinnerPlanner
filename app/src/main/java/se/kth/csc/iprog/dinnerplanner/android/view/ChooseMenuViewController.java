package se.kth.csc.iprog.dinnerplanner.android.view;

import android.view.View;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

/**
 * Created by jonas on 2016-02-22.
 */
public class ChooseMenuViewController implements View.OnClickListener {
  DinnerModel model;
  ChooseMenuView view;

  public ChooseMenuViewController(DinnerModel model, ChooseMenuView view){
    this.model = model;
    this.view = view;

  }


  @Override
  public void onClick(View view) {

  }
}
