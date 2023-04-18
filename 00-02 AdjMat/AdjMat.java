// Name:Siddhant Sood
// Date:5/6/2022
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   public int[][] getGrid();
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public String toString();  //returns the grid as a String
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
   public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
   public boolean isEdge(String from, String to);
   public Map<String, Integer> getVertices();     
   public void readNames(String fileName) throws FileNotFoundException;
   public void readGrid(String fileName) throws FileNotFoundException;
   public void displayVertices();
   public void allPairsReachability();   // Warshall's Algorithm
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix, Warshall, Floyd
{
   
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  write constructor, accessor method, and instance methods here  */  
   
   
   
   
   
   
   public AdjMat(int x)
   {
      grid = new int[x][x];
      vertices = new TreeMap<>();
      nameList = new ArrayList<>();

   }
  
   public int[][] getGrid()
   {
      return grid;
   }
   
   public void addEdge(int x, int y)
   {
      grid[x][y] = 1;
   }
   
   public void removeEdge(int x, int y)
   {
      grid[x][y] = 0;
   }
   
   public boolean isEdge(int x, int y)
   {
       return (grid[x][y] == 1);
   }
   
   
   public boolean isEdge(String x, String y)
   {
		
         return (grid[vertices.get(x)][vertices.get(y)]==1);
      
     
	}
   
   public String toString()
   {
     String full = "";
     for(int[] i : grid) 
     {
       for(int j : i) 
          full = full + j + " ";
         
        full = full + '\n';
     }

     return full;
   }
   
   public int edgeCount()
   {
   int edges =0;
   int x = grid.length-1;
   int y = grid[0].length-1;
   for(int i = 0;i <= x ;i++)
   {
     for(int j = 0; j <= y; j++)
       {
         if(isEdge(i, j))
            edges++;    
       }
   }
     return edges;
   }
   
   public void readGrid(String fileName) throws FileNotFoundException
   {
		Scanner scan= new Scanner(new File(fileName));
        int x = scan.nextInt();
        for(int i = 0; i<x ; i++)
        {
         for(int j = 0; j< x; j++)
         {
            grid[i][j] = scan.nextInt();
     		}
        }
	}
   
   public void displayVertices()
   {
		for (int i = 0; i <= nameList.size()-1; i ++) 
      {
         System.out.println(i + "-" + nameList.get(i));
      }
		
	}
   
   public List<Integer> getNeighbors(int place)
   {
   
     List<Integer> neigh = new ArrayList<Integer>();
     for(int i = 0; i <= grid[0].length - 1; i++) 
     {
       if(grid[place][i] == 1) 
         neigh.add(i);   
     
     }
    return neigh;
   }
   
   public void readNames(String fileNames) throws FileNotFoundException
    {
		Scanner sc = new Scanner(new File(fileNames));
		int end = Integer.parseInt(sc.nextLine());
		String x = "";
      for(int i=0; i< end; i++) 
      {
        
             x=sc.nextLine();
             
             nameList.add(x);
             vertices.put(x, i);
             
         
       }

		
	 }
    
  public List<String> getReachables(String x) 
  {       
    List<String> reach= new ArrayList<String>();
    int w = grid[0].length;
    for(int i=0; i< w ; i++)
    {
       if(grid[vertices.get(x)][i] == 1) 
          reach.add(nameList.get(i));      
    }
    return reach;
   // return null;
   }

    
    
    public void allPairsReachability()
    {
		for (int i = 0; i < grid.length; i ++) 
      {
         for (int j = 0; j < grid.length; j ++)
          {
            for (int k = 0; k < grid.length; k ++)
            {
               if (isEdge(j, i) && isEdge(i, k)) 
                  addEdge(j, k);
               
            }
         }
      }

		
	 }
    
    public int getCost(int x, int y)
    {
		return grid[x][y];
	 }
    
    public int getCost(String x, String y) 
    {
		
		return getCost(vertices.get(x),vertices.get(y));
	}
    
    
    public void allPairsWeighted() 
    {
		 for(int i = 0; i < grid.length; i++)
       {
	         for(int j = 0; j < grid.length; j++)
            {
	            for(int k = 0; k < grid.length; k++)
               {
	               if(grid[i][j] > grid[i][k] + grid[k][j])
                  
	                  grid[i][j] = grid[i][k] + grid[k][j];
	               
	            }
	         }
		 }
		
	}
   
   
    public Map<String, Integer> getVertices() 
    {
		 return vertices;
	 }
  }


