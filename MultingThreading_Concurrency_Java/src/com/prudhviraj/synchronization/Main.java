package com.prudhviraj.synchronization;

public class Main {

	public static void main(String[] args) {
		//here we are creating the array with 5 capacity ( stack )
		Stack stack=new Stack(5);
		/*NOTE:-1.two threads are running in parallel.
		 		2.
		 */
				
				
		
		//one thread to push on same stack
		new Thread(()->{
			//we are passing the run method implementation go to multithreading main class to know more about it.
			int counter=0;
			//this loop we are trying to push 100 for ten times 
			while (++ counter<10) {
				System.out.println("Pushed: "+stack.push(100));
			}
		},"Pusher").start();
		
		//second thread to pop on same stack
		new Thread(()->{
			int counter=0;
			//here we are trying 10 times to pop 
			while (++ counter<10) {
				System.out.println("Popped: "+stack.pop());
			}
		},"Poppded").start();
		
	}

}
