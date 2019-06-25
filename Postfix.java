/**
* Class to convert a pre-fix expression into a post-fix expression
*  
* @author Derek Holsapple, Gabriel Grant
* @version 4/24/2019
*/

import java.util.*;

// ********************************************************************************

public class Postfix {

   // Strings used to store temporary data for Postfix conversion
   private static String flag2String;
   private static String flag3String;
    
// ********************************************************************************
   /**
   * convertToPostfix
   
   * Method to convert a pre-fix expression into a post-fix expression
   
   * @return joined,         -String of post-fix expression
   
   * @param inputExpression, -String of pre-fix expression                              
   */      
   
   public static String convertToPostfix(String inputExpression){
   
      // Create a array to store the pre-fix expression 
      String[] myStringArray = inputExpression.split( "" );        
      
      String evaluateChar; // String to evaluate top of the stack

      // List to store incoming pre-Fix String into individual string elements
      List< String > postFixList = new ArrayList< String >();
      // Create a stack linked list to process incoming pre-Fix String array
      StackLinkedlist inFixList = new StackLinkedlist();

      int count = 0; // Counter for determining location inside myStringArray
      
      // Flags used to follow PEMDAS rules of operation
      int flag1 = 0; // Flag if a subtraction or addition operator is activated
      int flag2 = 0; // Flag if a multiplication or devision operator is activated
      int flag3 = 0; // Flag if a exponent operator is activated
      
      String joined; // String of post-fix expression
      
     // ******************************** 

      // Check for errors within the file      
      if ( variableError(myStringArray) ) {
            return "Error: Two operands procede one another.";
            }
      else if ( operatorError(myStringArray) ) {
            return "Error: Two operators procede one another.";
            }
      else if ( deliminatorError(myStringArray) ) {
            return "Error: Syntax error with deliminators.";
            }            

     // ********************************  
 
   //Begin routine
      // Put the first string on top of the stack
      inFixList.push( myStringArray[ 0 ] );  
   
      // Loop to iterate through all characters of the incoming pre-fix string
      for( int i = 1; i < myStringArray.length + 1; i++ ) {
      
         count++; // Counter for determining location inside myStringArray
            
         // String to determine the top of the stack
         evaluateChar = inFixList.peek();  
   
   
     // Determine if the top of the stack is a operator or a variable
     // If operator run subroutine, else add variable to the postFixList
   
     // ********************************  
     // Top of stack equals " ( "
     
         // Deliminator open, move on to the next element
         if ( evaluateChar.equals( "(" ) ) {

            inFixList.push( myStringArray[ i ] );  
            
            } // End if
   
     // ********************************
     // Top of stack equals " ) "
 
            // Routine to unload content inside of the deliminators
            else if ( evaluateChar.equals( ")" ) ) {
            
               // Reset flags, No power orders after the deliminatr subroutine
               flag1 = 0;
               flag2 = 0;
               flag3 = 0; 
            
               // Remove ")" from the top of the stack
               inFixList.pop();
               
               // Determine top of stack relative to inside the deliminator
               String deliminatorRoutine = inFixList.peek();
               
               // Loop to unload the inside the deliminators
               while ( !deliminatorRoutine.equals( "(" ) ) {
               
                  postFixList.add( inFixList.peek() );
                  inFixList.pop();
                  deliminatorRoutine = inFixList.peek(); 
                  
                } // End while 
                
               // Remove the ")" from the stack
               inFixList.pop();
               
               // Insure there is more content to put on the stack before pushing
               if( count < myStringArray.length ) {
               
                     inFixList.push( myStringArray[ i ] ); 
                     
                  } // end if
   
               // If at the end of the prefix expression expression, 
               // Ensure operators between two deliminators are unloaded
               else if ( !inFixList.isEmpty() && 
                         !inFixList.peek().equals( ")" ) ) {
               
                 // Unload the operators between deliminators, empty the stack
                 // The postfix list will be complete, End routine
                     while( !inFixList.isEmpty() ) {
                     
                        postFixList.add( inFixList.peek() ); 
                        inFixList.pop();
                        
                     } // End while
                  } // End else if
               } // End else if

     // ********************************
     // Top of stack equals " ^ " 
               
            else if ( evaluateChar.equals( "^" ) ) {
            
               // Case where there is a "^" on top of a "^"
               if ( flag3 == 3 ){
                         
                  flag3 = 3; // Set flag to signify "^" is on the stack
                  // Pop "^" off the stack and push the new "^"  
                  inFixList.pop();
                  inFixList.pop();
                  inFixList.push( myStringArray[ i-1 ] );
                  
                  // Add the previous "^" to the post-fix list
                  postFixList.add( flag3String );
                   
                   } // End if
                   
               // If the ")" deliminator is directly before the operator then 
               //   current "^" will not be popped until the end of the expression
               else if ( myStringArray[ i-2 ].equals( ")" ) ) {
               
                  flag3 = 0;
                  
                  } // End else if
                  
               else{
               
                  flag3 = 3;
                  
                  } // end Else
               
               // Set the new "^" to a temporary string for the next loop   
               flag3String = evaluateChar;
               
               // Add the next element to the stack to be processed
               inFixList.push( myStringArray[ i ] );
            } // End else if
   
     // ********************************
     // Top of stack equals " * " 
        
            else if ( evaluateChar.equals( "*" ) ) {
            
               // Case where there is a "*" or "/" on top of a "*"
               if ( flag2 == 2 ){    
                     flag2 = 2;
                     // Pop either a "*" or "/" off the stack and push the new "*"  
                     inFixList.pop();
                     inFixList.pop();
                     inFixList.push( myStringArray[ i-1 ] );

                     // Add the previous "*" or "/" to the post-fix list
                     postFixList.add( flag2String );

               } // End  if 
               // Case where there is a "^" on top of a "*" 
               else if ( flag3 == 3 ){    
                     // Pop either a * or / off the stack 
                     inFixList.pop();
                     inFixList.pop();
                     inFixList.push( myStringArray[ i-1 ] );  
                       
                     // Add the previous "*" or "/" to the post-fix list
                     postFixList.add( flag3String );
                     flag2 = 2;
               } // End  if
                
               // If the ")" deliminator is directly before the operator then 
               //   current "*" will not be popped until the end of the expression
               else if ( myStringArray[ i - 2 ].equals( ")" ) ) {
                  flag2 = 0;
                  } // End else if
                  
               else{
                  flag2 = 2;
                  } // end Else
                  
               // Set the new "*" to a temporary string for the next loop  
               flag2String = evaluateChar;
               
               // Add the next element to the stack to be processed
               inFixList.push( myStringArray[ i ] );  
               } // End if
   
     // ********************************
     // Top of stack equals " / " 
        
            else if ( evaluateChar.equals("/") ) {
            
               // Case where there is a "*" or "/" on top of a "*"
               if ( flag2 == 2 ){    
                     flag2 = 2;
                     // Pop either a "*" or "/" off the stack and push the new "/"  
                     inFixList.pop();
                     inFixList.pop();
                     inFixList.push( myStringArray[ i - 1 ] ); 

                     // Add the previous "*" or "/" to the post-fix list
                     postFixList.add( flag2String );
               } // End  if 
               
               // Case where there is a "^" on top of a "/"
               else if ( flag3 == 3 ){    
                     // Pop either a * or / off the stack
                     inFixList.pop();
                     inFixList.pop();
                     inFixList.push( myStringArray[ i - 1 ] );  
                      
                     // Add the previous "*" or "/" to the post-fix list
                     postFixList.add( flag3String );
                     flag2 = 2; 
               } // End  if 
               // If the ")" deliminator is directly before the operator then 
               //   current "/" will not be popped until the end of the expression
               else if ( myStringArray[ i - 2 ].equals(")") ) {
               
                  flag2 = 0;
                  
                  } // End else if
                  
               else{
               
                  flag2 = 2;
                  
                  } // end Else
                  
               // Set the new "/" to a temporary string for the next loop  
               flag2String = evaluateChar;
               
               // Add the next element to the stack to be processed
               inFixList.push( myStringArray[ i ] );  
               } // End if
   
     // ********************************
     // Top of stack equals " + "  
       
            else if ( evaluateChar.equals( "+" ) ) {
            
               flag1 = 1;
               
               // Case where there is a "*" or "/" on top of a "+"
               if( flag1 < flag2 ) {

                     // Pop either a "*" or "/" off the stack  
                     inFixList.pop();
                     inFixList.pop();
                     inFixList.push( myStringArray[ i - 1 ] ); 

                     // Add the previous "*" or "/" to the post-fix list
                     postFixList.add( flag2String );
                     
                     flag2 = 0; 
                     
                  } // End if 
                  
               // Case where there is a "^" on top of a "+"
               else if ( flag1 < flag3 ) {
               
                     // Pop "^" off the stack
                     inFixList.pop();
                     inFixList.pop();
                     inFixList.push( myStringArray[ i - 1 ] ); 
                       
                     // Add the previous "^" to the post-fix list
                     postFixList.add( flag3String );
                     
                     flag3 = 0; 
                  } // end else if
                  
               // Add the next element to the stack to be processed   
               inFixList.push( myStringArray[ i ] );
               
            } // End else if
   
     // ********************************
     // Top of stack equals " - "    
     
            else if ( evaluateChar.equals( "-" ) ) {
            
               flag1 = 1;
               
               // Case where there is a "*" or "/" on top of a "-"
               if( flag1 < flag2 ) {
               
                     // Pop either a "*" or "/" off the stack  
                     inFixList.pop();
                     inFixList.pop();
                     inFixList.push( myStringArray[ i - 1 ] );  

                     // Add the previous "*" or "/" to the post-fix list
                     postFixList.add( flag2String );
                     flag2 = 0; 
                     
                  } // End if 
               // Case where there is a "^" on top of a "-"
               else if ( flag1 < flag3 ) {
               
                     // Pop "^" off the stack
                     inFixList.pop();
                     inFixList.pop();
                     inFixList.push( myStringArray[ i - 1 ] ); 
                       
                     // Add the previous "^" to the post-fix list
                     postFixList.add( flag3String );
                     flag3 = 0;
                      
                  } // end else if
                  
               // Add the next element to the stack to be processed  
               inFixList.push( myStringArray[ i ] );
            } // End else if
   
     // ********************************
     // Top of stack equals a variable
        
            // If the top of the stack is a letter then add it to the postfix list
            else {
               postFixList.add( inFixList.peek() ); 
               inFixList.pop();
               // Check to see if there is more to add before pushing onto the stack
               if( count < myStringArray.length ) {
                  inFixList.push( myStringArray[ i ] ); 
               } // End if
   
               else{
                  // Unload the stack at the end
                  while( !inFixList.isEmpty() ) {
                  postFixList.add( inFixList.peek() ); 
                  inFixList.pop();
                     } // End while
                  } // End else
               } // End else
            } // End for
   
   
            // Combine the elements of the postFixList into one string and return
            joined = String.join( " " , postFixList );
   
            return joined;
            
           } // End convertToPostfix method

// ********************************************************************************
   /**
   * variableError
   
   * Method to detect if a string, broken up into an array, contains a variable 
      after another variable.  If the string contains this error then the 
      method will return true.  
   
   * @return boolean,         -True if error detected, false if no error detected
   
   * @param inputStringArray, -String[] single string elements in pre-fix format                           
   */   


public static boolean variableError ( String[] inputStringArray ) {

      // Loop through the array and compare a position to the next position
      for ( int j = 0; j < inputStringArray.length - 1; j++ ) 
      {  
        // Detect a variable by eliminating operators and deliminators
    	  if ( !inputStringArray[ j ].equals( "+" ) && 
             !inputStringArray[ j ].equals( "-" ) && 
             !inputStringArray[ j ].equals( "/" ) && 
             !inputStringArray[ j ].equals( "*" ) && 
             !inputStringArray[ j ].equals( "^" ) && 
             !inputStringArray[ j ].equals( "(" ) &&
    		    !inputStringArray[ j ].equals( ")" ) )
    	  {
           // If another variable precedes then there is an error
    		  if ( !inputStringArray[ j + 1 ].equals( "+" ) && 
                !inputStringArray[ j + 1 ].equals( "-" ) && 
                !inputStringArray[ j + 1 ].equals( "/" ) && 
                !inputStringArray[ j + 1 ].equals( "*" ) && 
                !inputStringArray[ j + 1 ].equals( "^" ) && 
                !inputStringArray[ j + 1 ].equals( "(" ) && 
                !inputStringArray[ j + 1 ].equals( ")" ) )
    		  {
           
    			  	return true; // Return true if a varaible follows another variable
               
    		  } // End if
           
    	  } // End if
        
      } // End for
      
      return false; // Return false if no error found
      
   } // End variableError method

// ********************************************************************************
   /**
   * operatorError
   
   * Method to detect if a string, broken up into an array, contains a operator 
      after another operator.  If the string contains this error then the 
      method will return true.  
   
   * @return boolean,         -True if error detected, false if no error detected
   
   * @param inputStringArray, -String[] single string elements in pre-fix format                           
   */   

public static boolean operatorError ( String[] inputStringArray ) {   

      // Loop through the array and compare a position to the next position
      for (int j = 0; j < inputStringArray.length - 1; j++) 
      {  
        // Detect a operator 
    	  if ( inputStringArray[ j ].equals( "+" ) || 
             inputStringArray[ j ].equals( "-" ) || 
             inputStringArray[ j ].equals( "/" ) || 
    			 inputStringArray[ j ].equals( "*" ) || 
             inputStringArray[ j ].equals( "^" ) ) 
             {
           // If another operator precedes then there is an error
    		  if ( inputStringArray[ j + 1 ].equals( "+" ) ||
                inputStringArray[ j + 1 ].equals( "-" ) || 
                inputStringArray[ j + 1 ].equals( "/" ) ||
    				 inputStringArray[ j + 1 ].equals( "*" ) || 
                inputStringArray[ j + 1 ].equals( "^" ) ) 
                {
    			  
    			  	return true; // Return true if a operator follows another operator
               
    		  } // End if
    	  } // End if
      }  // End for
      
      return false; // Return false if no error detected
      
   } // End operatorError

// ********************************************************************************
   /**
   * deliminatorError
   
   * Method to detect if a string, broken up into an array, contains an 
       unbalanced deliminators.  This method will also detect an error if a 
       closed deliminator follows an open deliminator.  If the string contains 
       these error then the method will return true.  
   
   * @return boolean,         -True if error detected, false if no error detected
   
   * @param inputStringArray, -String[] single string elements in pre-fix format                           
   */    

public static boolean deliminatorError( String[] inputStringArray ) {    
 	 
      		int countOpen = 0;   // Count the amount of open deliminators
      		int countClosed = 0; // Count the amount of closed deliminators
            
      		int j = 0;  // index variable for the while loop
      	
            // Loop through the array and count the deliminators
      		while(j <= inputStringArray.length - 1) {
            
      				if ( inputStringArray[ j ].equals( "(" ) ) {
                  
      					countOpen++;
      					
      				   } // End if
                  
      				else if ( inputStringArray[ j ].equals( ")" ) ) {
                  
      					countClosed++;
                     
      				   } // End else if
      				
                  // If a closed deliminator is before an open deliminator 
                  else if ( !(countOpen >= countClosed) ) {
                  
                     return true; // return error detected
                  
                     } //end else if
                  
                  j++;  // Increase the index
                  
      		   } // End while
      		
      		// After deliminators have been counted, 
            //   check to see that each has a matching pair
				if ( countOpen != countClosed ) {
            
    			  	return true; // Return error if there is not a matching pair
  					
				   } // End if
            
       return false; // Return no error detected
   } // End deliminatorError

// ********************************************************************************
   
} // End Postfix class