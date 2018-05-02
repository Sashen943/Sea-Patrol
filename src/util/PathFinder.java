package util;

import java.util.ArrayList;
import java.util.HashMap;

import model.graph.Graph;

public class PathFinder {
	
	public static <V> HashMap<V, Double> dijkstraShortestPath(Graph<V> graph,
			V source) {
		HashMap<V, Double> distances = new HashMap<>();
		ArrayList<V> queue = new ArrayList<>();
		ArrayList<V> visited = new ArrayList<>();
		queue.add(0, source);
		distances.put(source, 0.0);
		while (!queue.isEmpty()) {
			V currentVertex = queue.remove(queue.size() - 1);
			if (distances.get(currentVertex) == null) {
				distances.put(currentVertex, Double.POSITIVE_INFINITY);
			}
			for (V adjacentVertex : graph.getAdjacentVertices(currentVertex)) {
				if (distances.get(adjacentVertex) == null) {
					distances.put(adjacentVertex, Double.POSITIVE_INFINITY);
				}
				if (true) {

					if (distances.get(adjacentVertex) > graph
							.getDistanceBetween(currentVertex, adjacentVertex)
							+ distances.get(currentVertex)) {

						distances.put(
								adjacentVertex,
								graph.getDistanceBetween(currentVertex,
										adjacentVertex)
										+ distances.get(currentVertex));
					}
				}

				if (!visited.contains(adjacentVertex)
						&& !queue.contains(adjacentVertex)) {
					queue.add(0, adjacentVertex);
				}
			}
			visited.add(currentVertex);

		}

		for (V v : graph.getVertexList()) {
			if (!distances.containsKey(v)) {
				distances.put(v, Double.POSITIVE_INFINITY);
			}
		}

		return distances;
	}

	public static <V> ArrayList<V> shortestUnweightedPath(Graph<V> graph, V source, V end){
		HashMap<V, V> parent = new HashMap<V, V>();
		parent.put(source, null);
		ArrayList<V> path = new ArrayList<>();
		ArrayList<V> queue = new ArrayList<>();
		ArrayList<V> visited = new ArrayList<>();
		queue.add(0, source);
		boolean done = false;
		while (!queue.isEmpty() && !done) {
			V currentVertex = queue.remove(queue.size() - 1);
			for (V adjacentVertex : graph.getAdjacentVertices(currentVertex)) {
				if (adjacentVertex == end){
					path.add(0, adjacentVertex);
					path.add(0, currentVertex);
					while(parent.get(currentVertex) != null){
						currentVertex =  parent.get(currentVertex);
						path.add(0, currentVertex);
					}
					done = true;
					break;
				}
				if (!visited.contains(adjacentVertex)
						&& !queue.contains(adjacentVertex)) {
					parent.put(adjacentVertex, currentVertex);
					queue.add(0, adjacentVertex);
				}
			}
			visited.add(currentVertex);
		}
		return path;
		
	}

}
