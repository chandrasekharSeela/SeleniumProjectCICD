package Util;

import java.util.Arrays;

public class BinarySearch {

    public static int binarySearch(int[] arr, int numberToFind) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int middlePosition = (low + high) / 2;
            int middleNumber = arr[middlePosition];
            if (middleNumber == numberToFind) {
                return middlePosition;
            }
            if (numberToFind < middleNumber) {
                high = middlePosition - 1;
            } else {
                low = middlePosition + 1;
            }

        }
        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int numToFind ){

        int low = 0;
        int high = arr.length-1;
        int myval = 0;
        while(low <= high){
            int middlePosition = (low + high)/2;
            int middleNumber = arr[middlePosition];
            if(middleNumber==numToFind) {
                return middlePosition;
            }
            if(numToFind < middleNumber){
                high = middlePosition;
                myval = binarySearchRecursive(Arrays.copyOfRange(arr,low,high),numToFind);
                if( myval==-1)
                    return -1;
            }else{
                low = middlePosition+1;
                myval = binarySearchRecursive(Arrays.copyOfRange(arr,low,arr.length),numToFind);
                if( myval==-1)
                    return -1;
            }

        }
        return -1;
    }
}
