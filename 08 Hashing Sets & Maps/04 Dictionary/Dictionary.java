// Name: Siddhant Sood
// Date: 3/10/2022

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      String x  = "";
      String y  = ""; 
      Map<String, Set<String>> r = new TreeMap<>();
       
      while (infile.hasNext())
      {
         x = infile.nextLine();
         y = infile.nextLine();    
        
         add(r, x, y);
            
         
      }
      
      return r;
      
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
      if(dictionary.containsKey(word))
      {
         dictionary.get(word).add(translation);
      }
      else
      {
         TreeSet<String> g = new TreeSet<String>();
         g.add(translation);
         dictionary.put(word, g);
      }
      

   }
   
   public static void display(Map<String, Set<String>> m)
   {
      
      for(String str : m.keySet()) 
      {
         
         System.out.println(str + " " + m.get(str));
      }   
     
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      Map<String, Set<String>> r = new TreeMap<>();
      for (String Str: dictionary.keySet())
      {
         for (String t: dictionary.get(Str))
         {
            add(r,t,Str);
         }
         
      }
      
      return r;
   
   }
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/