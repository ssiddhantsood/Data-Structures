// Name: Grace Liu B6-016
// Date: 2/18/22

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);   //does not balance
   public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

public class BSTcopy implements BSTinterface
{
   /*  copy your BST code  here  */
   private TreeNode root;
   private int size;
   public BSTcopy()
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
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {
      return null;
   }
   public void add(String s) 
   {
      root = add(root, s);
      size++;
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if (t == null)
		   return new TreeNode(s, null, null);
      if (s.compareTo(t.getValue() + "") <= 0)
		   t.setLeft(add(t.getLeft(), s));
      else
		   t.setRight(add(t.getRight(), s));
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
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public boolean contains( String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
	   while(t != null)
      {
	      if( x.equals("" + t.getValue()) )
		      return true;
         if( x.compareTo("" + t.getValue()) < 0 )
 		      t = t.getLeft();
	      else
		      t = t.getRight();
      }
      return false; 
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if( t == null )   
          return null;
      while( t.getLeft() != null )
          t = t.getLeft();
      return (String)t.getValue();
   }
   
   public String max()
   {
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t == null)          
           return null;
    	   if( t.getRight() == null )   
           return (String)t.getValue();
    	   return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += toString(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";      //process root
      toReturn += toString(t.getRight());  //recurse right
      return toReturn;
   }
   
   
   
   /*  start the addBalanced methods */



   private int height(TreeNode t)
   {
      
      int rightMax;
      int leftMax;
      
      if (t == null)
         return -1;
      else
      {
        rightMax = height(t.getRight());
        leftMax = height(t.getLeft());
      }
      
      if (rightMax >= leftMax)
         return 1 + rightMax;
      else
         return 1 + leftMax;
      
   }
   private int calcBalance(TreeNode t)
   {
      int temp;
      if(t == null)
         return 0;
      else if (t.getLeft() == null)
         return 1 + height(t.getRight());
      else if (t.getRight() == null)
         return -1 - height(t.getLeft());
      else
         return height(t.getRight()) - height(t.getLeft());
   }

   /*  start the addBalanced methods */
   public void addBalanced(String value)  
   {
      add(value);
      root = balanceTree(root);   // for an AVL tree.  You may change this line.
   }
   private TreeNode balanceTree(TreeNode t)
   {
      if(t == null)
         return null;
      t.setLeft(balanceTree(t.getLeft()));
      t.setRight(balanceTree(t.getRight()));
      int bal = calcBalance(t);
      
      if (bal >= 2) //right heavy
      {
         if(calcBalance(t.getRight()) >= 0)    //tree's right subtree is left heavy
         {
            t = leftLeft(t);
         }
         else 
            t = left(t); 
      }
      
      else if (bal <= -2)  //left heavy
      {
         if (calcBalance(t.getLeft()) <= 0)
         {
            t = rightRight(t);      
         }
         else
            t = right(t);
      }
      
      return t;   
   }
   
  
   private TreeNode right(TreeNode current)
   {
      TreeNode temp = current.getLeft();
      TreeNode temp2 = temp.getRight();
      temp.setRight(current);
      current.setLeft(temp2);
      
      return temp;
   }
   private TreeNode left(TreeNode current)
   {
      TreeNode temp = current.getRight();
      TreeNode temp2 = temp.getLeft();
      temp.setLeft(current);
      current.setRight(temp2);
      
      return temp;

   }

   
   private TreeNode rightRight(TreeNode current)
   {
      current.setLeft(left(current.getLeft()));
      return right(current);  
   }
   
   private TreeNode leftLeft(TreeNode current)
   {
      current.setRight(right(current.getRight()));
      return left(current); 
   }
}