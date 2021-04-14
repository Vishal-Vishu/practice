package forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class ForkJoinTask extends RecursiveTask<String>{

	@Override
	protected String compute() {
		// TODO Auto-generated method stub
		return new java.util.Date().toString();
	}
	
}

public class ForkJoinExample {
	
	public static void main(String ar[]) {
		ForkJoinPool pool = new ForkJoinPool(10);
		
		for(int i=0;i<100;i++) {
			System.out.println(pool.invoke(new ForkJoinTask().fork()));
		}
		
		System.out.println("Finsih");
	}

}
