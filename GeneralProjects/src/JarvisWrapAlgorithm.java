import algorithm.*;
import java.util.*;

public class JarvisWrapAlgorithm {
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
	      int n = In.readInt();
	      int m = In.readInt();
	      int[] start = new int[1];
	      int[][] rose = getArray(n, start);
	      int[][] weed = getArray(m, null);
	      ArrayList<Integer> randkanten = jarvisWrap(rose,start[0]);
	      String res = getFinalString(randkanten, weed, rose);
	      Out.println(res);
	    }
	    
	    public static int[][] getArray(int k, int[] start){
	      int[][] res = new int[k][2];
	      int smallX = Integer.MAX_VALUE; 
	      int smallY = Integer.MAX_VALUE;
	      
	      for(int i = 0; i < k; i++){
	        res[i][0] = In.readInt();
	        res[i][1] = In.readInt();
	        
	        if(start != null){
	          if((res[i][0] < smallX) || (res[i][0] == smallX && res[i][1] < smallY)){
	            smallX = res[i][0];
	            smallY = res[i][1];
	            start[0] = i;
	          }
	        }
	      }
	      
	      return res;
	    }
	    
	    public static String getFinalString(ArrayList<Integer> randkanten, int[][] weed, int[][] rose){
	      String res = "";
	      for(int i = 0; i < weed.length; i++){
	        res = res + getOneString(randkanten,rose,weed[i][0],weed[i][1]);
	      }
	      return res;
	    }
	    
	    public static String getOneString(ArrayList<Integer> randkanten, int[][] rose, int px, int py){
	      for(int i = 0; i < randkanten.size() - 1; i++){
	        int indexQ = randkanten.get(i);
	        int indexR = randkanten.get(i + 1);
	        int qx = rose[indexQ][0];
	        int qy = rose[indexQ][1];
	        int rx = rose[indexR][0];
	        int ry = rose[indexR][1];
	        if(!pointLeftOrSmaller(px,py,qx,qy,rx,ry)){
	          return "n";
	        }
	      }
	      return "y";
	    }
	    
	    public static ArrayList<Integer> jarvisWrap(int[][] rose, int start){
	      Set<Integer> visited = new HashSet<Integer>();
	      ArrayList<Integer> randkanten = new ArrayList<Integer>();
	      int pOut = start;
	      int pIn = findNext(pOut,visited,rose);
	      randkanten.add(pOut);
	      randkanten.add(pIn);
	      while(pIn != start){
	        pOut = pIn;
	        pIn = findNext(pOut,visited,rose);
	        randkanten.add(pIn);
	      }
	      return randkanten;
	    }
	    
	    public static int getRandomPoint(Set<Integer> visited, int q, int k){
	      for(int i = 0; i < k; i++){
	        if(!visited.contains(i) && i != q){
	          visited.add(i);
	          return i;
	        }
	      }
	      return -1;
	    }
	    
	    public static int findNext(int q, Set<Integer> visited, int[][] rose){
	      int pZero = getRandomPoint(visited, q, rose.length);
	      int qnext = 0;
	      
	      if(pZero != -1){
	        qnext = pZero;
	      }
	      
	      for (int i = 0; i < rose.length; i++){
	        if(i != q && i != pZero){
	          if(!pointLeftOrSmaller(rose[i][0],rose[i][1],rose[q][0],rose[q][1],rose[qnext][0],rose[qnext][1])){
	            qnext = i;
	          }
	        }
	      }
	      
	      return qnext;
	    }
	    
	    public static boolean pointLeftOrSmaller(int px, int py, int qx, int qy, int rx, int ry){
	      int big = (qx-px)*(ry-py);
	      int small = (qy-py)*(rx-px);
	      double lengthOld = Math.hypot((double)(rx-qx) ,(double)(rx-qy));
	      double lengthNew = Math.hypot((double)(px-qx) ,(double)(px-qy));
	      
	      if(big > small){
	        return true;
	      }
	      else if(big == small && lengthNew <= lengthOld){
	        return true;
	      }
	      else if (px == qx && py == qy){
	        return true;
	      }
	      else{
	        return false;
	      }
	      
	    }
	

}