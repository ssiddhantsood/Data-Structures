import java.util.*;


public class BXT
{
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;   
   
  public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   
   {
      return root;
   }
    
   public void buildTree(String str)
   {
      Stack<TreeNode> tree = new Stack<TreeNode>();
     	
      
      for(String stri : str.split(" ")) 
      { 
         if(isOperator(stri))
         {
         TreeNode next = tree.pop();
         TreeNode r = new TreeNode(stri, tree.pop(), next);
         
         tree.push(r); 
         }
         else 
           tree.push(new TreeNode(stri));          
      }
      
      root = tree.pop();
   }
   
   public double evaluateTree()
   {
   
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  
   {
      if(!isOperator((String) t.getValue())) { 
         return Double.parseDouble((String) t.getValue());
      }
      return computeTerm((String) t.getValue(), evaluateNode(t.getLeft()), evaluateNode(t.getRight())); 
   }
   
   private double computeTerm(String s, double a, double b)
   {
      
      if(s.equals("-")) 
         return a - b;
      
      if(s.equals("+")) 
         return a + b;
      
      if(s.equals("*")) 
         return a * b;
      
      if(s.equals("/")) 
         return a / b;
      
      return 1.0;
   }
   
   private boolean isOperator(String s)
   {
      if (operators.contains(s)) 
         return true;
      
      return false;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); 
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); 
      return toRet;
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
      
   }
   
   
   private String inorderTraverse(TreeNode t)
   {
     if(t != null)
     {
         return  (inorderTraverse( t.getLeft()) + t.getValue() + " " + inorderTraverse(t.getRight()) );
     }
     else
      return "";
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String  preorderTraverse(TreeNode root)
   {
     if(root != null)
     {
         return (root.getValue() + " " + preorderTraverse(root.getLeft()) + preorderTraverse(root.getRight())); 
     }
     return "";

   }
   
   
   }