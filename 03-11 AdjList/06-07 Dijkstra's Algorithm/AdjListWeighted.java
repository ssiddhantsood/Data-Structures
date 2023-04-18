// Name: Siddhant Sood
// Date:
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces for 
 *              Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Neighbor implements Comparable<Neighbor>
{
   //2 Neighbors are equal if and only if they have the same name
   //implement all methods needed for a HashSet and TreeSet to work with Neighbor objects
   private final wVertex target;
   private final double edgeDistance;
   
   public Neighbor(wVertex t, double d)
   {
      target = t;
      edgeDistance = d;
   }
   
   public wVertex getTarget()
   {
      return target;
   }
   
   public double getDistance()
   {
      return edgeDistance;
   
   }
   
   //add all methods needed for a HashSet and TreeSet to function with Neighbor objects
   //use only target, not distances, since a vertex can't have 2 neighbors that have the same target
   //.........
   public int hashCode()
   {
      return target.hashCode();
   }
   
   public boolean equals(Object other)
   {
      return hashCode() == ((other).hashCode());
   }
   
   public int compareTo(Neighbor n)
   {
      return target.getName().compareTo(n.getTarget().getName());
   }
   
  
   public String toString()
   {
      return target.getName() + " " + edgeDistance;  
   }
}

 /**************************************************************/
class PQelement implements Comparable<PQelement> { 
//used just for a PQ, contains a wVertex and a distance, also previous that is used for Dijksra 7
//compareTo is using the distanceToVertex to order them such that the PriorityQueue works
//will be used by the priority queue to order by distance
 
   private wVertex vertex;
   private Double distanceToVertex; 
   private wVertex previous; //for Dijkstra 7
      
   public PQelement(wVertex v, double d) {
      vertex = v;
      distanceToVertex = d;
   }
   
   //getter and setter methods provided
   public wVertex getVertex() {
      return this.vertex;
   }
   
   public Double getDistanceToVertex() {
      return this.distanceToVertex;
   }
   
   public void setVertex(wVertex v) {
      this.vertex = v;
   }
   
   public void setDistanceToVertex(Double d) {
      this.distanceToVertex = d;
   }   
   
   public int compareTo(PQelement other) {
      //we assume no overflow will happen since distances will not go over the range of int
      return (int)(distanceToVertex - other.distanceToVertex);
   }
   
   public wVertex getPrevious()  //Dijkstra 7
   {
      return this.previous;
   }
   public void setPrevious(wVertex v) //Dijkstra 7
   {
      this.previous = v;
   } 
   
   //implement toString to match the sample output   
   public String toString()
   { 
      String toReturn = "";
      //your code here...
      
      toReturn = vertex.getName()+ " "+ distanceToVertex;
      
      return toReturn;
   }
}

/********************* wVertexInterface ************************/
interface wVertexInterface 
{
   public String getName(); 
      
    //returns an arraylist of PQelements that store distanceToVertex to another wVertex
   public ArrayList<PQelement> getAlDistanceToVertex();
   
   //returns the PQelement that has the vertex equal to v
   public PQelement getPQelement(wVertex v);
      
   /*
   postcondition: returns null if wVertex v is not in the alDistanceToVertex
                  or the distance associated with that wVertex in case there is a PQelement that has v as wVertex
   */
   public Double getDistanceToVertex(wVertex v);
   
   /*
   precondition:  v is not null
   postcondition: - if the alDistanceToVertex has a PQelement that has the wVertex component equal to v
                  it updates the distanceToVertex component to d
                  - if the alDistanceToVertex has no PQelement that has the wVertex component equal to v,
                  then a new PQelement is added to the alDistanceToVertex using v and d   
   */
   public void setDistanceToVertex(wVertex v, double m);
   public Set<Neighbor> getNeighbors(); 
   public void addAdjacent(wVertex v, double d);  
   public String toString(); 
}

class wVertex implements Comparable<wVertex>, wVertexInterface 
{ 
   public static final double NODISTANCE = Double.POSITIVE_INFINITY; //constant to be used in class
   private final String name;
   private Set<Neighbor> neighbors;  
   private ArrayList<PQelement> alDistanceToVertex; //should have no duplicates, enforced by the getter/setter methods
  
   /* constructor, accessors, modifiers  */ 
   
   public wVertex(String a, Set<Neighbor> b, ArrayList<PQelement> c)
   {
      this.name = a;
      this.neighbors = b;
      this.alDistanceToVertex = c;
      
   }
   
   public wVertex(String a)
   {
      this.name = a;
      alDistanceToVertex = new ArrayList <PQelement>();
      neighbors = new HashSet <Neighbor> ();
      
   }

    
   public String getName()
   {
      return name;
   }
    
   public ArrayList<PQelement> getAlDistanceToVertex()
   {
      return alDistanceToVertex;
   }
   
   
   public PQelement getPQelement(wVertex v)
   {
      for(PQelement x: alDistanceToVertex)
      {
         if(x.getVertex().equals(v))
             return x;
     
      }
      return null;

   }
      
   public Double getDistanceToVertex(wVertex v)
   {
      PQelement x = getPQelement(v);
      if(x==null) 
          return Double.POSITIVE_INFINITY;
      
      return x.getDistanceToVertex();
   }
   
