import java.util.Random;

/**
 * Created by Łukasz on 2016-12-08.
 */
public class RandomIntegersGenerator {
    public static int[] generateIntsArray(int amount, int min, int max){
        return new Random().ints(amount, min, max+1).toArray();
    }
}
