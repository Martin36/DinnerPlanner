package se.kth.csc.iprog.dinnerplanner.android.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import se.kth.csc.iprog.dinnerplanner.android.MainActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;

/**
 * Created by martin on 2016-02-12.
 */
public class ChooseMenu extends MainActivity{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_menu);
    //Create the spinner (drop down) object
    Spinner nrGuests = (Spinner)findViewById(R.id.spinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.participants,
        android.R.layout.simple_spinner_dropdown_item);
    //Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //Apply the adapter to the spinner
    nrGuests.setAdapter(adapter);
  }
}
