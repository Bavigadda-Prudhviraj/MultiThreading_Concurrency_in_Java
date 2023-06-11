package com.prudhviraj.synchronization;
//Stack is DSA follows which follows FISO( first in last out), we are assuming array as stack 
public class Stack {
	//creating an array
	private int[] array;
	//stack top element
	private int stackTop;
	
	//Object lockObject; //synchronized block to check this remove synchronized from method and uncomment this line and above 
	
	public Stack(int capacity) {
		//here we are giving ability to fix the size of the stack by calling this method
		array=new int[capacity];
		//-1 index doesn't exist it indicates that no element is present in the stack
		stackTop=-1;
		
		//lockObject=new Object(); ////synchronized block to check this remove synchronized from method and uncomment this line 
	}
	public boolean isEmpty() {
		//if stack top -1 or <0, it means the stack is empty.
		return stackTop<0;
	}
	public boolean isFull() {
		//if the stack top equal to stack length-1, it indicates the stack is full.
		return stackTop>=array.length-1;
	}
	
	public synchronized boolean push(int element) {
		//every object can use the lock
		/*
		 	Note:- 1.why we are using synchronized block, because when popping and pushing happening parallel when we pop one index means reducing the array size then we are pausing the element at popped index it will give exception thats why we are giving access to one method( running only one method pausing another one) by using synchronized block.
		 		   2.why lock object we are passing, lock object is a key type how have lock they can access this method
		 		   3.lock can be any object we can pass directly new string(" randomString ") we can pass anything that has to be object.
		 		   4.in java synchronized lock we can use the lock object, we can use any object as lock
		 		   5.imp:- 
		 		   			1.To stop running parallel,we have to use the same lock for synchronized block,then only one method we get will run we have same lock, then execute another synchronized block even same thread tries to access two methods at the same time.  
		 		   			2.which ever thread gets access to this lock he will be only be access any of these methods other threads,have to wait
		 		   			3.These two methods may be completely different but bounded by the same lock object
		 */
		//synchronized (lockObject) { ////synchronized block to check this remove synchronized from method and uncomment this line and below
			//if element is greater than the capacity of the stack(array) it return false we can't add elements
			if(isFull())
				return false;
			//if stack is not full then we are increasing stack top first
			++ stackTop;
			try {
				//sleep(time in milliseconds) this method will pause execution for given time
				//this sleep method increases the chances for the stack being corrupted by one of the thread
				//while another is sleeping
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//at stack top we are adding that element
			array[stackTop]=element;
			return true;	
		//}	//synchronized block to check this remove synchronized from method and uncomment this line and above 
	}
	 public synchronized int pop() {
		 /*
		  	NOte:- 1. we use synchronized to to make particular peace of code as synchronized
		  		   2. In these we are explicitly we are creating an lock object passing that object as parameter for synchronized block
		  		   3. we have to pass the same lock object to synchronize the codes.
		  		   4 Instead of that we can make entire method as synchronized.
		  		   5. by making the method synchronized no need to pass lock object explicitly
		  		   6. Internally what happens means
		  		        //Note:- this we use current object as the log
		  		   		synchronized(this){
		  		   			// our code written in the method
		  		   		}
		  */
		 //same log lock object as above method
		 
		 //synchronized (lockObject) { //synchronized block, //synchronized block to check this remove synchronized from method and uncomment this line and above 
			//we are checking is stack empty if true means returning infinity values.
			 if(isEmpty())
				 	return Integer.MIN_VALUE;
			 //we are getting the last element(top stack element)
			 int obj=array[stackTop];
			 //by placing an infinity value a placeholder,then we are trying to make thread sleep
			 array[stackTop]=Integer.MIN_VALUE;
			 try {
				 //making thread sleep for one second
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 //then we are decreasing the size of the stack(array size);
			 stackTop--;
			 //Returning the removed stack top;
			 return obj;
			
		//} //synchronized block to check this remove synchronized from method and uncomment synchronized block
		 
		
	}

}
