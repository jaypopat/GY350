import javax.swing.JOptionPane;

/**
 * Array implementation of Queue ADT
 */

public class ArrayQueue implements Queue 
{
	 protected Object Q[];				// array used to implement the queue 
	 protected int rear = -1;			// index for the rear of the queue
	 protected int capacity; 			// The actual capacity of the queue array
	 public static final int CAPACITY = 1000;	// default array capacity
	   
	 public ArrayQueue() {
		   // default constructor: creates queue with default capacity
		   this(CAPACITY);
		   Main.numOperations_stackAndQueues++;
	 }

	 public ArrayQueue(int cap) {
		  // this constructor allows you to specify capacity
		  capacity = (cap > 0) ? cap : CAPACITY;
		  Q = new Object[capacity]; 
	 }
	 
	 public void enqueue(Object n)
	 {
		 if (isFull()) {
			 Main.numOperations_stackAndQueues+=2; // function call and checking if value returned is equal to true, hence 2 operations

			 JOptionPane.showMessageDialog(null, "Cannot enqueue object; queue is full."); // invokes a showMessageDialog method hence 1 operation
			 Main.numOperations_stackAndQueues++;

			 return;
		 }
		 rear++; // increment operation
		 Main.numOperations_stackAndQueues++;

		 Q[rear] = n; // allocation operation
		 Main.numOperations_stackAndQueues++;

	 }
	 
	 public Object dequeue()
	 {
		 // Can't do anything if it's empty
		 if (isEmpty()) {
			 // calls function and compares if value returned = true
			 Main.numOperations_stackAndQueues++;
			 return null;
		 }
		 
		 Object toReturn = Q[0];
		 // allocation operation
		 Main.numOperations_stackAndQueues++;
		 
		 // shuffle all other objects towards 0
		 int i = 1;
		 //  initialises var to be 1; (allocates value)
		 Main.numOperations_stackAndQueues++;
		 while (i <= rear) {
			 // checks if condition true - 1 operation evaluates i<=rear into a boolean, 1 operation while loop evaluates ie if true/false
			 Main.numOperations_stackAndQueues+=2;
			 Q[i-1] = Q[i];
			 // allocation operation
			 Main.numOperations_stackAndQueues++;
			 i++;
			 // increment operation
			 Main.numOperations_stackAndQueues++;
		 }
		 rear--;
		 // decrement operation
		 Main.numOperations_stackAndQueues++;
		 return toReturn;
	 }
	 
	 public boolean isEmpty()  {
		 // evaluates expression 'rear<0' into a boolean
		 Main.numOperations_stackAndQueues++;
		 return (rear < 0);
	 }
	 
	 public boolean isFull() {
		 Main.numOperations_stackAndQueues++; // evaluates expression 'rear == capacity-1' into a boolean

		 return (rear == capacity-1);
	 }
	 
	 public Object front()
	 {
		 if (isEmpty())
			 return null;
		 
		 return Q[0];
	 }
}