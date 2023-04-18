// Name: Siddhant Sood
// Date: 
import java.util.*;

interface BSTinterface<E>
{
   public int size();
   public boolean contains(E element);
   public E add(E element);
   //public E addBalanced(E element);
   public E remove(E element);
   public E min();
   public E max();
   public String display();
   public String toString();
   public List<E> toList();  //returns an in-order list of E
}



/*******************
  Copy your BST code.  Implement generics.
**********************/
  
  public class BST_Generic<E extends Comparable<E>> implements BSTinterface<E>
  {
   private TreeNode<E> root;
   private int size;
   public BST_Generic()
   {
      root = null;
      size = 0;
   }
   
   public List<E> toList()
   {   
    
  List<E> t = new ArrayList<E>();
  t = toList(root, t);
  return t;
   
   
   }
   
   
   private List<E> toList(TreeNode <E> t,List<E> x)
   {   
     if (t == null)
      return null;
      
         
      toList(t.getLeft(), x);  
      x.add(t.getValue());           
      toList(t.getRight(),x);  
      
      return x;
      
   }

   
   
   
   
   public int size()
   {
      return size;
     
   }
   public TreeNode<E> getRoot()  
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public E add(E s) 
   {
     size++;
     root = add(root, s);
     return s;
     
   }
   private TreeNode<E> add(TreeNode<E> t, E s) //recursive helper method
   {     
      if(t == null){
         return new TreeNode(s, null, null);
      }
      
      if(s.compareTo( t.getValue()) > 0)
         t.setRight(add(t.getRight(), s));
      else
         t.setLeft(add(t.getLeft(), s));
      return t;
       
   }
   
   
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode<E> t, int level) //recursive helper method
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
   
   
    public E remove(E target)
   {
      root = remove(root, target);
      size--;
      return target;
   }

   
   private TreeNode<E> remove(TreeNode<E> current, E target)
   {
   /*
      TreeNode<E> x = current;
      TreeNode<E> n = current;
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
    */  
    return root;      

   }
   
   public boolean contains(E obj)
   {
       return contains(root, obj);
   }
   private boolean contains(TreeNode<E> t, E x) //recursive helper method
   {
   
      if ( t == null)
         return false;
      if(x.compareTo(t.getValue()) == 0)
         return true;
      if(x.compareTo(t.getValue()) < 0)   
         return contains(t.getLeft(),x);
      if(x.compareTo(t.getValue()) >= 0)   
         return contains(t.getRight(),x);
      
      return false;
      
   }
   
   public E min()
   {
   return min(root);
      
   }
   private E min(TreeNode<E> t)  //use iteration
   {
      if (t==null)
      return null;
      
      while (t.getLeft() != null)
         t = t.getLeft();
      return t.getValue();

   }
   
   public E max()
   {
      return max(root);
   }
   private E max(TreeNode<E> t)  //recursive helper method
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
      return t.getValue();
      
   }
   
   public String toString()
   {
      return toString(root);
      
   }
   private String toString(TreeNode<E> t)  //an in-order traversal.  Use recursion.
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
   private int calcBalance(TreeNode<E> t) //height to right minus 
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

   private int height(TreeNode<E> t)   //from TreeLab
   {
   if(t != null) 
         return  Math.max(height(t.getRight())+1 , height(t.getLeft()) +1);
       
       else
         return -1;
   }

   public void addBalanced(E value)  
   {
      add(value);
      root = balanceTree(root);   // for an AVL tree. Put in the arguments you want.
   }
   private TreeNode<E> balanceTree(TreeNode<E> f)  //recursive.  Whatever makes sense.
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
          TreeNode<E> te = f.getRight();
          f.setRight(te.getLeft());
          te.setLeft(f);
          f = te;
         }
      else
        {
        
          TreeNode<E> te = f.getRight().getLeft();
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
          TreeNode<E> te = f.getLeft().getRight();
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
          TreeNode<E> te = f.getLeft();
          f.setLeft(te.getRight());
          te.setRight(f);
          f = te;
            //  
         }
         
       }
      return f;
    }



/*******************
  Copy your TreeNode<E> code.  Implement generics.
**********************/
class TreeNode<E>
{
   private E value; 
   private TreeNode<E> left, right;
   
   public TreeNode(E initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(E initValue, TreeNode<E> initLeft, TreeNode<E> initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public E getValue()
   { 
      return value; 
   }
   
   public TreeNode<E> getLeft() 
   { 
      return left; 
   }
   
   public TreeNode<E> getRight() 
   { 
      return right; 
   }
   
   public void setValue(E theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode<E> theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode<E> theNewRight)
   { 
      right = theNewRight;
   }

}
}