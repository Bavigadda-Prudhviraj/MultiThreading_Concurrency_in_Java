package com.prudhviraj.producter_consumer_problem;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
	
	private Queue<Integer> queue;
	
	private int capacity;
	
	public BlockingQueue(int queueCapacity) {
		queue=new LinkedList<>();
		capacity=queueCapacity;
	}
	
	public boolean add(int item) {
		//we can use queue object as lock,we can use any object as lock it has to be same for synchronized methods,for more details view synchronized branch
		synchronized (queue) {
			/*
			 The problem arises when there are two adding threads and one removing thread:
					1.If Adder Thread 1 gets access, it will wait because the capacity is full.
					2.Then the removing thread starts executing and removes an item from the stack.
					3.It notifies all threads that elements have been removed.
					4.Now, the first Adder thread can execute and add elements.
					5.However, since the capacity is full again, the second Adder thread will wait until an element is removed. This is the problem.
					6.To solve this problem, we use a while loop.

			Now, during execution:

					1.When Adder Thread 1 wins the lock, let's assume both threads are awakened. They are removed from the wait set and both try to acquire the lock again.
					2.Remember, when a particular thread calls the wait() method, it relinquishes the lock. It no longer holds the lock.
					3.When it is awakened again, it fights for the lock just like other threads were doing.
					4.Once it acquires the lock, it resumes its execution from the while loop.
					5.If there is another while loop after the first one, it checks the condition again. The first thread checks if only one room is left, so it can add an item, notify, and return true.
					6.Now, Adder Thread 2 gets the lock to execute and checks the capacity of the queue again due to the while loop. If the capacity is full, it goes back to the wait set.
					7.Here, we maintain consistency by not allowing more items to be added than the capacity of the queue.
			Note that we must use a while loop instead of an if condition. If we use an if condition, we won't have the correct condition for the total number of threads.
			 
			 */
			while(queue.size()==capacity) { 
				//Do something we can't add items the capacity size if full.
				try {
					/*
					 //now thread is waiting state because capacity==full;
					//once the removing thread is removed an item it notify all threads that item is removed
					//now it will add that element,when wait(),gets notification that elements is removed the capacity is queue.size()-2
					//This is the case when two threads trying to access synchronized methods of add and remove at a time.
					//when add thread get access when capacity is full before the remove thread, it will wait until remove thread removes elements from stack
					 */
					queue.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			queue.add(item);
			//it says to all threads when an item is added.it is for remove method wait function to notify
			queue.notifyAll();
			return true;
			
			
			
		}
		
	}
	public int remove(int item) {
		synchronized (queue) {
			while(queue.size()==0) {
				/*
				1.Do Something, because the queue is empty we can't remove item because the no items are there
				2.suppose if two threads are there add and remove remove thread get first access,then there is no element are there to remove
				3.In this case the thread has to wait for the condition
				4.The condition is," it has to wait until any add thread is adding element after adding it has to start removing elements"
				 imp:- Object class have three methods,the object class already implemented this class
				 			i.wait();
				 			ii.notify();// it will notify to the single thread
				 			iii.notifyAll();// it will notify to all threads
				 		Note:-1.queue is extends the object class so,it has all above methods wait(),notify(),notifyAll()
				  */
				try {
					//now thread is waiting state because capacity==0;
					//once the adding thread is added an item it notify all threads that item is added
					//now it will remove that element
					//This is the case when two threads trying to access synchronized methods of add and remove at a time.
					//when remove thread get access when the capacity==0 before the add thread, it will wait until add thread adds elements,and get notified from added thread that element is added then it will remove element 
					queue.wait(); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				 
				
			}
			int element=queue.poll();
			//it will notify to the waiting thread that element is removed from the stack
			queue.notifyAll();
			return element;
		}
		
	}
}
