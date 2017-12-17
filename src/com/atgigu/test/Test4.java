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
public class Test4 {
		
	public static void main(String[] args) {
//————————————————————————————————————————————————————————————————————————————————————————————————————————————————		
		piao piao = new piao();
		//线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					piao.SalePiao();
				}
			}
		},"AA").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					piao.SalePiao();
				}
			}
		},"bb").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					piao.SalePiao();
				}
			}
		},"cc").start();
//————————————————————————————————————————————————————————————————————————————————————————————————————————————————
		new Thread(()-> {for (int i = 0; i < 50; i++) {piao.SalePiao();}},"AA").start();
		new Thread(()-> {for (int i = 0; i < 50; i++) {piao.SalePiao();}},"BB").start();
		new Thread(()-> {for (int i = 0; i < 50; i++) {piao.SalePiao();}},"CC").start();
//————————————————————————————————————————————————————————————————————————————————————————————————————————————————
	}
		
}

//资源类
class piao{
	private int count = 50;
	private Lock lock = new ReentrantLock();
	
	public void SalePiao() {
		
		lock.lock();
		try {
			
			if (count > 0) {
				System.out.println(Thread.currentThread().getName()+"\t卖出:"+count--+"\t还剩下"+count+"张票");
			}
		} catch (Exception e) {
		}finally {
			lock.unlock();
			
		}
	}
}