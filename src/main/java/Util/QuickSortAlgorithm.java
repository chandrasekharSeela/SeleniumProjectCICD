package Util;

import java.util.Arrays;
import java.util.Random;

public class QuickSortAlgorithm {

    public static void main(String[] args) {
        Random random = new Random();
        int[] numbers = new int[1000000];
        for(int i=0; i< numbers.length; i++) numbers[i] = random.nextInt(10000);

       String[] names = new String[10];
       for(int j=0;j<names.length;j++){
           names[j] = UniqueAlphaNumericIdGenerator.randomLetterString(6);
       }

        //Arrays.stream(numbers).forEach(System.out::println);
        long startTime = System.currentTimeMillis();
        quickSort(numbers);
        long endTime = System.currentTimeMillis();
        Arrays.stream(names).forEach(System.out::println);
        quickSort(names);
        System.out.println("*********************");
        Arrays.stream(names).forEach(System.out::println);
        System.out.println(endTime-startTime);

        //Arrays.stream(numbers).forEach(System.out::println);
    }


    public static void quickSort(int[] array){
        int lowerIndex = 0;
        int higherIndex = array.length-1;
        quickSort(array, lowerIndex, higherIndex);
    }
    public static void quickSort(String[] array){
        int lowerIndex = 0;
        int higherIndex = array.length-1;
        quickSort(array, lowerIndex, higherIndex);
    }

    public static void quickSort(int[] array, int lowerIndex, int higherIndex){
        if(lowerIndex>=higherIndex) return;
        int pivotIndex = new Random().nextInt(higherIndex -lowerIndex)+lowerIndex;
        swap(array, pivotIndex,higherIndex);
        int pivot = array[higherIndex];
        int leftPointer = partition(array, lowerIndex, higherIndex, pivot);
        quickSort(array, lowerIndex, leftPointer-1);
        quickSort(array, leftPointer+1, higherIndex);

    }

    public static void quickSort(String[] array, int lowerIndex, int higherIndex){
        if(lowerIndex>=higherIndex) return;
        int pivotIndex = new Random().nextInt(higherIndex -lowerIndex)+lowerIndex;
        swap(array, pivotIndex,higherIndex);
        String  pivot = array[higherIndex];
        int leftPointer = partition(array, lowerIndex, higherIndex, pivot);
        if(leftPointer!=0) quickSort(array, lowerIndex, leftPointer-1);
        quickSort(array, leftPointer+1, higherIndex);

    }

    private static int partition(int[] array, int lowerIndex, int higherIndex, int pivot) {
        int leftPointer = lowerIndex;
        int rightPointer = higherIndex;

        while(leftPointer < rightPointer){

            while(array[leftPointer] <= pivot && leftPointer<rightPointer){
                leftPointer++;
            }
            while (array[rightPointer]>= pivot && leftPointer<rightPointer){
                rightPointer--;
            }
           swap(array, leftPointer,rightPointer);
        }
        swap(array,rightPointer, higherIndex);
        return rightPointer;
    }

   public static int partition(String[] array, int lowerIndex, int higherIndex, String pivot){
       int leftPointer = lowerIndex;
       int rightPointer = higherIndex;


        while(leftPointer<rightPointer){
            while(array[leftPointer].compareTo(pivot)<=0 && leftPointer<rightPointer){
                leftPointer++;
            }
            while(array[rightPointer].compareTo(pivot)>=0 && leftPointer<rightPointer){
                rightPointer--;
            }
            swap(array, leftPointer,rightPointer);
        }
       swap(array,leftPointer, higherIndex);
        return leftPointer;
   }

    public static void swap(int[] array, int leftPointer, int rightPointer) {
        int temp = array[leftPointer];
        array[leftPointer] = array[rightPointer];
        array[rightPointer]= temp;
    }
    public static void swap(String[] array, int leftPointer, int rightPointer){
        String temp = array[leftPointer];
        array[leftPointer] = array[rightPointer];
        array[rightPointer] = temp;
    }
}
