/**
* Class to implement a stack using singly linked list
*  
* @author Derek Holsapple, Gabriel Grant
* @version 4/24/2019
*/

import static java.lang.System.exit; 
  
// Create Stack Using Linked list 
class StackLinkedlist { 
  
    // Linked list node 
    private class Node { 
  
        String data; // integer data   
        Node link;   // reference variable Node type 
        
      } // End class
      
    // Create global top reference variable 
    Node topNode;

// ********************************************************************************      
/**
* Default constuctor - Sets up empty collection.
*
*/  
    StackLinkedlist() { 
    
        this.topNode = null; 
        
      } // End constructor
    
// ******************************************************************************** 
/**
* Puts a new entry on the top of the stack
* 
* @param newEntry -  The string to be added to the stack
*/       
   public void push( String newEntry ) 
   {
      Node newNode = new Node();
     
           // Initialize data into temp data field 
           newNode.data = newEntry; 
     
           // Put top reference into temp link 
           newNode.link = topNode; 
     
           // Update top reference 
           topNode = newNode;
      } // End push

// ********************************************************************************
/**
* Return the top of the stack, If the stack is empty throw a exception
  
* @return topNode.data -  Return the top node being a string
*/ 
    public String peek() //throws EmptyStackException          
      { 
        // Check for empty stack 
        if ( !isEmpty() ) { 

            return topNode.data;
             
         } // End if
        else { 

            //throw new EmptyStackException( "Stack is Empty" ); 
           return "Stack is Empty"; 
         } // End else
    } // End peek
    
// ********************************************************************************
/**
* Remove the top of the stack, Check for stack underflow 
*/ 
    public void pop() // remove at the beginning 
    { 
        // check for stack underflow 
        if ( topNode == null ) { 
            System.out.print( "\nStack Underflow" ); 
            return; 
        } // End if
  
        // update the top pointer to point to the next node 
        topNode = ( topNode ).link; 
        
      } // End pop
      
// ********************************************************************************
/**
 * Clear the stack  
*/ 

    public void clear() 
      { 
        topNode = null; 
      } // End clear
      
// ********************************************************************************
/** 
 * Check to see if the collection is empty.
 *
 * @return True if the collection is empty, or false if not.
*/ 
    // Check if the stack is empty or not 
    public boolean isEmpty() 
    { 
        return topNode == null; 
        
    } // End isEmpty

// ********************************************************************************
/** 
 * Display the content of the stack.  Check for stack underflow.
*/ 
    public void display() 
    { 
        // check for stack underflow 
        if ( topNode == null ) {
         
            System.out.printf( "\nStack Underflow" ); 
            exit( 1 ); 
            
        } // End if
        else { 
        
            Node newNode = topNode; 
            while ( newNode != null ) { 
  
                // print node data 
                System.out.println( newNode.data ); 
  
                // assign temp link to temp 
                newNode = newNode.link; 
                
               } // End while
           } // End else
       } // End display
       
// ******************************************************************************** 
      
   } // End class StackLinkedlist