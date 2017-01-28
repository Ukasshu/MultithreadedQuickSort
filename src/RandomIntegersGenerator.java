import java.util.Random;

/**
 * Created by Łukasz on 2016-12-08.
 */
public class RandomIntegersGenerator {
    public static int[] generateIntsArray(long amount, long min, long max){
        return new Random().ints(amount, (int)min, (int)max+1).toArray();
    }
}
