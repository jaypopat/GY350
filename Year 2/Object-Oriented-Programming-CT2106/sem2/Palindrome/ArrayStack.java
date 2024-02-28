/** Array implementation of Stack ADT  */

import javax.swing.JOptionPane;

public class ArrayStack implements Stack
{
   protected int capacity; 			// The actual capacity of the stack array
   protected static final int CAPACITY = 1000;	// default array capacity
   protected Object S[];			// array used to implement the stack 
   protected int top = -1;			// index for the top of the stack
   
   public ArrayStack() {
	   // default constructor: creates stack with default capacity
	   this(CAPACITY);
	   Main.numOperations_stackAndQueues++;

   }

   public ArrayStack(int cap) {
	  // this constructor allows you to specify capacity of stack
	  capacity = (cap > 0) ? cap : CAPACITY;
	  S = new Object[capacity]; 
   }
   
   public void push(Object element) {
	 if (isFull()) {
		 Main.numOperations_stackAndQueues+=2; // function call and checking if value returned is equal to true, hence 2 operations

		 JOptionPane.showMessageDialog(null, "ERROR: Stack is full.");// invokes a showMessageDialog method hence 1 operation
		 Main.numOperations_stackAndQueues++;
	   return;
	 }
	 top++; // increment operation
	   Main.numOperations_stackAndQueues++;
	 S[top] = element;// allocation operation
	   Main.numOperations_stackAndQueues++;
   }

   public Object pop() {
	  Object element;
	  if (isEmpty()) {
		  Main.numOperations_stackAndQueues+=2;
	     JOptionPane.showMessageDialog(null, "ERROR: Stack is empty.");
		  Main.numOperations_stackAndQueues++;
	     return  null;
	  }
	  element = S[top];
	   // allocation operation
	   Main.numOperations_stackAndQueues++;
	  S[top] = null;
	   // allocation operation
	   Main.numOperations_stackAndQueues++;
	  top--;
	   // decrement operation
	   Main.numOperations_stackAndQueues++;
	  return element;
   }

   public Object top() {
	 if (isEmpty()) {
		 JOptionPane.showMessageDialog(null, "ERROR: Stack is empty.");
		 return null;
	 }
	 return S[top];
   }
	   
   public boolean isEmpty() {
	   // evaluates expression 'rear<0' into a boolean
	   Main.numOperations_stackAndQueues++;
	   return (top < 0);
   }

   public boolean isFull() {
	   // evaluates expression 'top == capacity-1' into a boolean
	   Main.numOperations_stackAndQueues++;
	   return (top == capacity-1);
   }

   public int size() { 
		 return (top + 1);
   }
 }