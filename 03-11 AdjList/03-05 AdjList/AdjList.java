// Name: Siddhant Sood
// Date:
 
import java.util.*;
import java.io.*;
 
/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */
 
/**************** Graphs 3: EdgeList *****/
interface VertexInterface
{
   public String getName();
   public HashSet<Vertex> getAdjacencies();
   
   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    this method should be O(1)
   */
   public void addAdjacent(Vertex v);
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
     */
   public String toString(); 
 
} 
 
/*************************************************************/
class Vertex implements VertexInterface, Comparable<Vertex> //2 vertexes are equal if and only if they have the same name
{
   private final String name;
   private HashSet<Vertex> adjacencies;
  /* enter your code here  */
  
   public Vertex(String x)
   {
      
       adjacencies = new HashSet<Vertex>();
       name = x;
   }
   
   public String getName()
   {
       return name;
   }
   
   public HashSet<Vertex> getAdjacencies()
   {
       return adjacencies;
   }
   
   public void addAdjacent(Vertex x)
   {
       adjacencies.add(x);
   }
      
   public String toString()
   {
      String end = name + " [";
      for (Vertex x : adjacencies)
      {
         if (x != null)
         {
            end = end + x.getName() + " ";
         }
      }
      
      return (end.trim()+ "]");
   }
  
  
   public int compareTo(Vertex other)
   {
      return ( name.compareTo(other.getName()));
   }
   public int hashCode( )
   {
   return name.hashCode();
   }
   
   
   public boolean equals(Vertex other)
   {
   return (( name.compareTo(other.getName())) == 0);
   }
   
   
   
  
}   
 
/*************************************************************/
interface AdjListInterface 
{
   public Set<Vertex> getVertices();
   public Vertex getVertex(String vName);
   public Map<String, Vertex> getVertexMap();  //this is just for codepost testing
   
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName);
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(1)
   */
   public void addEdge(String source, String target); 
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(); 
 
}
 
  
/********************** Graphs 4: DFS and BFS *****/
interface DFS_BFS
{
   public List<Vertex> depthFirstSearch(String name);
   public List<Vertex> breadthFirstSearch(String name);
   /*   extra credit methods */
 //  public List<Vertex> depthFirstRecur(String name);
 //  public List<Vertex> depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}
 
/****************** Graphs 5: Edgelist with Cities *****/
interface EdgeListWithCities
{
   public void readData(String cities, String edges) throws FileNotFoundException;
   public int edgeCount();
   public int vertexCount();
   public boolean isReachable(String source, String target);
   public boolean isStronglyConnected(); //return true if every vertex is reachable from every 
                                          //other vertex, otherwise false 
}
 
 
/*************  start the Adjacency-List graph  *********/
public class AdjList implements AdjListInterface,DFS_BFS, EdgeListWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();
      
   /* constructor is not needed because of the instantiation above */
  
   /* enter your code here  */
   
   public void readData(String cities, String edges) throws FileNotFoundException 
   {
      Scanner sc = new Scanner(new File(cities));
      Scanner scan = new Scanner(new File(edges));
      
      while(sc.hasNextLine())
      {
         addVertex(sc.nextLine());
      }
      
      while(scan.hasNextLine())
      {
         addEdge(scan.next(), scan.next());  
      }  
   }
   
   public int edgeCount()
   {
      int w = 0;
      for (Vertex s : getVertices())
      {
         int add = s.getAdjacencies().size();
         w = w + add; 
      }
      return w;  
   }
   public int vertexCount()
   {
      return getVertices().size();
   }
   public boolean isReachable(String source, String target)
   {
      List<Vertex> reach = breadthFirstSearch(source);
      for (Vertex x : reach)
      {
         if(x.getName().equals(target))
            return true;
         
      }
      return false;
   }
   
   public boolean isStronglyConnected()
   {
      for (String s : vertexMap.keySet())
      {
         for (String w: vertexMap.keySet())
         {
            if ((!isReachable(s, w)) && (!s.equals(w)))
            {
               return false;
            }
         }
         
      }
      return true;
         
   }  
   
   public List<Vertex> depthFirstSearch(String name)
   {
   
      
      List<Vertex> reach = new ArrayList<Vertex>();
      Queue<Vertex> line = new LinkedList<Vertex>();
      
      for(Vertex v : (vertexMap.get(name)).getAdjacencies())
      line.add(v);
      
      
      while(!line.isEmpty())
      {
      
        
        if(!(reach.contains(line.peek())))
        {
          Vertex te = line.remove();
          reach.add(te);
          for(Vertex v :te.getAdjacencies())
          
          {
            if(!line.contains(v))
            {
              line.add(v);
            }
         }
        }
      }
      return reach;
   

   
         }
   
   public List<Vertex> breadthFirstSearch(String name)
   {
   Stack<Vertex> w = new Stack<Vertex>();
      List<Vertex> reach = new ArrayList<Vertex>();
      
      for(Vertex v :(vertexMap.get(name)).getAdjacencies())
      {
         w.push(v);
      }
      
      while(( !w.isEmpty()))
      {   
         Vertex te = w.pop(); 
        if(!reach.contains(te))
        {
         
          reach.add(te);
          for(Vertex v : te.getAdjacencies())
          {
            
              w.push(v);
          }
        }
         
      }
      return reach;

   }   
   
   /*   extra credit methods 
   public List<Vertex> depthFirstRecur(String name)
   {
   }
   public List<Vertex> depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable)
   {
   }
   */
   
   public Set<Vertex> getVertices()
   {
      Set<Vertex> ret = new TreeSet<Vertex>();
      for(String i : vertexMap.keySet())
      {
         ret.add(vertexMap.get(i));
      }
      
      return ret;
       
   }
   public Vertex getVertex(String vName)
   {
      return vertexMap.get(vName);
   }
   public Map<String, Vertex> getVertexMap()  //this is just for codepost testing
   {
      return vertexMap;
   }  
   
   public void addVertex(String vName) 
   {
       if (!vertexMap.containsKey(vName))
       {
         Vertex x = new Vertex(vName);
         vertexMap.put(vName, x);
       }
   }
   
   public void addEdge(String source, String target)
   {
       vertexMap.get(source).addAdjacent(vertexMap.get(target));
   } 
   
   
   
   public String toString()
   {
      String end = "";
      for (String key : vertexMap.keySet())
      {
         end = end + vertexMap.get(key).toString();
         end = end + "\n";
      }
      
      
      return end.strip();

   }
 
 
 
 
 
 
}


