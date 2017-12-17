package com.atgigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 线程按序接力 
 * 
 * 题目：有三个线程，要求完成A打印5次，B打印10次，C打印15次，重复10轮
 * 提示：游标
 * 
 */
public class ThreadDemo05 {
	
	public static void main(String[] args) {
//——————————————————————————————————————————————————————————————————————————————————————————————————————————		
		ShareResource s = new ShareResource();
		
		new Thread(()->{
			for (int i = 1; i <= 10; i++) {
				s.T1(i);
			}
			
		},"AA").start();
		
		new Thread(()->{
			for (int i = 1; i <= 10; i++) {
				s.T2(i);
			}
		},"BB").start();
		new Thread(()->{
			for (int i = 1; i <= 10; i++) {
				s.T3(i);
			}
		},"CC").start();
		
	}
}
//——————————————————————————————————————————————————————————————————————————————————————————————————————
//资源类
class ShareResource{
	private int flang =1;  //游标：1-A 2-B 3-C
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();
		
	public void T1(int totalLoop) {
		lock.lock();
		try {
			//判断
			while (flang != 1) {
				c1.await();
			}
			//干活
			for (int i = 1; i < 5; i++) {
				
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoop: "+totalLoop);
			}
			//换醒/通知
			flang =2;
			c2.signal();
		} catch (Exception e) {
		}finally {
			lock.unlock();
		}
	}
	
	public void T2(int totalLoop) {
		lock.lock();
		try {
			//判断
			 while(flang !=2) {
				
				c2.await();
			}
			//干活
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoop: "+totalLoop);
			}
			
			//换醒/通知
			flang =3;
			c3.signal();
		} catch (Exception e) {
		}finally {
			lock.unlock();
		}
	}
	
	public void T3(int totalLoop) {
		lock.lock();
		try {
			//判断
			while (flang !=3) {
				
				c3.await();
			}
			//干活
			for (int i =1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoop: "+totalLoop);
			}
			//换醒/通知
			flang =1;
			c1.signal();
		} catch (Exception e) {
		}finally {
			lock.unlock();
		}
	}
}
