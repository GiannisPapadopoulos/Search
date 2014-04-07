package graph;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Graph<E> {

	private List<Vertex<E>> vertexList;
	private List<Edge<E>> edgeList;

	public Graph() {
		vertexList = new ArrayList<Vertex<E>>();
		edgeList = new ArrayList<Edge<E>>();
	}

	public Vertex<E> addVertex(E data) {
		Vertex<E> vertex = new Vertex<E>(this, vertexList.size(), data);
		vertexList.add(vertex);
		return vertex;
	}

	public Edge<E> addEdge(E data, Vertex<E> v1, Vertex<E> v2, boolean directed) {
		Edge<E> edge = new Edge<E>(this, edgeList.size(), data, v1, v2, directed);
		edgeList.add(edge);
		return edge;
	}

	public Iterable<Vertex<E>> getVertexIterator() {
		return vertexList;
	}

	public Iterable<Edge<E>> getEdgeIterator() {
		return edgeList;
	}

	/**
	 * Returns the number of vertices in this graph
	 */
	public int getVertexCount() {
		return vertexList.size();
	}

	/**
	 * Returns the number of edges in this graph
	 */
	public int getEdgeCount() {
		return edgeList.size();
	}

	public Vertex<E> getVertex(int ID) {
		return vertexList.get(ID);
	}

	public Edge<E> getEdge(int ID) {
		return edgeList.get(ID);
	}

}