   public void setDistanceToVertex(wVertex v, double m)
   { 
      getPQelement(v).setDistanceToVertex(m);
   }
   public Set<Neighbor> getNeighbors()
   {
      return neighbors;
   }
   public void addAdjacent(wVertex v, double d)
   {
      Neighbor x = new Neighbor(v,d);
      neighbors.add(x);
   }
   
     
   public String toString()
   { 
      String toReturn = name;
      toReturn += " "+ neighbors;
      toReturn += " Distances: " + alDistanceToVertex; 
      return toReturn;
   }
   
   public int hashCode()
   {
      return name.hashCode();
   }
   
   public boolean equals(Object other)
   {
      return hashCode() == ((other).hashCode());
   }
   
   public int compareTo(wVertex n)
   {
      return name.compareTo(n.getName());
   }

}

/*********************   Interface for Graphs 6:  Dijkstra ****************/
interface AdjListWeightedInterface 
{
   public Set<wVertex> getVertices();  
   public Map<String, wVertex> getVertexMap();  //this is just for codepost testing
   public wVertex getVertex(String vName);
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName);
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d);
   public void minimumWeightPath(String vertexName); // Dijstra's algorithm
   public String toString();  
}  

 /***********************  Interface for Graphs 7:  Dijkstra with Cities   */
interface AdjListWeightedInterfaceWithCities 
{       
   public List<String> getShortestPathTo(wVertex target);
   public void readData(String vertexNames, String edgeListData) ;
}
 
/****************************************************************/ 
/**************** this is the graph  ****************************/
public class AdjListWeighted implements AdjListWeightedInterface//,AdjListWeightedInterfaceWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, wVertex> vertexMap = new TreeMap<String, wVertex>();
   
   /* default constructor -- not needed!  */
   public Set<wVertex> getVertices()
   {
   
      Set <wVertex> v = new TreeSet<wVertex>();
   
      for(String x: vertexMap.keySet())
         v.add(vertexMap.get(x));
      
      return v;
   
   } 
   public Map<String, wVertex> getVertexMap()
   {
      return vertexMap;
   }
   public wVertex getVertex(String vName)
   {
      return vertexMap.get(vName);
   }
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName)
   {
      vertexMap.put(vName, new wVertex(vName));
   }
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d)
   {
      vertexMap.get( source).addAdjacent( vertexMap.get(target),d);
   }
   
   public void minimumWeightPath(String vertexName)
   { 
       PriorityQueue<PQelement> p = new PriorityQueue<PQelement>();
       ArrayList<PQelement> everything = vertexMap.get(vertexName).getAlDistanceToVertex();
       
       for(String x:vertexMap.keySet())
       {
           if(vertexMap.get(x).equals(vertexMap.get(vertexName)))
           {
              p.add(new PQelement(vertexMap.get(x),0));
              everything.add(p.peek());
               
           }
           else 
              everything.add(new PQelement(vertexMap.get(x), Double.POSITIVE_INFINITY)); 
            
       }
       
       
       while(!p.isEmpty()) 
       {
           PQelement x = p.remove();
           for(Neighbor y : x.getVertex().getNeighbors())
           {
              
              if(y.getDistance() + vertexMap.get(vertexName).getDistanceToVertex(x.getVertex()) < (vertexMap.get(vertexName).getDistanceToVertex(y.getTarget()))) 
              {
                 PQelement s = vertexMap.get (vertexName).getPQelement(y.getTarget());  
                 s.setDistanceToVertex (y.getDistance() + vertexMap.get(vertexName).getDistanceToVertex(x.getVertex()));             
                 p.add(s);
              }
           }
       }
    
   }
   
    // Dijstra's algorithm
   /* similar to AdjList, but handles distances (weights) and wVertex*/ 
   
   
   public String toString()
   {
      String strResult = "";
      for(String vName: vertexMap.keySet())
      {
         strResult += vertexMap.get(vName) + "\n"; 
      }
      return strResult;
   }
   
   /*  Graphs 7 has two more methods */
   public List<String> getShortestPathTo(wVertex source, wVertex target) 
   {
   
      
      PQelement c = source.getPQelement(target);
      LinkedList<String> p = new LinkedList<>();
      
      while( (c.getPrevious() != null)  && (c.getVertex() != source)  )
      {
         p.addFirst( c.getVertex().getName()  );
         c = source.getPQelement(c.getPrevious());
      }
      
      p.addFirst ( c.getVertex().getName() );
      return p;
   }  
     
   public void readData(String vertexNames, String edgeListData) 
   {
     /* use a try-catch  */
      
      Scanner a = null;
      
      try
      { 
         a = new Scanner(new File(edgeListData)); 
         for ( int i = 0 ; i < Integer.parseInt(a.nextLine()); i++)
            addVertex(a.nextLine());
         
      }
      
      catch (IOException e)
         {  System.exit(0);  }
         

      try
      {  
         a = new Scanner(new File(vertexNames)); 
         while( a.hasNext() )
            addEdge(a.next(),a.next(),Double.parseDouble(a.next()));
    
      }
      catch (IOException e)        
          {  System.exit(0); }

      
    }
}