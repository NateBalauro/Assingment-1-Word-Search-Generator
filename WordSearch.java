/*
Name: Nathaniel Balauro
Class: CS&145
Assignment: Assingment 1 - Word Search Generator
Purpose: To generate a word search puzzle based off of user input
*/

import java.util.*;

public class WordSearch {
   
   private static int width;
   private static int length;
   private static int wordC;
   private static ArrayList<String> words;
   private static int[] positions;
   private static char [][] key;
   private static String input;
   
   
   public static void main(String [] args){
   
      intro();
      
   } // end of main method
   
   //Calls all methods to create the puzzle
   public static void intro(){
      System.out.println("...RUNNING WORD SEARCH GENERATOR...");
      System.out.print("\nThis program will generate a word search puzzle...\n");
      System.out.println("******************************************************"); 
      System.out.println(); 
      userInput(); 
      generate();
      random();
      printY();
   } // end of into method
   
   public static void printY() {
      for(int i =0; i <width; i++) {
         for(int ind =0; ind<length; ind ++){
            System.out.print(key[i][ind]+ " ");
         }
         System.out.println(" ");
      }
      System.out.println("FIND THESE WORDS");
      for(int i =0; i<wordC; i++){
         System.out.println(words.get(i));
      }
   } // end of printY method
   
   //method to randomize the placement of the words and letters 
   public static void random(){
      int a, b;
      int x, y;
      positions = new int[wordC];
      
      for(int i =0; i < wordC; i++){ //this loop is for every word
         b = words.get(i).length();
         a = width -b;
         x = randomRange(0, a);
         y = randomRange(0, length-5);
         if(key(positions,y) ){
            y++;
         }
         positions[i] = y;
         for(int ind =0; ind <b; ind++){//this loop for every letter/char
            key[x][y] = words.get(i).charAt(ind);x++;
         }
      } 
      for (int i =0; i <length; i ++){ // searches for positions unoccupied by letters
         for (int ind =0; ind< width; ind++){
            if(key[ind][i] == 0){
               char t = (char) 
               randomRange(97, 122);
               key[ind][i] = t;
            }
         }
      }
   } // end of random method
   
   //method for deciding the length of words for the puzzle
   public static boolean key(int [ ] num, int lock) {
      for (int index = 0; index < num.length; index++){
         if (num[index] == lock ){ 
            return true;
         }
      }
      return false;
   } // end of key method
   
   //random char creator
   public static int randomRange(int min, int max){
      Random generator = new Random();
      return generator.nextInt(max-min+1) + min;
   } //end of randomRange method
   
   //method for user input
   public static void userInput(){
      Scanner scan = new Scanner(System.in);
      wordC =0;
      words= new ArrayList<String>();
      System.out.println("Please enter the words you would like in the puzzle." );
      System.out.println("Only enter one word per line." );
      System.out.print("When you are finished entering your words, enter: \"create\"\n");
      System.out.println("If you would like some help solving the soon to be puzzle,");
      System.out.println("enter: \"help\"");
      
      while (scan.hasNextLine()){
         input = scan.next();
         if(input.equals("create")){
            scan.close();break;
         } 
         else if(input.equals("help")){
            System.out.println("Puzzle Help: ");
            System.out.println("The solution will always be verticaly lined, ");
            System.out.println("from left to right, bottom to top");            
            scan.close();break;
         }
         wordC++;
         words.add(input);
      }
   } // end of userinput method
   
   
   public static void generate(){
      System.out.println("Generating word search");
      int i;
      for(i =0; i<words.size(); i++){
         if(words.get(i).length() > width){
            width = words.get(i).length();
         }
      }
      width = width *2;
      length = width + (width/3);
      key = new char [width][length];
   } // end of generate method

} // end of class