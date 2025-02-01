package Util;


import java.math.BigInteger;
import static Util.BinarySearch.binarySearch;
import static Util.BinarySearch.binarySearchRecursive;
import static Util.Factorial.factorial;
import static Util.ReturnDuplicates.returnDuplicates;
import static Util.UniqueAlphaNumericIdGenerator.AlphaNumericIdGenerator;

public class ProgrammingLearning {





    public static void main(String[] args) {
        //print factorial of given parameter integer
       System.out.println( factorial(BigInteger.valueOf(20)));

        //print the unique Alpha Numeric 10 digit id
        System.out.println( AlphaNumericIdGenerator(15));

        //print Duplicate Numbers
        Integer[] arr = {1,4,3,2,4,5,2,7,8,0,0,8,9,4};
        System.out.println(returnDuplicates(arr));
        //print Duplicate Values
        String[] names = {"chandra","bhargavi", "aarushi","chandra" };
        System.out.println(returnDuplicates(names));
        //Binary Search
        int[] into = {3, 5, 7, 8, 9, 11, 12};
        System.out.println(binarySearch(into, -20));
        //Binary Search using Recursive
        System.out.println(binarySearchRecursive(into, -20));

    }


}
