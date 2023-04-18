 // Name: Siddhant Sood

import java.io.*;
import java.util.*;
public class herdle
{
   public static void main(String[] args)
   {
      int green = 0;
      int yellow = 0;
   
      String[] first = new String[3]; 
      String[] second = new String[3];
   
      String totalfirst = "";
      String totalsecond = "";
   
      Scanner sc =new Scanner(System.in);  
  
      for(int i=0; i<3; i++)
      {  
         first[i]=sc.nextLine();  
   
      } 
      for(int i=0; i<3; i++)
      {  
         second[i]=sc.nextLine();  
   
      } 
     
       
      for(int i = 0; i<9; i++)
      {
      int r = i%3;
      int y = i/3;
      
      
      
      
      if (first[y].charAt(r) == second[y].charAt(r)){
         green++;
         if (r<2){
         first[y] = first[y].substring(0,r) + "0" + first[y].substring(r+1);
         second[y] = second[y].substring(0,r) + "0" + second[y].substring(r+1);
         
         
         
         }
         if (r>=2){
         first[y] = first[y].substring(0,r) + "0";
         second[y] = second[y].substring(0,r) + "0";
         }
         
       
       }
       
      }
      
      
      
      
      for (int i = 0; i<3;i++)
      {
      totalfirst = totalfirst + first[i];
      
      totalsecond = totalsecond + second[i];
      }
      
      totalfirst= totalfirst.replaceAll("0", "");
      totalsecond= totalsecond.replaceAll("0", "");
      
      
      int w = totalsecond.length();
      for (int i = 0; i<w-1;i++)
      {
         if ((totalfirst.contains(totalsecond.substring(i,i+1))) && (totalsecond.substring(i,i+1) != "0" ))
         {
            yellow++;
            int h = totalfirst.indexOf(totalsecond.substring(i,i+1));
            if (h == (totalfirst.length()-1)){
               totalfirst= totalfirst.substring(0,h);
             }
             else
            {
                totalfirst= totalfirst.substring(0,h)  + totalfirst.substring(h+1);
            }
         
         
         }
      
      
      }
      
      
      
      
    
    
    
     System.out.println(green);
     System.out.println(yellow);
   
   
   
   
   
   
   }  
   
   
   
   
   
      
     
   
      
   


}
