package src;

// necessary imports
import java.util.EmptyStackException;

/**
 * Stack class to hold values from the file.
 * The stack class is generic; therefore, able to hold different data types.
 * Created as a linked list structure due to array requiring size which is not
 * known when reading from file.
 */
public class LinkedStack<Thing> {

   /**
    * Node class so each node has reference to its data, as well as the next node.
    * This is an example of singly-linked list
    */
   private class Node {
      Thing data;
      Node next;
   }

   // reference to the top node
   private Node top;
   // reference to the size of the stack
   private int size;

   /**
    * push method to add an element to the stack
    * 
    * @param input Accepts a value from the user to push to the stack. Data type
    *              defined by the user when creating the stack instance.
    */
   public void push(Thing input) {
      Node temp = new Node();
      temp.data = input;
      temp.next = top;
      top = temp;
      size++;
   }

   /**
    * pop method which deletes the top element from the stack (LIFO)
    * 
    * @return Returns the top element from stack. This element has been removed
    *         stack, as well.
    */
   public Thing pop() {
      if (isEmpty()) {
         throw new EmptyStackException();
      }
      Thing result = top.data;
      top = top.next;
      size--;
      return result;
   }

   /**
    * size method to return the size of the stack
    * 
    * @return The size of the stack
    */
   public int size() {
      return size;
   }

   /**
    * isEmpty method to determine whether the stack is empty or not
    * 
    * @return If the size of stack is 0, return true; otherwise, return false
    */
   public boolean isEmpty() {
      if (size == 0) {
         return true;
      } else {
         return false;
      }
   }

   /**
    * peek method to identify the value on top of the stack. Requires at least one
    * value inside stack for this method to be utilized.
    * 
    * @return Top value on the stack
    */
   public Thing peek() {
      if (isEmpty()) {
         throw new EmptyStackException();
      }
      return top.data;
   }

   /**
    * display method to display all the elements in the stack
    */
   public void display() {
      Node temp = top;
      while (temp != null) {
         System.out.println(temp.data);
         temp = temp.next;
      }
   }

}
