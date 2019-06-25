// ********************************************************************************
/**
* Class for main. Reads a file of prefix expressions and writes them as 
   postfix expressions. 
*  
* This program will first use a series of methods to open the file and clean data.
  The program then uses convertToPostfix() method to convert the prefix
  data into postfix notation.  If the convertToPostfix() method encounters 
  incompadibilities it will display an appropriate response.
   
* @author Derek Holsapple, Gabriel Grant
* @version 4/24/2019

* Palimpsest: noun, a parchment or the like from which writing has been 
              partially or completely erased to make room for another text.


* “You can get help from teachers, but you are going to have to learn a lot 
      by yourself, sitting alone in a room.”  
* Theodor Seuss Geisel ( 05/02/1904 - 09/24/1991 )
*/
// ********************************************************************************

import java.io.*;
import java.util.*; 


public class DerekHolsapple_GabrielGrant_05 extends Postfix {

   // Create a scanner and a list to store the incoming file data
   private static List< String > dataList = new ArrayList< String >();
   private static Scanner scan;

// ********************************************************************************
/**
   * Method main
   * The main method converts a prefix expression to a postfix from a file.  
      The program then displays the postfix notation on the console
   
   * @param INPUT_FILE       String, path of file being read.
   
   * @param dataFile         String[], Store input data from the file

   */
// ********************************************************************************

   public static void main(String[] args) throws EmptyStackException, IOException {
   
   // Input file path
   String INPUT_FILE = "project_05_Input.txt";

   // Open the file and read content
   openFile( INPUT_FILE );
   readFile();
   
   // Create an array to store each line of the file.
   // Remove the whitespace from each line of the file
   String[] dataFile = removeWhiteSpace();
   closeFile();

   // Convert each line from the file into Postfix
   for ( int i = 0; i < dataFile.length; i++ ) { 

         System.out.println(convertToPostfix( dataFile[ i ] ) );
   
        } // End for 
        
    } // End main 
      
// ********************************************************************************
   /**
   * openFile
   
   * Method to open the file.
   
   *@param fileName,  -String of the file name with a .txt extentsion
   
   */
   

   public static void openFile( String fileName ) {

        try{
          scan = new Scanner( new File( fileName ) );
            } // End try
      
        catch ( Exception e ) {
          System.out.println( "Could not Find File" );
            } // End catch
        } // End openFile

// ********************************************************************************
   /**
   * closeFile
   
   * Method to close the file.
   
   */
     public static void closeFile() {
     
        scan.close();
        
      } // End closeFile
      
// ********************************************************************************
   /**
   * readFile
   
   * Method to read and store content of file in a list.
   
   * @return stringArray,  -String ArrayList containing each line of the 
                              file as an element
   */

   public static List<String> readFile() {
   
     // Loop until there are no more lines of the file
     // Store each line of the file into a list
     while ( scan.hasNext() ) {
     
       String dataLine = scan.nextLine();
       dataList.add( dataLine ); 

        } // End while
        
     return dataList;
     
     } // End readFile
    
// ********************************************************************************
   /**
   * removeWhiteSpace
   
   * Method to read and store content of file in a list.
   
   * @return stringArray,  -String Array that will store each line of data as 
                              an element with no white space
   */
   public static String[] removeWhiteSpace() {
   
      // Create a string array from the file 
        String[] stringArray = dataList.toArray( new String[ dataList.size() ] );
         
    // Remove white space from each element in the array and replace 
    for ( int i = 0; i < stringArray.length; i++ ) { 
    		             
          String noWhiteSpace = stringArray[ i ].replace( " " , "" );
          stringArray[ i ] = noWhiteSpace; 
          
      }
      
      return stringArray; 
        		
   } // End removeWhiteSpace 
     
} // End DerekHolsapple_GabrielGrant_05 class
