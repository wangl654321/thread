package com.atgigu.test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import sun.applet.Main;



/*
* 作者：wangshan
* 2017.7.21
* 
* 读写锁机制
* 
* 题目：一台机子写，100台机子读   
* 
* 为什么我跑出来就是null
*/
public class ThreadDamo06 {
	public static void main(String[] args) {
		MyQueuexxxxxx myQueuexxxxxx = new MyQueuexxxxxx();
		
		new Thread(()-> {
			try {
				
				myQueuexxxxxx.du(new Random().nextInt(100));
			} catch (Exception e) {
			}
			
		}).start();
		
		new Thread(()-> {
			for (int i = 0; i < 100; i++) {
				
				myQueuexxxxxx.xie(String.valueOf(i));
			}
			
		}).start();
		
	}
	
}

class MyQueue{
	
	private Object object;
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(); //专门读写操作使用ReentrantReadWriteLock
	
	//读
	public void du(Object object) {
		rwLock.writeLock().lock();
		
		try {
			
			this.object = object;
			System.out.println(Thread.currentThread().getName()+"\t writeThread info: "+object);
			
			
		} catch (Exception e) {
		}finally {
			rwLock.writeLock().unlock();
		}
	}
	
	//写
	public void xie(Object object) {
		rwLock.writeLock().lock();
		
		try {
			
			System.out.println(Thread.currentThread().getName()+"\t writeThread info: "+object);

		} catch (Exception e) {
		}finally {
			rwLock.writeLock().unlock();
		}
	}
	
}

 