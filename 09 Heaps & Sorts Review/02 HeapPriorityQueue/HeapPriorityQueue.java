 //Name: Siddhant Sood 
 //Date:
 
import java.util.*;

/* implement the API for java.util.PriorityQueue
 *     a min-heap of objects in an ArrayList<E> in a resource class
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public ArrayList<E> getHeap()   //for Codepost
   {
      return myHeap;
   }
   
   public int lastIndex()
   {
     return (myHeap.size()-1); 
   }
   
   public boolean isEmpty()
   {
     if (myHeap.size() > 1)
       return false;
     return true;   
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(lastIndex());
      return true;
   }
   
   public E remove()
   {
      swap(1,lastIndex());
      E x = myHeap.remove(lastIndex());
      heapDown(1,lastIndex());
      return x; 
   }
   
   public E peek()
   {
     if(lastIndex()<=0)
       return null;   
     return myHeap.get(1); 
   }
   
   //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapUp(int k)
   {
    
      if (k/2 == 0) 
         return;
      else if (myHeap.get(k/2).compareTo(myHeap.get(k)) > 0) 
      {
         swap(k,k/2);
         heapUp(k/2);
      }             
   }
   
   private void swap(int a, int b)
   {
      E c = myHeap.get(a);
      myHeap.set(a,myHeap.get(b));
      myHeap.set(b,c); 
   }
   
  //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapDown(int k, int lastIndex)
   {
      
      if (2*k == lastIndex) 
      {
         if ((myHeap.get(k)).compareTo(myHeap.get(2*k)) > 0)
            swap(k, 2*k);
      }
      if (2*k>lastIndex)
         return;
         
      else 
      {
         int min;
         if (myHeap.get(k).compareTo(myHeap.get(2*k+1))<0)
            min = (2*k);
            
         else
            min =(2*k+1);
         if (myHeap.get(k).compareTo(myHeap.get(min))>0) 
         {
            swap(k, min);
            heapDown(min, lastIndex);
         }
      }
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
