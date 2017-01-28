import java.util.Arrays;

/**
 * Created by Łukasz on 2016-12-07.
 */
public class Main {

    public static void main(String[] args){
        int[] array = RandomIntegersGenerator.generateIntsArray(1000000, 1, 10000);
        int[] array2 = Arrays.copyOf(array, array.length);
        //System.out.println(Arrays.toString(array));
        long startTime, endTime;
        startTime = System.nanoTime();
        QuickSorter.quickSort(array);
        //QuickSorter2.nonParallelQS(array, 0 , array.length-1);
        endTime = System.nanoTime();
        //System.out.println(Arrays.toString(array));
        System.out.println((endTime-startTime)*Math.pow(10, -9));
        startTime = System.nanoTime();
        Arrays.parallelSort(array2);
        endTime = System.nanoTime();
        System.out.println((endTime-startTime)*Math.pow(10, -9));
        //System.out.println(Arrays.toString(array2));


    }
}
