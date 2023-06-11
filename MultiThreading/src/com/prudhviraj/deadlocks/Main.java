package com.prudhviraj.deadlocks;

import java.lang.Thread.State;

import javax.lang.model.element.ExecutableElement;

public class Main {
	/*
	 1.Here's what happens:
		1.thread1 acquires resource1 and enters its synchronized block.
		2.thread2 acquires resource2 and enters its synchronized block.
		3.Both threads are now waiting for the other thread to release the resource they need to proceed.
		4.As a result, both threads are blocked indefinitely, and a deadlock occurs.
	2.To prevent deadlocks, it's essential to ensure proper ordering of resource acquisition and release. In this example, modifying the order in which the threads acquire resources can resolve the deadlock. Alternatively, using mechanisms like Lock objects from the java.util.concurrent package can provide more control over resource locking and unlocking, helping to avoid deadlocks.
	3.It's worth noting that detecting and resolving deadlocks in complex multithreaded applications can be challenging. Various debugging and profiling tools, such as thread dump analysis, can help identify deadlocks and analyze the root causes. It's crucial to design and implement multithreaded applications with careful consideration of resource dependencies and synchronization mechanisms to minimize the chances of deadlocks.
	 */

	public static void main(String[] args) {
		String lock1="lock1";
		String lock2="lock2";
		
		Thread thread1=new Thread(()->{
			//here thread one is started,thread1 and thread2 are not synchronized because there lock are different they start immediately because we are using start(); 
			synchronized (lock1) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//here we required lock 2 will release the lock
				synchronized (lock2) {
					//to reach this point of execution it needs thread two lock to release,but it wont happen because to get thread two lock we need to terminate this(thread one) execution it wont stop executing until it gets thread two lock(lock2) key 
					System.out.println("thread one lock is acquired");
					
				}
				
			}
		},"first thread");
		
		Thread thread2=new Thread(()->{
			//here thread one is started,thread1 and thread2 are not synchronized because there lock are different they start immediately because we are using start(); 
			synchronized (lock2 ) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//here we required lock 1 will release the lock
				synchronized (lock1) {
					//to reach this point of execution it needs thread one lock to release,but it wont happen because to get thread one lock we need to terminate this(thread two) execution it wont stop executing until it gets thread one lock(lock1) key 
					System.out.println("thread two lock is acquired");
					
				}
				
			}
		},"second thread");
		

		thread1.start();
		thread2.start();
		if(thread1.getState()== State.TERMINATED && thread2.getState()==State.TERMINATED) {
			System.out.println("this code won't because thread one is waiting of lock by thread two, and thread two is waiting for lock to realse by thread one ");
		}
		System.out.println("this code runes will keep on running");
	}

}
