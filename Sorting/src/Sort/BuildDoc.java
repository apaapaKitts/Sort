package Sort;

/**
 * BuildDoc is a class that fills an array with 'Person' objects
 * The user can then choose whether they would like to sort the array by age using Merge or Exchange (bubble) sorts
 * The complexity is updated as each main computation occurs and is presented to the user upon sort has completed
 *
 *
 * Written by Andrew Pauls
 * 2022 / 04 / 14
 *
 * Sorting algorithms can be found in "Introduction to Data Structures" - Written by David Hughes, 2015
 */

import BasicIO.*;

public class BuildDoc {

    private ASCIIDataFile input;
    Person[] listOfPeople;
    int mergeComplexity = 0;
    int exchangeComplexity = 0;



    public BuildDoc () {

        input = new ASCIIDataFile("names.txt");
        Person aPerson;
        listOfPeople = new Person[1000];
        // build and display an array of unsorted 'Persons'
        buildList();
        displayList(listOfPeople);
        System.out.println("now we sort the list by age.");
        printGap();
//
        // sort by age using merge sort
        System.out.println("Sorting with merge sort and displaying the results");
        mergeSortByAge(listOfPeople);
        displayList(listOfPeople);
        System.out.println("Number of steps: " + mergeComplexity + ".");
        System.out.println("Steps for O(nlogn) = " + getStepsMerge(listOfPeople.length));

//
//        // sort by age using exchange sort
//        System.out.println("Using exchange sort and displaying the results");
//        exchangeSortByAge(listOfPeople);
//        displayList(listOfPeople);
//        System.out.println("Number of steps: " + exchangeComplexity + ". ");
//        System.out.println("Average steps for O(n^2): " + getExchangeAve(listOfPeople.length));
//

    }

    // methods

    // returns the number of steps MergeSort takes. Problem size is passed as parameter
    public int getStepsMerge ( int n ) {
        return 2*n - 1;
    }

    // returns the average number of steps taken to perform exchange sort on a problem of size n
    public int getExchangeAve ( int n ) {
        return (int)(n * (n-1) / 2);
    }

    // initializes 1000 'Person' objects and puts them into an array
    private void buildList ( ) {
        Person aPerson;
        for ( int i = 0 ; i < listOfPeople.length ; i++ ) {
            aPerson = new Person(input);
            listOfPeople[i] = aPerson;
        }
    }

    // prints a bunch of spaces to System.out so that you can see the gap between sorts easier
    private void printGap () {
        for ( int i = 0 ; i < 9 ; i++ ) {
            System.out.println();
        }
    }

    // common merge sort algorithm used in this case to sort 'Persons' by highest age to lowest
    private void mergeSortByAge( Person[] toSort ) {
        mergeComplexity++;      // every time the method is called, there is both a split and a close
        Person[] leftside;
        Person[] rightside;
        int leftPartition;          // left partition
        int rightPartition;         // right partition

        if ( toSort.length > 1 ) {
            leftside  = new Person[toSort.length/2];
            rightside = new Person[toSort.length- leftside.length];

            // fill in left side
            for ( int i = 0 ; i < leftside.length; i++ ) {
                leftside[i] = toSort[i];
            }
            // fill in right side
            for ( int i = 0 ; i < rightside.length; i++ ) {
                rightside[i] = toSort[leftside.length+i];
            }

            // sort each side
            mergeSortByAge(leftside);
            mergeSortByAge(rightside);

            // merge the two
            leftPartition = 0;
            rightPartition = 0;

            // merges two list, from atomic -> highest level
            for ( int i = 0 ; i < toSort.length; i++ ) {
                if ( rightPartition >= rightside.length ) {             // if the right partition is off the array ( down to the atomic )
                    toSort[i] = leftside[leftPartition];                // the original array at 'i' becomes the partition of the left side
                    leftPartition = leftPartition + 1;                  // left partition advances
                }
                else if ( leftPartition >= leftside.length ) {          // if the left partition is off the list ( down to the atomic )
                    toSort[i] = rightside[rightPartition];
                    rightPartition = rightPartition +1;
                }
                else if ( leftside[leftPartition].getAge() >= rightside[rightPartition].getAge() ) {        // if left partition >= right partition,
                    toSort[i] = leftside[leftPartition];                                                    // put the left partition in 'toSort'
                    leftPartition = leftPartition + 1;                                                      // advance left partition
                }
                else {                                                                                      // rightside partition > leftside partition
                    toSort[i] = rightside[rightPartition];                                                  // put the rightside partition in 'toSort'
                    rightPartition = rightPartition + 1;                                                    // advance the right partition
                }
            }
        }
    }

    // common exchangeSort. Sorts people in an array from max to min age
    public void exchangeSortByAge ( Person[] people) {
        exchangeComplexity = 0;
        Person temporary;

        for ( int i = people.length -1 ; i >=1 ; i--) {
            for ( int j = 0 ; j<i ; j++ ) {
                if (people[j+1].getAge() > people[j].getAge()) {
                    temporary = people[j];
                    people[j] = people[j+1];
                    people[j+1] = temporary;
                    exchangeComplexity++;
                }
            }
        }
    }

    // takes an arary of Persons, displays all Persons ages
    public void displayList ( Person[] thePeople ) {
        // displays the ages of each person... just a tester
        for ( int j = 0 ; j < thePeople.length ; j++ ) {
            System.out.println("person number: " + j + " is named: " + thePeople[j].getName() + " and is " + thePeople[j].getAge() + " years old");
        }
    }


    public static void main (String [] args) { BuildDoc bD = new BuildDoc();}
}
