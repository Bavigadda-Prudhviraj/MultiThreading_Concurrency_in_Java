package com.prudhviraj.multithreading;

public class Thread_by_impelemting_Runnable_Interface implements Runnable{
	//runnable is the functional interface which have one abstract we need to implement 
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.print(i+" ");
		}
		System.out.println("executing the Thread_by_impelemting_Runnable_Interface");
		//Thread.currentThread() method will all information current thread 
		
	}

}
