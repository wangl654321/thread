package com.atgigu.test;


public class Test2 {
		
		public static void main(String[] args) {
//————————————————————————————————————————————————————————————————————————————————————————————————————————————————————		
			   /*
			    * 只作用在新生区，养老区，不作用在永久区
			    * MAX_MEMORY =1655701504(字节)，=1579.0MB	-Xmx：最大分配内存量，默认为物理内存1/4
			    * TOTAL_MEMORY =112721920(字节)，=107.5MB	-Xms :设置初始分配大小，默认为物理内存的1/64
			    * 
			    * 
			    * 可以改变maxMemory，totalMemory 大小在Run configurations设置参数
			    */
//————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
				long maxMemory = Runtime.getRuntime().maxMemory();      //返回java虚拟机使图使用的最大内存量
				long totalMemory = Runtime.getRuntime().totalMemory();  //返回java虚拟机的内存总量
				
				System.out.println("java虚拟机使图使用的最大内存量:"+"MAX_MEMORY ="+maxMemory+"(字节)，="+(maxMemory /(double)1024/1024)+"MB");
				System.out.println("java虚拟机的内存总量:"+"TOTAL_MEMORY ="+totalMemory+"(字节)，="+(totalMemory / (double)1024/1024)+"MB");
		}
}
