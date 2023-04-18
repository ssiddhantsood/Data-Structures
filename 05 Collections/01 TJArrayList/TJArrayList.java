// Name: Siddhant Sood 
// Date: 11/30/2021 

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()4
 */ 
public class TJArrayList<E>
{
   private int size;							//stores the number of objects
   private E[] myArray;
   public TJArrayList()                
   {
      myArray = (E[]) new Object[10]; //default constructor instantiates a raw array with 10 cells
   
      size = 0;
   }
   public int size()
   {
      return size;
         }
   
 	/* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length, 
         and O(n) when it doubles the length of the array.
	  @return true  */
   public boolean add(E obj)
   {
   
   E[] tempArray = (E[]) new Object[size+1];
   tempArray = myArray;
   tempArray[size] = obj;
   myArray = tempArray;
   size+=1;
   return true;
   
   
   }
   /* inserts obj at position index.  increments size. 
		*/
   public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      E[] temp = (E[]) new Object[size+1];
      for(int i =0; i< index; i++){
         temp[i] = myArray[i];
      }
      temp[index] = obj;
      for(int i = index; i< size; i++){
         temp[i+1] = myArray[i];
      }
      myArray = temp;
      size++;      
               
   }

   /* return obj at position index.  
		*/
   public E get(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      return myArray[index];
   
   }
   /**
    * Replaces obj at position index. 
    * @return the object is being replaced.
    */  
   public E set(int index, E obj) throws IndexOutOfBoundsException  
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      E replaced = myArray[index];
      myArray[index] = obj;
      return replaced;
      
   
   }
 /*  removes the node from position index. shifts elements 
     to the left.   Decrements size.
	  @return the object that used to be at position index.
	 */
   public E remove(int index) throws IndexOutOfBoundsException  
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
         
     E pp= myArray[index];
     for (int i = index +1; i < size; i++){
         myArray[i-1] = myArray[i];
         
     }
      return pp;
     
   }
	   /*
		   This method compares objects.
         Must use .equals(), not ==
     	*/
   public boolean contains(E obj)
   {
      for(int i = 0; i< size; i++)
      {
         if((myArray[i]).equals(obj))
         {
            return true;
         }
      }
      return false;
   }
	 /*returns a String of E objects in the array with 
       square brackets and commas.
     	*/
   public String toString()
   {
      String f = "[";
      for(int i = 0; i< size-1; i++)
      {
      
      f = f + myArray[i] + ", ";
     
      }
      f = f +myArray[size-1] + "]";
      
      return f;
   }
}