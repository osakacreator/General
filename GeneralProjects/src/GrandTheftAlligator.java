import algorithms.*;
import java.util.*;

public class GrandTheftAlligator {
	
	    public static void main(String[] args) {
	        // Uncomment this line if you want to read from a file
	        //In.open("public/test1.in");
	        //Out.compareTo("public/test1.out");

	        int t = In.readInt();
	        for (int i = 0; i < t; i++) {
	            testCase();
	        }
	        
	        // Uncomment this line if you want to read from a file
	        // In.close();
	    }

	    public static void testCase() {
	      int city = In.readInt();
	      int rWithP = In.readInt();
	      int rNoP = In.readInt();
	      int source = In.readInt();
	      int zoo = In.readInt();
	      int sink = In.readInt();
	      int[][] eWithP = getArray("P",rWithP);
	      int[][] eNoP = getArray("N",rNoP);
	      
	      if(checkRich(eNoP,city,source,zoo,sink)){
	        Out.println("RICH");
	      }
	      else{
	        Out.println(getAlligator(eWithP,eNoP,city,source,zoo,sink));
	      }
	    }
	    
	    public static int getAlligator(int[][] eWithP, int[][] eNoP, int city, int source, int zoo, int sink){
	      Graph g = new Graph(city);
	      for(int i = 0; i < eWithP.length; i++){
	        g.addEdge(eWithP[i][0],eWithP[i][1],eWithP[i][2]);
	      }
	      for(int i = 0; i < eNoP.length; i++){
	        g.addEdge(eNoP[i][0],eNoP[i][1],1000);
	      }
	      int a = g.computeMaximumFlow(source,zoo);
	      int b = g.computeMaximumFlow(zoo,sink);
	      return Math.min(a,b);
	    }
	    
	    public static boolean checkRich(int[][] edge, int city, int source, int zoo, int sink){
	      Graph g = new Graph(city);
	      for(int i = 0; i < edge.length; i++){
	       g.addEdge(edge[i][0],edge[i][1],1); 
	      }
	      
	      if(source != zoo){
	        int maxFlow = g.computeMaximumFlow(source,zoo);
	        if(maxFlow > 0){
	          return checkRich(edge,city,zoo,zoo,sink);
	        }
	        else{
	          return false;
	        }
	      }
	      else{
	        int maxFlow = g.computeMaximumFlow(zoo,sink);
	        if(maxFlow > 0){
	          return true;
	        }
	        else{
	          return false;
	        }
	      }
	    }
	    
	    public static int[][] getArray(String s, int size){
	      int[][] res = null;
	      if(s.equals("P")){
	        res = new int[size][3];
	        for(int i = 0; i < size; i++){
	          res[i][0] = In.readInt();
	          res[i][1] = In.readInt();
	          res[i][2] = In.readInt();
	        }
	      }
	      if(s.equals("N")){
	        res = new int[size][2];
	        for(int i = 0; i < size; i++){
	          res[i][0] = In.readInt();
	          res[i][1] = In.readInt();
	        }
	      }
	      return res;
	    }
	

}
