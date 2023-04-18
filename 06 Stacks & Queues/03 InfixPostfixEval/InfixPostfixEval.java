// Name: 
// Date:
//uses PostfixEval

import java.util.*;
public class InfixPostfixEval
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   public static final String al = LEFT + RIGHT + operators;
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      List<String> infixExp = new ArrayList<>();
                        
         
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         System.out.println(infix + "\t\t\t" + pf );  
       //  System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
         
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      Stack<String> x  = new Stack<>();
      String y = "";
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      /* enter your code here  */
      
      for(String i : nums) {
        
        
    	  if(!(al.contains(i)))
    		 y = y + i + " ";
    	
        else if(LEFT.contains(i)) 
    		  x.push(i);
    	  
        else if(RIGHT.contains(i))
        {
    		  while (!(LEFT.contains(x.peek())))
           {
    			  y = y + x.pop() + " ";
    		  }
    		  x.pop();
    	  } 
        
        
        else if(operators.contains(i))
        {
    		y = solve(x, y, i);     
              
        }
    }    
    	 
      
      while(!(x.isEmpty()))
    	  y = y + x.pop() + " ";
      
      y = y.trim();
      return y;
   
   
   } 
   public static String solve(Stack<String> x, String y, String i) 
   {
   
   if ( x.isEmpty() ) 
      x.push(i);
		      
   else if ( LEFT.contains(x.peek()) ) 
	   x.push(i);
              
   else if (isStrictlyLower(x.peek(), i)) 
	   x.push(i);
		       
   else 
   {
			      y = y + x.pop() + " ";
			      y = solve(x, y, i);
   }
   return y;
   
}

   
   

   
   //enter your precedence method below
   
   public static boolean isStrictlyLower(String next, String top){
      
      int x = operators.indexOf(next);
      int y = operators.indexOf(top);
      
      if (x==2)
      x=0;
      if (x==6 || x ==8)
      x=4; 
      
      if (y==2)
      y=0;
      if (y==6 || y ==8)
      y=4; 
      
      return (x<y);


   }
}


/********************************************

Infix  	-->	Postfix		-->	Evaluate
 5 - 1 - 1			5 1 - 1 -			3.0
 5 - 1 + 1			5 1 - 1 +			5.0
 12 / 6 / 2			12 6 / 2 /			1.0
 3 + 4 * 5			3 4 5 * +			23.0
 3 * 4 + 5			3 4 * 5 +			17.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * +			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + *			-100.0
 8 + 1 * 2 - 9 / 3			8 1 2 * + 9 3 / -			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - +			-10.0
 2 + 7 % 3			2 7 3 % +			3.0
 ( 2 + 7 ) % 3			2 7 + 3 %			0.0
      
***********************************************/
