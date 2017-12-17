package com.atgigu.test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/*
 * Arrays Collections Executors
 * 三个非常重要的s
 * 
 */
public class ThreadDamo07 {
		
	public static void main(String[] args) {
//—————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
		    //一个线程池同时5个线程（线程数量是直接指定的）
			ScheduledExecutorService service  = Executors.newScheduledThreadPool(5); 
			ScheduledFuture<Integer> result = null;
			
			try {
				for (int i =1; i < 20; i++) {
					result = service.schedule(()->{
						System.out.print(Thread.currentThread().getName()+" : ");
						return new Random().nextInt(100);
					}, 2, TimeUnit.SECONDS);
				}
				
			} catch (Exception e) {
				
			}finally {
				service.shutdown();
			}
	}
//—————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————	
	public static void testThreadPool() {
			
		   //这个在线程池中使用非常多，给你一个线程池，里面多少线程不知道，需要线程的时候自己拿
		   ExecutorService service = Executors.newCachedThreadPool();
		   
		   //ExecutorService service = Executors.newFixedThreadPool(5); 一池5线程
		   Future<Integer> result = null;
		   
		   
		   result = service.submit( new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				
				System.out.print(Thread.currentThread().getName()+" : ");
				return new Random().nextInt(100);
			}} );
		   
/*		   result = service.submit(
				   () ->{
					   System.out.print(Thread.currentThread().getName()+" : ");
						return new Random().nextInt(100);
				   }
				   );
	*/	   
	}
	
	
	
	
	
}