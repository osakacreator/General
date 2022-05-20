import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int n = 5;
		int m = 5;
		int[][] edges = {{1,2},{0},{0,3,4},{2,4},{2,3}};
		int[][] weight = {{5,8},{5},{8,1,3},{1,1},{3,1}};
		DijkstraAlgorithm t = new DijkstraAlgorithm(n,m,edges,weight);
		System.out.println(Arrays.toString(t.shortestPath(0)));
	}
}
