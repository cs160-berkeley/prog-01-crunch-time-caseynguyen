package com.casey.exerconvert;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private Spinner unitTypeSpinner;

    private EditText amountTextView;

    TextView caloriesTextView;


    TextView PushupTextView, SitupTextView, Jumping_jackTextView, CyclingTextView,
            Stair_climbingTextView, WalkingTextView, PullupTextView, JoggingTextView,
            Leg_liftTextView, SwimmingTextView, PlankTextView, SquatsTextView,
            RepMintextView;

    double amountExer = 0; // Exercise data amount
    int amountInvalid ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fills the spinner with the unit options
        addItemsSpinner();

        // Add listener to the Spinner
        addListenerSpinner();


        // Get a reference to the edit text view to retrieve the amount of the unit type
        caloriesTextView = (TextView) findViewById(R.id.calories_text_view);

        // Get a reference to the edit text view to retrieve the amount of the unit type
        amountTextView = (EditText) findViewById(R.id.amount_text_view);

        initializeTextViews();

        // RepMin update
        RepMintextView = (TextView) findViewById(R.id.RepMintextView);

        amountTextView.addTextChangedListener(amountExerListener);


    }
    // Called when the bill before tip amount is changed

    private TextWatcher amountExerListener = new TextWatcher(){

        @Override
        public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {

            try{

                // Change the amount  to the new input

                amountExer = Double.parseDouble(arg0.toString());

                amountInvalid = 0 ;
            }

            catch(NumberFormatException e){

                amountExer = 0.0;
                amountInvalid = 1 ;


            }
            if( amountInvalid == 0 ) {

                String itemSelectedInSpinner = unitTypeSpinner.getSelectedItem().toString();
                chkIfConvertFrPushup(itemSelectedInSpinner);
            }

        }

    };

    public void initializeTextViews(){

        PushupTextView = (TextView) findViewById(R.id.Pushup_text_view);
        SitupTextView = (TextView) findViewById(R.id.Situp_text_view);
        Jumping_jackTextView = (TextView) findViewById(R.id.Jumping_jack_text_view);
        CyclingTextView = (TextView) findViewById(R.id.Cycling_text_view);
        Stair_climbingTextView = (TextView) findViewById(R.id.Stair_climbing_text_view);
        WalkingTextView = (TextView) findViewById(R.id.Walking_text_view);
        PullupTextView = (TextView) findViewById(R.id.Pullup_text_view);
        JoggingTextView = (TextView) findViewById(R.id.Jogging_text_view);
        Leg_liftTextView = (TextView) findViewById(R.id.Leg_lift_text_view);
        SwimmingTextView = (TextView) findViewById(R.id.Swimming_text_view);
        PlankTextView = (TextView) findViewById(R.id.Plank_text_view);
        SquatsTextView = (TextView) findViewById(R.id.Squats_text_view);

    }

    public void addItemsSpinner(){

        // Get a reference to the spinner
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.selection_t, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);

    }

    public void addListenerSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {
                // Get the item selected in the Spinner
                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString();

                // Verify if I'm converting from Pushup so that I use the right
                // conversion algorithm

                if (amountInvalid == 0) {

                    chkIfConvertFrPushup(itemSelectedInSpinner);
                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // ..

            }
        });
    }

    public void chkIfConvertFrPushup(String currentUnit){

        if(currentUnit.equals("Pushup")){

            updateUnitTwPushup(Convert.Unit.Pushup);

            RepMintextView.setText("reps");

        } else {

            if(currentUnit.equals("Situp")){

                updateUnitTwOther(Convert.Unit.Situp);
                RepMintextView.setText("reps");


            } else if(currentUnit.equals("Jumping_jack")){

                updateUnitTwOther(Convert.Unit.Jumping_jack);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Cycling")){

                updateUnitTwOther(Convert.Unit.Cycling);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Stair_climbing")){

                updateUnitTwOther(Convert.Unit.Stair_climbing);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Walking")){

                updateUnitTwOther(Convert.Unit.Walking);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Pullup")){

                updateUnitTwOther(Convert.Unit.Pullup);
                RepMintextView.setText("reps");

            } else if(currentUnit.equals("Jogging")){

                updateUnitTwOther(Convert.Unit.Jogging);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Leg_lift")){

                updateUnitTwOther(Convert.Unit.Leg_lift);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Swimming")){

                updateUnitTwOther(Convert.Unit.Swimming);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Plank")){

                updateUnitTwOther(Convert.Unit.Plank);
                RepMintextView.setText("mins");

            } else {

                updateUnitTwOther(Convert.Unit.Squats);
                RepMintextView.setText("mins");

            }

        }

    }

    public void updateUnitTwPushup(Convert.Unit currentUnit){
        double doubleToConvert;
        // Convert the value in the EditText box to a double
        if (amountInvalid == 1 ) {
            doubleToConvert = 0.0;


        } else {
            doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        }

        // Combine value to unit
        String PushupValueAndUnit = doubleToConvert + " Pushup";

        // Change the value for the Pushup TextView
        PushupTextView.setText(PushupValueAndUnit);

        // Update all the Unit Text Fields
        updateUnitFwPushup(doubleToConvert, Convert.Unit.Situp, SitupTextView);
        updateUnitFwPushup(doubleToConvert, Convert.Unit.Jumping_jack, Jumping_jackTextView);
        updateUnitFwPushup(doubleToConvert, Convert.Unit.Cycling, CyclingTextView);
        updateUnitFwPushup(doubleToConvert, Convert.Unit.Stair_climbing, Stair_climbingTextView);
        updateUnitFwPushup(doubleToConvert, Convert.Unit.Walking, WalkingTextView);
        updateUnitFwPushup(doubleToConvert, Convert.Unit.Pullup, PullupTextView);
        updateUnitFwPushup(doubleToConvert, Convert.Unit.Jogging, JoggingTextView);
        updateUnitFwPushup(doubleToConvert, Convert.Unit.Leg_lift, Leg_liftTextView);
        updateUnitFwPushup(doubleToConvert, Convert.Unit.Swimming, SwimmingTextView);
        updateUnitFwPushup(doubleToConvert, Convert.Unit.Plank, PlankTextView);
        updateUnitFwPushup(doubleToConvert, Convert.Unit.Squats, SquatsTextView);

        calories_updateUnitFwPushup(doubleToConvert, Convert.Unit.calories, caloriesTextView);

    }

    public void updateUnitFwPushup(double doubleToConvert, Convert.Unit unitConvertingTo,
                                            TextView theTextView){

        Convert unitConvert = new Convert(doubleToConvert, Convert.Unit.Pushup);

        String tempUnit = unitConvert.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);

    }

    public void calories_updateUnitFwPushup(double doubleToConvert, Convert.Unit unitConvertingTo,
                                                        TextView theTextView){

        Convert unitConvert = new Convert(doubleToConvert, Convert.Unit.Pushup);

        String tempUnit = unitConvert.to(unitConvertingTo).toString1();

        theTextView.setText(tempUnit);

    }


    public void updateUnitTwOther(Convert.Unit currentUnit){


        // Convert the value in the EditText box to a double
        double doubleToConvert;
        double doubleToConvert1 ;

        if (amountInvalid == 1 ) {
            doubleToConvert = 0.0;


        } else {
            doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        }

        // Create a Convert using the Pushup unit
        Convert currentConvertSelected = new Convert(doubleToConvert, currentUnit);

        // Create the String for the Pushup TextView
        String valueInPushups = currentConvertSelected.to(Convert.Unit.Pushup).toString();

        // Set the text for the Pushup TextView
        PushupTextView.setText(valueInPushups);

        updateUnitFwPushup(doubleToConvert, currentUnit,
                Convert.Unit.Situp, SitupTextView);

        updateUnitFwPushup(doubleToConvert, currentUnit,
                Convert.Unit.Jumping_jack, Jumping_jackTextView);

        updateUnitFwPushup(doubleToConvert, currentUnit,
                Convert.Unit.Cycling, CyclingTextView);

        updateUnitFwPushup(doubleToConvert, currentUnit,
                Convert.Unit.Stair_climbing, Stair_climbingTextView);

        updateUnitFwPushup(doubleToConvert, currentUnit,
                Convert.Unit.Walking, WalkingTextView);

        updateUnitFwPushup(doubleToConvert, currentUnit,
                Convert.Unit.Pullup, PullupTextView);

        updateUnitFwPushup(doubleToConvert, currentUnit,
                Convert.Unit.Jogging, JoggingTextView);

        updateUnitFwPushup(doubleToConvert, currentUnit,
                Convert.Unit.Leg_lift, Leg_liftTextView);

        updateUnitFwPushup(doubleToConvert, currentUnit,
                Convert.Unit.Swimming, SwimmingTextView);

        updateUnitFwPushup(doubleToConvert, currentUnit,
                Convert.Unit.Plank, PlankTextView);

        updateUnitFwPushup(doubleToConvert, currentUnit,
                Convert.Unit.Squats, SquatsTextView);

        doubleToConvert1 = Double.parseDouble(currentConvertSelected.to(Convert.Unit.Pushup).toString_nounit());


        calories_updateUnitFwPushup(doubleToConvert1, Convert.Unit.calories, caloriesTextView);

        // Set the currently selected unit to the number in the EditText
        if(currentUnit.name().equals(currentConvertSelected.unit.name())){

            // Create the TextView text by taking the value in EditText and adding
            // on the currently selected unit in the spinner
            String currentUnitTextViewText = doubleToConvert + " " +
                    currentConvertSelected.unit.name();

            // Create the TextView name to change by getting the currently
            // selected quantities unit name and tacking on _text_view
            String currentTextViewName = currentConvertSelected.unit.name() +
                    "_text_view";

            // Get the resource id needed for the textView to use in findViewById
            int currentId = getResources().getIdentifier(currentTextViewName, "id",
                    MainActivity.this.getPackageName());

            // Create an instance of the TextView we want to change
            TextView currentTextView = (TextView) findViewById(currentId);

            // Put the right data in the TextView
            currentTextView.setText(currentUnitTextViewText);

        }

    }

    public void updateUnitFwPushup(double doubleToConvert, Convert.Unit currentUnit,
                                            Convert.Unit preferredUnit, TextView targetTextView){

        Convert currentConvertSelected = new Convert(doubleToConvert, currentUnit);


        String tempTextViewText = currentConvertSelected.to(Convert.Unit.Pushup).
                to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);


    }

}