package Util;

import java.util.Arrays;

public class Java_Programming {

    public static void rotate(int[] num, int startNum){

        int[] result = new int[num.length];
        if (startNum >= 0) System.arraycopy(num, num.length - startNum, result, 0, startNum);
        System.arraycopy(num, 0, result, startNum,num.length-startNum);
        System.arraycopy(result, 0, num, 0, num.length);
    }

    public static void main(String[] args) {
        int[] num = {1,2,3,4,5,6,7};
        rotate(num, 1);
        System.out.println(Arrays.toString(num));
    }


}
