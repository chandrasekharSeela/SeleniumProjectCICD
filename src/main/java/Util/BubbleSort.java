package Util;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.Random;

import static Util.QuickSortAlgorithm.swap;


public class BubbleSort {

    public static void main(String[] args) {
        int[] num = new int[100000];
        for(int i =0; i<num.length;i++)
            num[i] = new Random().nextInt(1000000)+1;
        printArray(num);
          bubbleSort(num);
        printArray(num);
    }

    public static void bubbleSort(int[] numbers){
        boolean swapeSomething  = true;

       while (swapeSomething){
           swapeSomething = false;
            for (int j = 0; j < numbers.length - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    swap(numbers, j, j + 1);
                    swapeSomething= true;
                }
            }
        }
    }
    public  static  void  printArray(int[] arr){
        for(int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

}
