/*
 * Edge class for weighted graphs
 */
public class Edge implements Comparable<Edge> {

	/* an edge has weight, two vertices
	 * 
	 */
	private double weight;
	private int v;
	private int w;	

	public Edge(double weight, int v, int w){
		this.v = v;
	}	
	
	// return the two bouding vertices
	public int[] get_vertices(){
		return new int[]{v,w};		
	}
	
	//return weight
	public double weight(){
		return weight;
	}
	
	//new compare method
	@Override
	public int compareTo(Edge other_edge){
		if(this.weight < other_edge.weight)
			return -1;
		if(this.weight > other_edge.weight)
			return 1;
		
		return 0;
	}
	
}
