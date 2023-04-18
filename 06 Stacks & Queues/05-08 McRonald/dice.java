 // Name: Siddhant Sood

import java.util.*;
import java.io.*;
import java.util.Arrays;

public class dice
{
   public static void main(String[] args)
   {
   
   Scanner sc =new Scanner(System.in);  
   int n =sc.nextInt();
   Queue<String> str = new LinkedList<String>();
   for(int i=0; i<n+1; i++)
   {  
      String f = (sc.nextLine().replaceAll(" ", ""));
      str.add(f);
      
   }
   
   
   String answer = "yes";
   int []ia = new int[4]; 
   int []ib = new int[4];
   char []set= new char[8]; 
   for(int i=0; i<n; i++)
   {  
      str.remove();
      String x = str.peek();
      
      set = x.toCharArray();
      
      for(int j = 0; j<4;j++)
      {
        ia[j]= Character.getNumericValue(set[j]);
        
        
        ib[j]= Character.getNumericValue(set[j+4]);
      }
      
      Arrays.sort(ia);
      Arrays.sort(ib);
      
      
      
      
      if ((ia[3] == ib[3]) && (ia[2] == ib[2]) ||
          (ia[0] == ib[0]) && (ia[1] == ib[1]) ||
          (ia[0] < ib[0]) && (ia[1] < ib[1])  ||
          (ia[3] == ib[3]) && (ia[2] == ib[2]) ||
          (ia[3] == ia[2]) && (ia[2] == ia[1]) 
          
      ){
      
      answer = "no";
      }
    
    
    
      System.out.println(answer); 
   }
   
   
   
   
   
      
     
   
      
   









}
}
