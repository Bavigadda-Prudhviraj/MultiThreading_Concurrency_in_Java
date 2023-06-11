package com.prudhviraj.thread_states;

public class Main {

	public static void main(String[] args) {
		Thread thread=new Thread(()->{
			try {
				Thread.sleep(1);
				for (int i = 100; i > 0; i--) {
					System.out.println(i);
				};	
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"thread_States");
		thread.start();
		while (true) {
			Thread.State state=thread.getState();
			System.out.println("Thread state is "+state);
			if(state== Thread.State.TERMINATED ) {
				break;
			}
			
		}
	}

}
