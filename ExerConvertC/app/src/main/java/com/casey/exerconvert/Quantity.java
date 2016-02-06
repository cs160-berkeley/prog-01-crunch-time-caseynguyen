package com.casey.exerconvert;

import java.text.DecimalFormat;

public class Quantity {

    // Each object will have both a value and a unit of measure
    final double value;
    final Unit unit;

    // Enum types use a constant key to represent a value
    // They allow use to easily define how to convert all the other types
    // of measurements to convert from teaspoon to anything. Then to make
    // any conversion we convert from the starting type to teaspoon and then
    // to the final required type.

    public static enum Unit {
        Pushup(1.0d), Situp(0.571429d), Jumping_jack(0.028571d), Cycling(0.034286d),
        Stair_climbing(0.042857d), Walking(0.057143d), Pullup(0.285714d), Jogging(0.034286d),
        Leg_lift(0.071429d), Swimming(0.037143d), Plank(0.071429d), Squats(0.642857d);

        // We define that Pushup will be the base unit of measure that we will
        // convert to and then convert from.
        final static Unit baseUnit = Pushup;

        // Will hold the number of Pushup the original unit converts to
        final double byBaseUnit;

        // Receives the number of Pushups the starting unit equals
        private Unit(double inPushup) {
            this.byBaseUnit = inPushup;
        }

        // Converts any other unit value to the number of Pushups
        public double toBaseUnit(double value) {
            return value / byBaseUnit;
        }

        // We convert to another unit by using the teaspoon conversion percent
        // defined in the enum
        public double fromBaseUnit(double value) {
            return value * byBaseUnit;
        }

    }

    // The constructor that receives the value and unit of measure
    public Quantity(double value, Unit unit) {
        super();
        this.value = value;
        this.unit = unit;
    }

    // Converts from Pushup to the desired unit type
    public Quantity to(Unit newUnit) {
        Unit oldUnit = this.unit;
        return new Quantity(newUnit.fromBaseUnit(oldUnit.toBaseUnit(value)),
                newUnit);
    }

    // Prints out to screen the unit amount and unit type
    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("#.0");

        return df.format(value) + " " + unit.name();
    }

}