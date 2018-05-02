package model.graph;

public class Edge<V> {
	
	private V vertex;
	private int weight;
	
	
	public Edge(V vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}


	public V getVertex() {
		return vertex;
	}


	public void setVertex(V vertex) {
		this.vertex = vertex;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String toString() {
		String line = "( "+ vertex + ", " + weight + " )";
		return line;
	}

}
