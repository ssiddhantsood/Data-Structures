// Name: Siddhant Sood
// Date: 

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

public class BST implements BSTinterface
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
   public TreeNode getRoot()  
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
   
   
    public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }

   
   private TreeNode remove(TreeNode current, String target)
   {
      TreeNode x = current;
      TreeNode n = current;
      int len = 0;
      target = target.trim();
      
      String y = "";
      while (!(x.getValue().toString().equals(target)))
      {
         len++;
         
      
         if((x.getValue().toString()).compareTo(target) >= 0) 
         {  
            x = x.getLeft();
            current.getLeft();
         }
         if((x.getValue().toString()).compareTo(target) < 0)  
         { 
          x  = x.getRight();
          current.getRight();
        }
        
      }
      //System.out.println(x.getValue() + "HEHE");
      for (int i = 0; i<len-1;i++)
      {
      
      //System.out.println("n" +n.getValue().toString());
     
      if((n.getValue().toString()).compareTo(target) >= 0)   
         n = n.getLeft();
      if((n.getValue().toString()).compareTo(target) < 0)   
         n  = n.getRight();
      
      }
      Boolean notDone = true;
     // System.out.println(current.getValue());
      
      
      if((x.getLeft() == null) && (x.getRight() == null))
      {
        if(current.getValue().toString().compareTo(target) == 0)
        return null;
        
         x.setValue(null); 
         size--;    
         notDone = false;
         return current;
      }
      if ((x.getLeft() != null) && (x.getRight() == null)&& (notDone == true))
      {
        // System.out.println("RARA" + n.getValue());
         if(current.getValue().toString().compareTo(target) == 0)
            return current.getLeft();
         
         else if((n.getValue().toString()).compareTo(target) >= 0)
            n.setLeft(x.getLeft());
         else 
            n.setRight(x.getLeft());
         
         return current;
         
      }
      
      
      if((x.getLeft() == null) && (x.getRight() != null)&& (notDone == true))
      {
         System.out.println("RARA" + n.getValue());
         if(current.getValue().toString().compareTo(target) == 0)
            return current.getRight();
         
         else if((n.getValue().toString()).compareTo(target) >= 0)
            n.setLeft(x.getRight());
         else 
            n.setRight(x.getRight());
            
         
         return current;
      }
      else// ((x.getLeft() != null) && (x.getRight() != null)&& (notDone == true));
      {
       if ((x.getLeft() != null) && (x.getRight() != null)&& (notDone == true)){
         n = x;
         //System.out.println(n.getValue());
         while (!(n.getLeft() == null))
         {
         
            n = n.getLeft();
         }
         y = n.getValue().toString();
         
         n.setValue(null);
         size--;
         
         x.setValue(y);   
         }
      }
      
      return current;
            

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




   /*  start the addBalanced methods */
   private int calcBalance(TreeNode t) //height to right minus 
   {                                    //height to left
   
     if(t == null)
        return 0;
      if(t.getLeft() == null)
         return height(t.getRight()) + 1;
      else if(t.getRight() == null)
         return -1 - height(t.getLeft());
      else
         return height(t.getRight()) - height(t.getLeft());
   }

   private int height(TreeNode t)   //from TreeLab
   {
   if(t != null) 
         return  Math.max(height(t.getRight())+1 , height(t.getLeft()) +1);
       
       else
         return -1;
   }

   public void addBalanced(String value)  
   {
      add(value);
      root = balanceTree(root);   // for an AVL tree. Put in the arguments you want.
   }
   private TreeNode balanceTree(TreeNode f)  //recursive.  Whatever makes sense.
   {
    if(f == null)
         return null;
      
      
    f.setLeft(balanceTree(f.getLeft()));
    f.setRight(balanceTree(f.getRight()));
      
      
    int x = calcBalance(f);
      if(x >= 2)
      {
      if(calcBalance(f.getRight()) >= 0)
        {
          TreeNode te = f.getRight();
          f.setRight(te.getLeft());
          te.setLeft(f);
          f = te;
         }
      else
        {
        
          TreeNode te = f.getRight().getLeft();
          f.getRight().setLeft(te.getRight());
          te.setRight(f.getRight());
          f.setRight(te);  
          
         
         
          
           te = f.getRight();
          f.setRight(te.getLeft());
          te.setLeft(f);
          f = te;  
         }
      }
      else if( x <= -2)
      {
        if(calcBalance(f.getLeft()) >= 0)
         {
          TreeNode te = f.getLeft().getRight();
          f.getLeft().setRight(te.getLeft());
          te.setLeft(f.getLeft());
          f.setLeft(te);  
          
          te = f.getLeft();
          f.setLeft(te.getRight());
          te.setRight(f);
          f = te;       
         }

        else
         {
          TreeNode te = f.getLeft();
          f.setLeft(te.getRight());
          te.setRight(f);
          f = te;
            //  
         }
         
       }
      return f;
    }
}
   
   
   
