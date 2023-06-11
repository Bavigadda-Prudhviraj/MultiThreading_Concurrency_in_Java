package com.prudhviraj.thread_join;

import java.lang.Thread.State;

public class Main {

	public static void main(String[] args) {
		Thread customThread=new Thread(()->{
			System.out.println(Thread.currentThread());
		},"First thread");
		//start is asynchronous call it will start immediately
		//in this situation the main thread will execute separately
		//and the custom thread execute separately
		//if we want stop this asynchronous thing,suppose we need to complete the custom thread execution then end the main thread execution, for that we use thread join
		customThread.start();
		
		
		
		try {
			//when ever we call join() method,it will execute all his child threads compete first and then program execution will continue the flow.it will stop the execution of other method until it gets completed
			customThread.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//it in runnable(non-run) state because we used join method on custom thread so,it will be in runnable state until. the custom thread execution is completed
		System.out.println("Main thread is exiting and state is "+Thread.currentThread().getState());
		System.out.println("custom thread state: "+customThread.getState());
		
		
		

	}

}
