import java.util.Arrays;

public class DijkstraAlgorithm {
	
	int n; 
	int m; 
	int [][]edges; 
	int [][]weight;
	boolean []visited;
		
	public DijkstraAlgorithm(int n, int m, int [][]edges, int [][]weight) {
		this.n= n;
		this.m= m;
		this.edges = edges;
		this.weight = weight;
		this.visited = new boolean[n];
	}
	
	public int[] shortestPath(int node) {
		int[] d = new int[n];
		//BaseCase: Alle Knotendistanzen von Knoten node werden mit Maximaler Distanz gekennzeichnet
		//Distanz von Knoten node nach node ist 0
		for (int i = 0; i < n; i++) {
			d[i] = Integer.MAX_VALUE;
		}
		
		d[node] = 0;
		helpershortestPath(node,d);
		clearVis();
		return d;
	}
	
	public int[] helpershortestPath(int node, int[] d) {
		visited[node] = true;
		
		//Hier werden die Distanzen nach minimum geupdatet
		for (int i = 0; i < edges[node].length; i++) {
			if (!visited[edges[node][i]]) {
				int x = d[edges[node][i]]; 
				int y = d[node] + weight[node][i];
				d[edges[node][i]] = Math.min(x, y);
			}
		}
		int next = Integer.MAX_VALUE;
		
		//Schliesslich suchen wir den Knoten mit der kleinsten Distanz welcher noch nicht besucht wurde
		//und führen dort Suche weiter aus bis alle Knoten besucht wurden.
		for (int i = 0; i < d.length; i++) {
			if(!visited[i]) {
				if(next > d[i]) {
					next = d[i];
				}
			}
		}
		
		if(next != Integer.MAX_VALUE) {
			helpershortestPath(linearSearch(d,next), d);
		}
		
		return d;
	}
	
	public void clearVis() {
		for (int i = 0; i < n; i++) {
			visited[i] = false;
		}
	}
	
	public int linearSearch(int []d, int next) {
		int index = - 1;
		for (int i = 0; i < d.length; i++) {
			if(d[i] == next) {
				index = i;
			}
		}
		return index;
	}
	
}

