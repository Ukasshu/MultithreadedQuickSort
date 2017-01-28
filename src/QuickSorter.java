import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Łukasz on 2016-12-13.
 */
public class QuickSorter {
    public static void quickSort(int[] array){
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        AtomicInteger counter = new AtomicInteger(1);
        class SplitTask implements Runnable{
            private int start;
            private int end;
            SplitTask(int start, int end){
                this.start = start;
                this.end = end;
            }

            @Override
            public void run() {
                if (end - start > 100) {
                    int pivotPos = split(array, start, end);
                    if (pivotPos > start + 1) {
                        executor.execute(new SplitTask(start, pivotPos - 1));
                        counter.getAndIncrement();
                    }
                    if (pivotPos < end - 1) {
                        executor.execute(new SplitTask(pivotPos + 1, end));
                        counter.getAndIncrement();
                    }
                } else {
                    nonParallelQS(array, start, end);
                }
                counter.getAndDecrement();

            }

        }
        executor.execute(new SplitTask(0, array.length-1))  ;


        while(counter.get()!=0)
        {

        }

        try{

            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }




    }

    private static int split(int[] array, int start, int end){
        int tmp;
        int endPivotPos = end;
        int pivot = array[(start+end)/2];
        array[(start+end)/2] = array[end];
        array[end] = pivot;
        for(int i = start; i<endPivotPos; i++){
            if(array[i]>pivot){
                tmp = array[i];
                array[i] = array[--endPivotPos];
                array[endPivotPos] = tmp;
                i--;
            }
        }
        array[end] = array[endPivotPos];
        array[endPivotPos] = pivot;
        return endPivotPos;
    }

    public static void nonParallelQS(int[] array, int start, int end){
        int p = split(array, start, end);
        if(p > start+1)
            nonParallelQS(array, start, p-1);
        if(end>p+1)
            nonParallelQS(array, p+1, end);
    }
}
