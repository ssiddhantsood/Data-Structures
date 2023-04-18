// Name:Siddhant Sood         Date:4/2/2021
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
   
      TreeNode main = new TreeNode("", null, null);
      
      while(huffLines.hasNext())
      {
      
        String str = huffLines.nextLine();
        TreeNode pointer = main;
         
        String v = str.substring(0, 1);
         
        for (char bit : (str.substring(1).trim()).toCharArray())
        {
          if (bit !='0')
          {
        
            if (pointer.getRight() == null)
              pointer.setRight(new TreeNode(""));
               
              pointer = pointer.getRight();
           } 
            else if (pointer.getLeft() == null)
            {
              pointer.setLeft(new TreeNode(""));
              pointer = pointer.getLeft();
            }
            else
              pointer = pointer.getLeft();  
         }
         
         pointer.setValue(v);
      }
     return main;  
   }
   public static String dehuff(String text, TreeNode root)
   {
      String str = "";
      
      TreeNode pointer = root;
      for (char bit:text.toCharArray())
      {
        
        
        if (bit != '0')
          pointer=pointer.getRight();
        else
          pointer=pointer.getLeft();
         
         
        if ((pointer.getRight() == null) && (pointer.getLeft() == null)) 
          {
          str = str + pointer.getValue();
          pointer = root;
        }
      }
      return str;
      
      
}
   
  
     }

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
