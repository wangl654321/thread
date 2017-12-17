package com.atgigu.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * 线程第三种方式:
 * 
 * 
 */
public class ThreadDamo02 {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> ft = new FutureTask<>(new T1()); //需要一个Callable类型的参数，T1继承了他
		
		new Thread(ft,"AA").start();
		System.out.println("——————————"+ft.get());
		
		new Thread(ft,"BB").start();
		System.out.println("——————————"+ft.get());
		
		System.out.println(ft.get() + ft.get());
//————————————————————————————————————————————————————————————————————————————————————		
/*		
 * 		结果：为什么?
 		———————hello,coll———————————
		——————————200
		——————————200
		400
		
		例子：就是开启线程后，把其中一条线程做自己的操作，最后在汇总
		好处：这样开启了线程的复用性，而且提高了线程的性能
*/
//————————————————————————————————————————————————————————————————————————————————————
	}
}
//————————————————————————————————————————————————————————————————————————————————————
class T1 implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		
		System.out.println("———————hello,coll———————————");
		return 200;
	}
}

