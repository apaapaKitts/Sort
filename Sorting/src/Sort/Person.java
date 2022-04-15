/**
 * This class represents a "Person" object.
 * Each person has a last name, income, and age.
 *
 * NAME:   gets name from text file included in project "names.txt"
 *
 * AGE:    generated randomly to be between 0 and 99
 *
 * INCOME: generated randomly to be between 0 and 1,000,000
 *
 */

package Sort;

import BasicIO.*;

import static java.lang.Math.random;

public class Person {

    private String name;
    private int age;
    private int income;
    public Person (ASCIIDataFile input) {
        name   = input.readString();                // make sure its random
        age    = (int)(99 * random());              // build random age
        income = (int)(1000000 * random());         // build random income
    }

    // methods
    public String getName() {           // getter method for name
        return name;
    }

    public int getAge() {               // getter method for age
        return age;
    }

    public int getIncome () {           // getter method for income
        return income;
    }

}   // Person.java
