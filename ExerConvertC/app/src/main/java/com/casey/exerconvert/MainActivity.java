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

    TextView PushupTextView, SitupTextView, Jumping_jackTextView, CyclingTextView,
            Stair_climbingTextView, WalkingTextView, PullupTextView, JoggingTextView,
            Leg_liftTextView, SwimmingTextView, PlankTextView, SquatsTextView,
            RepMintextView;

    double amountBefore = 0; // Exercise data amount
    int amountInvalid ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fills the spinner with the unit options
        addItemsToUnitTypeSpinner();

        // Add listener to the Spinner
        addListenerToUnitTypeSpinner();

        // amount change detect


        // Get a reference to the edit text view to retrieve the amount of the unit type
        amountTextView = (EditText) findViewById(R.id.amount_text_view);

        initializeTextViews();

        // RepMin update
        RepMintextView = (TextView) findViewById(R.id.RepMintextView);

        amountTextView.addTextChangedListener(amountBeforeListener);


    }
    // Called when the bill before tip amount is changed

    private TextWatcher amountBeforeListener = new TextWatcher(){

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

                amountBefore = Double.parseDouble(arg0.toString());

                amountInvalid = 0 ;
            }

            catch(NumberFormatException e){

                amountBefore = 0.0;
                amountInvalid = 1 ;


            }
            if( amountInvalid == 0 ) {

                String itemSelectedInSpinner = unitTypeSpinner.getSelectedItem().toString();
                checkIfConvertingFromPushup(itemSelectedInSpinner);
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

    public void addItemsToUnitTypeSpinner(){

        // Get a reference to the spinner
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_types, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);

    }

    public void addListenerToUnitTypeSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {
                // Get the item selected in the Spinner
                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString();

                // Verify if I'm converting from Pushup so that I use the right
                // conversion algorithm

                if (amountInvalid == 0 ) {

                    checkIfConvertingFromPushup(itemSelectedInSpinner);
                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO maybe add something here later

            }
        });
    }

    public void checkIfConvertingFromPushup(String currentUnit){

        if(currentUnit.equals("Pushup")){

            updateUnitTypesUsingPushup(Quantity.Unit.Pushup);

            RepMintextView.setText("reps");

        } else {

            if(currentUnit.equals("Situp")){

                updateUnitTypesUsingOther(Quantity.Unit.Situp);
                RepMintextView.setText("reps");


            } else if(currentUnit.equals("Jumping_jack")){

                updateUnitTypesUsingOther(Quantity.Unit.Jumping_jack);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Cycling")){

                updateUnitTypesUsingOther(Quantity.Unit.Cycling);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Stair_climbing")){

                updateUnitTypesUsingOther(Quantity.Unit.Stair_climbing);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Walking")){

                updateUnitTypesUsingOther(Quantity.Unit.Walking);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Pullup")){

                updateUnitTypesUsingOther(Quantity.Unit.Pullup);
                RepMintextView.setText("reps");

            } else if(currentUnit.equals("Jogging")){

                updateUnitTypesUsingOther(Quantity.Unit.Jogging);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Leg_lift")){

                updateUnitTypesUsingOther(Quantity.Unit.Leg_lift);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Swimming")){

                updateUnitTypesUsingOther(Quantity.Unit.Swimming);
                RepMintextView.setText("mins");

            } else if(currentUnit.equals("Plank")){

                updateUnitTypesUsingOther(Quantity.Unit.Plank);
                RepMintextView.setText("mins");

            } else {

                updateUnitTypesUsingOther(Quantity.Unit.Squats);
                RepMintextView.setText("mins");

            }

        }

    }

    public void updateUnitTypesUsingPushup(Quantity.Unit currentUnit){
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
        updateUnitTextFieldUsingPushup(doubleToConvert, Quantity.Unit.Situp, SitupTextView);
        updateUnitTextFieldUsingPushup(doubleToConvert, Quantity.Unit.Jumping_jack, Jumping_jackTextView);
        updateUnitTextFieldUsingPushup(doubleToConvert, Quantity.Unit.Cycling, CyclingTextView);
        updateUnitTextFieldUsingPushup(doubleToConvert, Quantity.Unit.Stair_climbing, Stair_climbingTextView);
        updateUnitTextFieldUsingPushup(doubleToConvert, Quantity.Unit.Walking, WalkingTextView);
        updateUnitTextFieldUsingPushup(doubleToConvert, Quantity.Unit.Pullup, PullupTextView);
        updateUnitTextFieldUsingPushup(doubleToConvert, Quantity.Unit.Jogging, JoggingTextView);
        updateUnitTextFieldUsingPushup(doubleToConvert, Quantity.Unit.Leg_lift, Leg_liftTextView);
        updateUnitTextFieldUsingPushup(doubleToConvert, Quantity.Unit.Swimming, SwimmingTextView);
        updateUnitTextFieldUsingPushup(doubleToConvert, Quantity.Unit.Plank, PlankTextView);
        updateUnitTextFieldUsingPushup(doubleToConvert, Quantity.Unit.Squats, SquatsTextView);

    }

    public void updateUnitTextFieldUsingPushup(double doubleToConvert, Quantity.Unit unitConvertingTo,
                                            TextView theTextView){

        Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.Pushup);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);

    }

    public void updateUnitTypesUsingOther(Quantity.Unit currentUnit){

        // Convert the value in the EditText box to a double
        // double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        // Convert the value in the EditText box to a double
        double doubleToConvert;

        if (amountInvalid == 1 ) {
            doubleToConvert = 0.0;


        } else {
            doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        }

        // Create a Quantity using the Pushup unit
        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);

        // Create the String for the Pushup TextView
        String valueInPushups = currentQuantitySelected.to(Quantity.Unit.Pushup).toString();

        // Set the text for the Pushup TextView
        PushupTextView.setText(valueInPushups);

        updateUnitTextFieldUsingPushup(doubleToConvert, currentUnit,
                Quantity.Unit.Situp, SitupTextView);

        updateUnitTextFieldUsingPushup(doubleToConvert, currentUnit,
                Quantity.Unit.Jumping_jack, Jumping_jackTextView);

        updateUnitTextFieldUsingPushup(doubleToConvert, currentUnit,
                Quantity.Unit.Cycling, CyclingTextView);

        updateUnitTextFieldUsingPushup(doubleToConvert, currentUnit,
                Quantity.Unit.Stair_climbing, Stair_climbingTextView);

        updateUnitTextFieldUsingPushup(doubleToConvert, currentUnit,
                Quantity.Unit.Walking, WalkingTextView);

        updateUnitTextFieldUsingPushup(doubleToConvert, currentUnit,
                Quantity.Unit.Pullup, PullupTextView);

        updateUnitTextFieldUsingPushup(doubleToConvert, currentUnit,
                Quantity.Unit.Jogging, JoggingTextView);

        updateUnitTextFieldUsingPushup(doubleToConvert, currentUnit,
                Quantity.Unit.Leg_lift, Leg_liftTextView);

        updateUnitTextFieldUsingPushup(doubleToConvert, currentUnit,
                Quantity.Unit.Swimming, SwimmingTextView);

        updateUnitTextFieldUsingPushup(doubleToConvert, currentUnit,
                Quantity.Unit.Plank, PlankTextView);

        updateUnitTextFieldUsingPushup(doubleToConvert, currentUnit,
                Quantity.Unit.Squats, SquatsTextView);


        // Set the currently selected unit to the number in the EditText
        if(currentUnit.name().equals(currentQuantitySelected.unit.name())){

            // Create the TextView text by taking the value in EditText and adding
            // on the currently selected unit in the spinner
            String currentUnitTextViewText = doubleToConvert + " " +
                    currentQuantitySelected.unit.name();

            // Create the TextView name to change by getting the currently
            // selected quantities unit name and tacking on _text_view
            String currentTextViewName = currentQuantitySelected.unit.name() +
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

    public void updateUnitTextFieldUsingPushup(double doubleToConvert, Quantity.Unit currentUnit,
                                            Quantity.Unit preferredUnit, TextView targetTextView){

        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);

        // Algorithm used quantityInSitup.to(Unit.Pushup).to(Unit.Cycling)

        String tempTextViewText = currentQuantitySelected.to(Quantity.Unit.Pushup).
                to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);


    }

}