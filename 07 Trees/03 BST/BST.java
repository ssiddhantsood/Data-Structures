//Name: siddhant sood
//Date:
import java.util.*;
interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   //public boolean remove(String obj);
   public String min();
   public String max();
   public String toString();
}

/*******************
Represents a binary search tree holding Strings. 
Implements (most of) BSTinterface, above. 
The recursive methods all have a public method calling a private helper method. 
Copy the display() method from TreeLab. 
**********************/
class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
     
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
     size++;
     root = add(root, s);
     
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {     
      if (t == null)
      {
 
      t = new TreeNode(s);
      return t;
      }
      TreeNode n = new TreeNode(s);
      
      
      if (((t.getValue().toString()).compareTo(s) >= 0) )
      {
         TreeNode y = add(t.getLeft(),s);
         t.setLeft(y);
      }
      if (((t.getValue().toString()).compareTo(s) < 0))
      {
         TreeNode z = add(t.getRight(),s);
         t.setRight(z);
      }
      return t;
      
       
   }
   
   
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode t, int level) //recursive helper method
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
   
   public boolean contains( String obj)
   {
       return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if ( t == null)
         return false;
      if((t.getValue().toString()).compareTo(x) == 0)
         return true;
      if((t.getValue().toString()).compareTo(x) >= 0)   
         return contains(t.getLeft(),x);
      if((t.getValue().toString()).compareTo(x) < 0)   
         return contains(t.getRight(),x);
      
      return false;
      
   }
   
   public String min()
   {
   return min(root);
      
   }
   private String min(TreeNode t)  //use iteration
   {
      if (t==null)
      return null;
      
      while (t.getLeft() != null)
         t = t.getLeft();
      return t.getValue().toString();

   }
   
   public String max()
   {
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
   /*w
      while (t.getRight() != null)
         t = t.getRight();
      return t.getValue().toString();
   */
   
   if (t==null)
      return null;
   
   
   while (t.getRight() != null)
         t = t.getRight();
      return t.getValue().toString();
      
   }
   
   public String toString()
   {
      return toString(root);
      
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      if(t != null)
      {
         return  (toString( t.getLeft()) + t.getValue() + " " + toString(t.getRight()) );
        // return  (toString(t.getRight()) + t.getValue() + " " + toString(t.getLeft()) );
     }
       else
         return "";

   
   }
}
