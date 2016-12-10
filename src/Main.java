import java.util.Arrays;

/**
 * Created by Łukasz on 2016-12-07.
 */
public class Main {

    public static void main(String[] args){
        int[] array = RandomIntegersGenerator.generateIntsArray(1000, 1, 10000);
        System.out.println(Arrays.toString(array));
        QuickSorter.quickSort(array);
        System.out.println(Arrays.toString(array));

    }
}
