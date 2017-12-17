package com.atgigu.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 
 * 作者:wangshan
 * 2017.7.20
 * 
 * 三个售票员卖票的故事
 * 
 * 1. 线程    		操作		资源类
 * 2. 高聚合		低耦合
 */
public class ThreadDemo01{
		
	public static void main(String[] args) {
//————————————————————————————————————————————————————————————————————————————————————————————————
		Ticket ticket = new Ticket();
		//线程/操作
		new Thread(new Runnable() {
			@Override
			public void run() {
				 for (int i = 0; i < 40; i++) {
					ticket.saleTicket();
				}
			}
		},"AA：").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				 for (int i = 0; i < 40; i++) {
					ticket.saleTicket();
				}
			}
		},"BB:").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				 for (int i = 0; i < 40; i++) {
					ticket.saleTicket();
				}
			}
		},"CC:").start();
//————————————————————————————————————————————————————————————————————————————————————————————————
		//lambda表达式：
		new Thread(()->{ for (int i = 0; i < 40; i++) {ticket.saleTicket();}},"AA:").start();
		new Thread(()->{ for (int i = 0; i < 40; i++) {ticket.saleTicket();}},"BB:").start();
		new Thread(()->{ for (int i = 0; i < 40; i++) {ticket.saleTicket();}},"CC:").start();
//————————————————————————————————————————————————————————————————————————————————————————————————		
	}
}
//资源类
class Ticket {
	private int number = 40;
	private Lock lock = new ReentrantLock();  // List list = new ArrayList<>();
	
	public void saleTicket() {
		
		lock.lock();
		try {
			
			if(number >0 ) {
				
				System.out.println(Thread.currentThread().getName()+"\t卖出第"+(number--)+"\t还剩下"+number+"张票");
			}
		} catch (Exception e) {
			
		}finally {
			lock.unlock();
		}
	}
}

