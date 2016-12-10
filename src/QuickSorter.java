import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created by Łukasz on 2016-12-09.
 */
public class QuickSorter {
    public static void quickSort(int[] array){
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        class SplitTask implements Runnable{
            private int start;
            private int end;
            SplitTask(int start, int end){
                this.start = start;
                this.end = end;
            }
            @Override
            public void run() {
                int pivotPos = split(array, start, end);
                if(pivotPos > start+1)
                    executorService.execute(new SplitTask(start, pivotPos-1));
                if(pivotPos < end-1)
                    executorService.execute(new SplitTask(pivotPos+1, end));
            }
        }
        executorService.execute(new SplitTask(0,array.length-1));
        while(executorService.getQueue().size()!=0 || executorService.getActiveCount()!=0){
        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
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


}
