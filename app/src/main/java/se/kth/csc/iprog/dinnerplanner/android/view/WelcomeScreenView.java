package se.kth.csc.iprog.dinnerplanner.android.view;

import android.view.View;
import android.widget.Button;

import se.kth.csc.iprog.dinnerplanner.android.R;

/**
 * Created by jonas on 2016-02-26.
 */
public class WelcomeScreenView {
  View view;
  Button createButton;

  public WelcomeScreenView(View view){
    this.view = view;
    createButton = (Button) view.findViewById(R.id.start_button);

  }
}
