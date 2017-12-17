package com.atgigu.test;


//———————————————————————————————————————————————————————————————————————————————————————————————
/*
* lambda表达式/函数式编程
* 
* 
* 写法一：
* 		(参数（只匹配参数，不匹配类型）) -> {方法体（无返回值）}; 
* 写法二：
* 		(参数（只匹配参数，不匹配类型）) -> {return x/y;}
* 写法三：
* 		接口名.方法名
* 写法四：
* 		lambda只需要一个参数时，括号都可以省略
* 写法五：
* 		当 Lambda 体只有一条语句时，return 与大括号可以省略
*/
//——————————————————————————————————————————————————————————————————————————————————————————————
public class Test1 {
		
	public static void main(String[] args) {
//——————————————————————————————————————————————————————————————————————————————————————————————		
/*		MyFoo test = new MyFoo() {
			@Override
			public void add(int x) {
				System.out.println("——————hello:"+x);
			}
		};
	
		test.add(2);*/
//——————————————————————————————————————————————————————————————————————————————————————————————
		// 写法一：
//		MyFoo test2 = (x)->{System.out.println("——————hello:"+x);};
//		test2.add(4);
		
		//写法五：当 Lambda 体只有一条语句时，return 与大括号可以省略
		MyFoo test3 = (x,y) ->  x/y;;
		System.out.println(10/2);
		
		//写法三：
		MyFoo.add(2, 5);
	}
}

interface MyFoo{
	
	//public void add(int x);
	public int sub(int x,int y);
	
	/*default int Div(int x, int y) {
		return 	x/y+1;
	}
	
	default int Div2(int x, int y) {
		return 	x/y;
	}
*/
	public static void add(int x,int y) {
		
		System.out.println("——————————hello:java——————————");
	}
}