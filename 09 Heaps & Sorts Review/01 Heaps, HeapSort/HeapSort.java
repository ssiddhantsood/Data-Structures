// Name: Siddhant Sood
// Date: 
import java.util.Random;
public class HeapSort
{
   public static int N;  //9 or 100
	
   public static void main(String[] args)
   {
      /* Part 1: Given a heap, sort it. Do this part first. */
      N = 9;  
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};  // size of array = N+1
      
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
      
      /* Part 2:  Generate 100 random numbers, make a heap, sort it.  */
      // N = 100;
      // double[] heap = new double[N + 1];  // size of array = N+1
      // heap = createRandom(heap);
      // display(heap);
      // makeHeap(heap, N);
      // display(heap); 
      // sort(heap);
      // display(heap);
      // System.out.println(isSorted(heap));
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      /* enter your code here */
      for(int i=array.length - 1;i >=1;i--)
      {
         swap(array, i, 1);
         heapDown(array, 1, i - 1);
      }
      
      if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);
   }
  
   public static void swap(double[] array, int a, int b)
   {
   double r = array[b];
   array[b]=a;
   array[a]=r;
   
   }
   
   public static void heapDown(double[] array, int k, int lastIndex)
   {
      int mid= 2*k;
      int end= mid+1;
      if (mid >lastIndex|| k > lastIndex )
         return;
         
      if (end <= lastIndex) 
      {
         int r = (array[mid] > array[end]) ? mid : end;
         if (array[k] < array[r])
         {
            swap(array, k, r);
            heapDown(array, r, lastIndex);
         }
      } 
      
      else if(array[k] < array[mid])
            swap(array, k, mid);
      
   }
   
   public static boolean isSorted(double[] arr)
   {
      for (int i =0; i <arr.length- 1; i++) 
      {
        if (arr[i] >arr[i + 1]) 
            return false; 
      } 
      return true; 
   } 
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1; 
       
      for (int i = 1; i <= 100; i++)
      {
         Random z = new Random();
         double randomValue = 1 + (100 - 1) * z.nextDouble();
         array[i] = randomValue;
      }      
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int lastIndex)
   {
       for(int i = lastIndex/2; i> 0; i--)
         heapDown(array,i, lastIndex);      
   }
   
   
}

