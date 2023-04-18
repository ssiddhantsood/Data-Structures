// Name: Siddhant Sood
// Date:

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add ("17 4 %");
   
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      
      Stack<Double> postfixParts  = new Stack<>();
      List<String> x = new ArrayList<String>(Arrays.asList(pf.split(" ")));      
      double y;
      double a;
      double b;
      for(int i = 0; i < x.size(); i++)
      {
         if (isOperator(x.get(i))){
            
            a  = postfixParts.pop();
            if (postfixParts.empty()){
               b = 0.0;
            }
            else
               b = postfixParts.pop();
            y = eval(b,a, x.get(i));
            postfixParts.push(y);
           
         } 
            
         else{
            postfixParts.push(Double.parseDouble(x.get(i)));
         }
      
      
      
      }
      return postfixParts.pop();
   }
   
   public static double eval(double a, double b, String ch)
   {
      
      if (ch.equals("+")){
         return a+b;
      }
      if (ch.equals("-")){
         return a-b;
      }
      if (ch.equals("*")){
         return a*b;
      }
      if (ch.equals("/")){
         return a/b;
      }
      if (ch.equals("%")){
         return a%b;
      }
      if (ch.equals("^")){
         return Math.pow(a,b);
      }
      if (ch.equals("!")){
         double x = 1;
         for (double i = 1; i<b+1; i++){
            
            x = x*i;
         }
         return x;
      }
     return 0;    
   
   }
   
   public static boolean isOperator(String op)
   {
      return (operators.contains(op));
   
   
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/