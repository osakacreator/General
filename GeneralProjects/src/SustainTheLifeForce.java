public class SustainTheLifeForce {
	import algorithms.*;
	import java.util.*;

	    public static void main(String[] args) {
	        // Uncomment this line if you want to read from a file
	        In.open("public/test1.in");
	        Out.compareTo("public/test1.out");

	        int t = In.readInt();
	        for (int i = 0; i < t; i++) {
	            testCase();
	        }
	        
	        // Uncomment this line if you want to read from a file
	        // In.close();
	    }

	    public static void testCase() {
	      //number of planet, a planet has a storehouse and an airport
	      int n = In.readInt();
	      //number of edges between planet
	      int m = In.readInt();
	      //number of vertex in ouer Network, n-times storehouse, n-times airport, source and sink
	      int q = (2 * n) + 2;
	      //final sum to the sink
	      int[] sum = new int[1];
	      //plant[i][0]: planet i has this amount stored in storehouse
	      //plant[i][1]: planet i need this amount to survive
	      int[][] plant = getArray(n,sum);
	      //directed edge[i][j] from i to j
	      int[][] edges = getArray(m,null);
	      //network to compute maxflow
	      Graph g = new Graph(q);
	      //now come on, fill it!
	      fillGraph(g,plant,edges,m,n,q);
	      //compute the maxflow from source 0 to sink q - 1
	      int maxFlow = g.computeMaximumFlow(0,q - 1);
	      
	      if(maxFlow < sum[0]){
	        Out.println("no");
	      }
	      else{
	        Out.println("yes");
	      }
	    }

	    public static int[][] getArray(int n, int[] sum){
	      
	      int[][] res = new int[n][2];
	      
	      for(int i = 0; i < n; i++){
	        res[i][0] = In.readInt();
	        res[i][1] = In.readInt();
	        
	        if(sum != null){
	          sum[0] += res[i][1];
	        }
	      }
	      return res;
	    }
	    
	    public static void fillGraph(Graph g, int[][] plant, int[][] edges, int m, int n, int q){
	      
	      for(int i = 1; i < n + 1; i++){
	        //Fill from source 0 to every planet with cap: local number of plant in storehouse
	        g.addEdge(0, i, plant[i - 1][0]);
	        //Fill from planet to local airport with cap: local number of plant in storehouse
	        g.addEdge(i, n + i, plant[i - 1][0]);
	        //Fill from Airport to sink q - 1 with cap: local number plant needed in storehouse
	        g.addEdge(n + i, q - 1, plant[i - 1][1]);
	      }
	      
	      //Now connect planet to airport from neighboor with cap: local number of plant in storehouse 
	      for(int i = 0; i < m; i++){
	        g.addEdge((edges[i][0] + 1), (edges[i][1] + n + 1), plant[edges[i][0]][0]);
	      }
	    }
}


