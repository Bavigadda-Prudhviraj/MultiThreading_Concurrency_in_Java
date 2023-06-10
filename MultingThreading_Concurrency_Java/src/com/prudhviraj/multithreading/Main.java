package com.prudhviraj.multithreading;

public class Main {
	/*
	  Imp:-
	  		1.Either we pass a runnable instance which set the target of the thread class to our instance and then our instance run method will executed
	  		2.extend the thread class and we order it designed with.
	 */

	public static void main(String[] args) {
		//main thread start running
		System.out.println("Main is starting");
		//---------------Thread_By_Extending_Thread_Class--------------------
		//instantiate with object of thread class which extends thread class
		Thread thread=new Thread_By_Extending_Thread_Class("NameOfTheThread");// we can give name to thread by passing the name of the string in constructor by over loading the constructor in thread extended classes. 
		
		//this will make thread as daemon thread
		//as there is in notes program will terminate when the user threads are done, even the daemon thread is running.it wont execute
		//comment this while checking the normal thread
		//*Note*:-to check Daemon thread uncomment this one
		//thread.setDaemon(true);
		
		//This start method don't start immediately
		//it is asynchronous method it return immediately
		//order doesn't followed by start method we can check by running the program. the order of 1st and last s.o.p and thread is not same for every one 
		//this start() method will call run method at some point of time to execute the program
		thread.start();
		
		
		
		
		
		
		//---------------Thread_by_impelemting_Runnable_Interface-------------------
		
		//The thread has another constructor we can pass object of the runnable interface
		// In this we can pass thread name it is optional
		Thread thread2=new Thread(new Thread_by_impelemting_Runnable_Interface(),"threadName");
		thread2.start(); 
		
		
		//lambda Implementation
		//here we are passing implementation on the run method as parameter
		//By this method we no need to create an separate class which implements runnable, and over ride run method
		//by passing implementation as parameter in constructor, it internally creates class implements the runnable override the method by parameter(code in constructor)
		Thread thread3=new Thread(()->
		{
			for(int i=0;i<5;i++) {
				System.out.print(i+" ");
				
			}
		},"threadName");
		
		
		
		
		
		
		
		
		//here main thread is done and exiting
		System.out.println("Main is exiting");

	}

}
