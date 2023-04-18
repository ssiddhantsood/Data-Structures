// Name: Siddhant Sood   
// Date: 9/5/2021
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
       part_2_using_piglatenizeFile();
      
      
       String pigLatin = pig("What!?");
       System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
       pigLatin = pig("{(Hello!)}");
       System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
       pigLatin = pig("\"McDonald???\"");
       System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }
/**
   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }
**/
   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   
   

   public static String pig(String s) {
     if (s.length() == 0)
        return "";

    int i = 0;
    String beginningPunct = "";
    while (i < s.length() && punct.contains(Character.toString(s.charAt(i)))){
      beginningPunct = beginningPunct + s.charAt(i);
        i = i+1;
        
     }
     
    String endingPunct = "";
    int j = s.length() - 1;
    
    while (j > 0 && punct.contains(Character.toString(s.charAt(j)))) {
      endingPunct = s.charAt(j) + endingPunct;
         j = j-1;
     }

        
    int abc = i;
    String moveTo = "";
    
    
    while (abc < s.length()-1 && !(vowels.contains(Character.toString(s.charAt(abc)))) || ("Yy".contains(Character.toString(s.charAt(abc))) && abc > i) ) {
        moveTo = moveTo + s.charAt(abc);
        abc = abc+1;
        
     }
    if (!moveTo.isEmpty() && "Qq".contains(Character.toString(moveTo.charAt(moveTo.length() - 1))) && abc > i  && abc < s.length() && ("Uu".contains(Character.toString(s.charAt(abc))))) {
        moveTo = moveTo + s.charAt(abc);
         abc = abc + 1;
    }
        
    if (!(abc < s.length())) {
        return "**** NO VOWEL ****";
    }

       
    boolean isCap = false;
     if (Character.isUpperCase(s.charAt(i))) {
          isCap = true;
     }

    String end = "ay";
     if (moveTo.isEmpty()) {
          end = "way";
     }
        // return the piglatinized word
    if (isCap) {
     if (!moveTo.isEmpty()) {
         moveTo = (moveTo.substring(0, 1)).toLowerCase() + moveTo.substring(1);
          }
      return beginningPunct + (s.substring(abc, abc + 1)).toUpperCase() + s.substring(abc + 1, j + 1) + moveTo + end + endingPunct;

        }
    return beginningPunct + s.substring(abc, j + 1) + moveTo + end + endingPunct;

  }

   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }
   
   
   

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
  
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      
      String tempLine;
      while (infile.hasNext()){
         tempLine = infile.nextLine();   
         String[] arrOfStr = tempLine.split(" ");
         for(int i = 0; i <arrOfStr.length; i++){
         
         tempLine = pig(arrOfStr[i]);
         if (i < arrOfStr.length - 1){
            tempLine = tempLine + " ";
         }
         //tempLine = tempLine.trim();
         outfile.print(tempLine);
         
         }
         outfile.print("\n");
         
         
      
      
      }
      
   
      outfile.close();
      infile.close();
   }
   
//    EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
//       punctuation.  
   
   public static String pigReverse(String s)
   {
      
         return "syo dont h";
         
         
   }



}