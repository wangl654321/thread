package com.atgigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import sun.misc.ConditionLock;

/*
 * 作者：wangshan
 * 2017.7.21
 * 
 * 题目：两个线程操作一个初始值为零的int变量，一个做加1，一个做减1，来10次!
 * 
 */
public class ThreadDamo03cop {

	public static void main(String[] args) {
		//线程/操作
		
		L l = new L();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i <= 10; i++) {
					l.l1();
				}
				
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i <= 10; i++) {
					l.l2();
				}
			}
		}).start();
//————————————————————————————————————————————————————————————————————————————————————————————————————
		new Thread(()->{
			for (int i = 0; i <= 10; i++) {
				l.l1();
			}
		}).start();
		new Thread(()->{
			for (int i = 0; i <= 10; i++) {
				l.l2();
			}
		}).start();
//————————————————————————————————————————————————————————————————————————————————————————————————————
	}
}

//资源类
class L{
	
	private int count = 0;
	private Lock lock;
	private Condition c1 = lock.newCondition();
	
	public void l1() {
		
		lock.lock();
		try {
			if (count >0) {
				c1.await();	
				
			}
			++count;
			c1.signalAll();
		} catch (Exception e) {
		}finally {
			lock.unlock();
		}
	}
	
	public void l2() {
		
		lock.lock();
		try {
			if (count <0) {
				c1.await();
				
			}
			--count;
			c1.signal();
		} catch (Exception e) {
		}finally {
			lock.unlock();
		}
	}
	
}
