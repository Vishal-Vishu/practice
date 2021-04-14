package forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Integer>{
	
	private static final int var = 5;  
    private final int[] value;  
   private final int st;  
   private final int ed;  
   //parameterized constructor  
	public ForkJoinDemo(int[] value, int st, int ed) {  
	this.value = value;  
	this.st = st;  
	this.ed = ed;  
	}  
	public ForkJoinDemo(int[] value) {  
	this(value, 0, value.length);  
	}  
	
	public static void main(String[] ar) {
		final int[] value = new int[10];  
        final Random rand = new Random();  
        for (int i = 0; i < value.length; i++) {  
          value[i] = rand.nextInt(100);  
        }  
           // submit the task to the poool  
           ForkJoinPool pool = ForkJoinPool.commonPool();  
           ForkJoinDemo finder = new ForkJoinDemo(value);  
        System.out.println(pool.invoke(finder));  
          //  pool.invokeAll(t);  
           pool.invoke(finder);  
	}
	@Override
	protected Integer compute() {
		final int length = ed - st;  
        if (length < var) {  
          return computeDirectly();  
        }  
        final int split = length / 2;  
            //new class object   
            ForkJoinPool pl = new  ForkJoinPool();  
            ForkJoinDemo left = new ForkJoinDemo(value, st, st + split);  
        pl.invoke(left.fork());  
        ForkJoinDemo right = new ForkJoinDemo(value, st + split, ed);  
                 return Math.max(right.compute(), left.join());   
	}
	
	 private Integer computeDirectly() {  
	        System.out.println(Thread.currentThread() + " computing: " + st + " to " + ed);  
	        int max = Integer.MIN_VALUE;  
	        for (int i = st; i < ed; i++) {  
	          if (value[i] > max) {  
	            max = value[i];  
	          }  
	        }  
	        return max;  
	      }  

}
