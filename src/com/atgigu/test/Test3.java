package com.atgigu.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 作者：wangshan
 * 2017.7.20
 * 
 * 三个卖票员的故事
 * 
 * 1.线程		操作		 	资源类
 * 2.高离		低耦合
 */
public class Test3 {
	
	public static void main(String[] args) {
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————		
		paio paio = new paio();
		//线程/操作
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					paio.saleTicket();
				}
			}
		},"AA").start();
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					paio.saleTicket();
				}
			}
		},"BB").start();
		
		
		new Thread(new Runnable() {
		@Override
		public void run() {
		for (int i = 0; i < 40; i++) {
			paio.saleTicket();
			}
		}
		},"CC").start();
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————
		new Thread(()->{for (int i = 0; i < 40; i++) {paio.saleTicket();}},"AA").start();
		new Thread(()->{for (int i = 0; i < 40; i++) {paio.saleTicket();}},"BB").start();
		new Thread(()->{for (int i = 0; i < 40; i++) {paio.saleTicket();}},"CC").start();
		//new Thread((x,y)->{for (int i = 0; i < 40; i++) {paio.add();}},"DD").start();
//——————————————————————————————————————————————————————————————————————————————————————————————————————————————
	}
}

//资源类
class paio{
		private int count = 50;
		private Lock lock = new ReentrantLock(); //多态
		
		public void saleTicket(){
			
			lock.lock();
			try {
				if (count > 0) {
					
					System.out.println(Thread.currentThread().getName()+"\t卖出第"+(count--)+"\t还剩下"+count+"张票");
				}
			} catch (Exception e) {
			}finally {
				lock.unlock();
			}
			
		}
		public void add(int x,int y) {
			System.out.println(x+y);
		}
}
