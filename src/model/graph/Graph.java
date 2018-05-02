package model.graph;
import java.util.ArrayList;
import java.util.HashMap;

public class Graph<V> {
	
	private HashMap<V, ArrayList<Edge<V>>> adjacencyList;
	private ArrayList<V> vertexList;
	private boolean directed;
	
	public Graph(boolean directed) {
		this.directed = directed;
		adjacencyList = new HashMap<V, ArrayList<Edge<V>>>();
		vertexList = new ArrayList<V>();
	}
	
	public void add(V vertex, ArrayList<Edge<V>> vertices) {
		adjacencyList.put(vertex, vertices);
		vertexList.add(vertex);
			for (Edge<V> vertexConnectedToAddedVertex : vertices) {
				ArrayList<Edge<V>> correspondingConnectedList = adjacencyList.get(vertexConnectedToAddedVertex.getVertex());
				if (correspondingConnectedList == null) {
					adjacencyList.put(vertexConnectedToAddedVertex.getVertex(),
							new ArrayList<Edge<V>>());
					vertexList.add(vertexConnectedToAddedVertex.getVertex());
					correspondingConnectedList = adjacencyList
							.get(vertexConnectedToAddedVertex.getVertex());
				}
				if (!directed) {
					int weight = vertexConnectedToAddedVertex.getWeight();
					correspondingConnectedList.add(new Edge<V>(vertex, weight));
				}
			}
	}
	

	public boolean addEdge(V VertexA, V VertexB, int weight) {
		if (directed) {
			return false;
		}
		
		if (!adjacencyList.containsKey(VertexA)) {
			ArrayList<Edge<V>> tempList = new ArrayList<Edge<V>>();
			tempList.add(new Edge<V>(VertexB, weight));
			add(VertexA, tempList);
			return true;
		}

		if (!adjacencyList.containsKey(VertexB)) {
			ArrayList<Edge<V>> tempList = new ArrayList<Edge<V>>();
			tempList.add(new Edge<V>(VertexA, weight));
			add(VertexB, tempList);
			return true;
		}

		adjacencyList.get(VertexA).add(new Edge<V>(VertexB, weight));
		adjacencyList.get(VertexB).add(new Edge<V>(VertexA, weight));
		return true;
	}

	public ArrayList<V> getAdjacentVertices(V vertex){
		ArrayList<V> list = new ArrayList<V>();
		for (Edge<V> edge : adjacencyList.get(vertex)) {
			list.add(edge.getVertex());
		}
		return list;
	}
	
	public double getDistanceBetween(V source, V end){
		 for (Edge<V> edge : adjacencyList.get(source)) {
			if (edge.getVertex() == end){
				return edge.getWeight();
			}
		}
		return Double.POSITIVE_INFINITY;
	}
	
	public ArrayList<V> getVertexList() {
		return vertexList;
	}
	
	public String toString() {
		String text = "";
		for (V vertex : vertexList) {
			text += vertex.toString();
			text += " : ";
			text += adjacencyList.get(vertex);
			text += "\n";
		}
		return text;
	}
}
