package com.atgigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/*
 * 作者：wangshan
 * 2017.7.21
 * 
 * 题目：两个线程操作一个初始值为零的int变量，一个做加1，一个做减1，来10次!
 * 
 */
public class ThreadDamo03 {
	
	public static void main(String[] args) {
//——————————————————————————————————————————————————————————————————————————————————————————————————————		
		//线程/操作
		T t = new T();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <=10; i++) {
					t.add();
				}
			}
		},"AA").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <=10; i++) {
					t.jian();
				}
			}
		},"BB").start();
//————————————————————————————————————————————————————————————————————————————————————————————————————————
		//lambda表达式：
		new Thread(()->{for (int i = 1; i <10; i++) {t.jian();}}).start();
		new Thread(()->{for (int i = 1; i <10; i++) {t.add();}}).start();
//————————————————————————————————————————————————————————————————————————————————————————————————————————
	}
}
//资源类
class T{
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
//————————————————————————————————————————————————————————————————————————————————————————————————————————	
	//多线程高级写法
	public  void add() {
		
		lock.lock();
		try {
			while (count != 0) {
				
				c1.await();     //自己等待，把锁交出去，后面也就不再走了，也不会在打印了
			}
			++count;
			System.out.println(Thread.currentThread().getName()+"\t"+count);
			c1.signalAll(); //通知下一个线程
			
		} catch (Exception e) {
		}finally {
			lock.unlock();
		}
	}
	public  void jian() {
		lock.lock();
		try {
			while (count == 0) {
				
				c1.await();    //自己等待，把锁交出去,后面也就不再走了，也不会在打印了
			}
			--count;
			System.out.println(Thread.currentThread().getName()+"\t"+count);
			c1.signalAll();//通知下一个线程
			
		} catch (Exception e) {
		}finally {
			lock.unlock();
		}
	}
//————————————————————————————————————————————————————————————————————————————————————————————————————————	
	//普通synchronized同步方法
	public synchronized void T1() throws InterruptedException {
		
		while (count != 0) {
			c1.await();
			
		}
		++count;
		System.out.println(Thread.currentThread().getName()+"\t"+count);
		c1.signalAll();
	}
	
	public synchronized void T2() throws InterruptedException {
		
		while (count == 0) {
			
			c1.await();
			
		}
		--count;
		System.out.println(Thread.currentThread().getName()+"\t"+count);
		c1.signalAll();
	}
}