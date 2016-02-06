package com.casey.exerconvert;

import java.text.DecimalFormat;

public class Convert {

    final double value;
    final Unit unit;

    // This emul of Unit will use Pushup as standard unit for everything... 
    // Every other selection will be convert to Pushup and then from Pushup 
    // we will convert to rest of the item in the spinner...
    // ... Except for the calories which is not part of the spinner or the grid layout

    public static enum Unit {
        Pushup(1.0d), Situp(0.571429d), Jumping_jack(0.028571d), Cycling(0.034286d),
        Stair_climbing(0.042857d), Walking(0.057143d), Pullup(0.285714d), Jogging(0.034286d),
        Leg_lift(0.071429d), Swimming(0.037143d), Plank(0.071429d), Squats(0.642857d),
        calories(0.2857);


        final static Unit baseUnit = Pushup;


        final double byBaseUnit;

        private Unit(double inPushup) {
            this.byBaseUnit = inPushup;
        }

        public double toBase(double value) {
            return value / byBaseUnit;
        }


        public double frBase(double value) {
            return value * byBaseUnit;
        }

    }

    public Convert(double value, Unit unit) {
        super();
        this.value = value;
        this.unit = unit;
    }

    public Convert to(Unit newUnit) {
        Unit oldUnit = this.unit;
        return new Convert(newUnit.frBase(oldUnit.toBase(value)),
                newUnit);
    }

    // Prints out to screen the unit amount and unit type
    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("#.0");

        return df.format(value) + " " + uname1();
    }
    // this is used to take care of the conversion for calories.
    // it is not part of the spinner selection

    public String toString_nounit() {

        DecimalFormat df = new DecimalFormat("#.0");

        return df.format(value);
    }
    // temp... optional used in the callories text view... 
    // to be integrated with toString later...
    public String toString1() {

        DecimalFormat df = new DecimalFormat("#.0");

        return df.format(value) + " " + uname1();

    }
    public String uname1() {

        if (unit.name().equals( "Stair_climbing") ) {
            return "Stair climbing";
        } else if (unit.name().equals("Leg_lift") ){
            return "Leg lift";
        } else if (unit.name().equals( "Jumping_jack") ){
            return "Jumping jack";
        } else
            return unit.name();
    }

}