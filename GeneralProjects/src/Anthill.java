import algorithms.*;
import java.util.*;

class Anthill {
    
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        //In.open("public/test2.in");
        //Out.compareTo("public/test2.out");

        int t = In.readInt();
        for (int i = 0; i < t; i++) {
            testCase();
        }
        
        // Uncomment this line if you want to read from a file
        // In.close();
    }
    
    public static void testCase() {
      int n = In.readInt();
      int s = In.readInt();
      int d = In.readInt();
      int anthill = 0;
      int lunchbox = n - 1;
      Map<Integer,Set<Integer>> sunMap = getMap(s, new HashMap<Integer,Set<Integer>>());
      Map<Integer,Set<Integer>> shadeMap = getMap(d, new HashMap<Integer,Set<Integer>>());
      Graph g = new Graph(n);
      
      doCapacity(g,sunMap,d);
      int maxFlowSunOnly = g.computeMaximumFlow(anthill,lunchbox);
      
      doCapacity(g,shadeMap,1);
      int maxFlow = g.computeMaximumFlow(anthill,lunchbox);
      
      Out.println((maxFlowSunOnly / d) + " " + (maxFlow - maxFlowSunOnly));
      
    }
    
    public static void doCapacity(Graph g, Map<Integer,Set<Integer>> map, int cap){
      
      for(Integer u : map.keySet()){
        for(Integer v : map.get(u)){
          g.addEdge(u,v,cap);
        }
      }
      
    }
    
    public static Map<Integer,Set<Integer>> getMap(int m, Map<Integer,Set<Integer>> map){
      int[][] arr = new int[m][2];
      
      for (int i = 0; i < m; i++){
        arr[i][0] = In.readInt();
        arr[i][1] = In.readInt();
        
        if(map.containsKey(arr[i][0])){
          map.get(arr[i][0]).add(arr[i][1]);
        }
        else{
          map.put(arr[i][0],new HashSet<Integer>());
          map.get(arr[i][0]).add(arr[i][1]);
        }
        
        if(map.containsKey(arr[i][1])){
          map.get(arr[i][1]).add(arr[i][0]);
        }
        else{
          map.put(arr[i][1],new HashSet<Integer>());
          map.get(arr[i][1]).add(arr[i][0]);
        }
      }
      return map;
    }
    
}