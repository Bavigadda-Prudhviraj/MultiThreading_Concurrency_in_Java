package com.prudhviraj.multithreading;

/*
//NOTE:- " Thread class also implements the runnable interface
		   1.Runnable class in not abstract class, we can instantiate the thread object directly by
		   		new Thread().run();
		   	2.One thing is for sure this thread is  implementing runnable interface, it has to give a concrete implementation of the run method
		   	3.we also called run method, by calling above way run method noting happens
		   	4.Actually Run method in thread class
		   		@override
		   		public void run(){
		   			if(target != null ){
		   				target.run(); // to execute or run the code ensure that target !=null, it will execute according to your code overridden the run method in the thread class
		   			}
		   		}
		   	5.Thread class has an object of runnable interface that is "target" it is a type of runnable interface.
		   	6.initially it is null.if we run the thread, it will check weather thread is null or something else
		   	  if it is null nothing will happen, other than null means it will call the 
		   
*/
public class Thread_By_Extending_Thread_Class extends Thread {
	//constructor overloading for naming the thread
	public Thread_By_Extending_Thread_Class(String threadName) {
		
		super(threadName);
	}

	//this thread class has run method we are over riding that run method
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.print(i+" ");
		}
		System.out.println("thread executing which is created by extending the thread class and overriding the run method");
		
		//here current thread means the thread which is running now, the get name method will give the name of the current thread which is running now
		System.out.println(Thread.currentThread().getName());
	}

}
