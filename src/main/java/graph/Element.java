package graph;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Element<E> {

	protected Graph<E> parent;
	protected int ID;

	protected E data;

	protected TIntList adjacentVertices;
	protected TIntList adjacentEdges;


	protected Element(Graph<E> parent, int ID, E data) {
		this.parent = parent;
		this.ID = ID;
		this.data = data;
		adjacentVertices = new TIntArrayList();
		adjacentEdges = new TIntArrayList();
	}

	public VertexIterator<E> getAdjacentVertexIterator() {
		return new VertexIterator<E>(parent, adjacentVertices);
	}

	public EdgeIterator<E> getAdjacentEdgeIterator() {
		return new EdgeIterator<E>(parent, adjacentEdges);
	}

	public ElementIterator<E, Edge<E>> getAdjacentEdgesIterator() {
		return new ElementIterator<E, Edge<E>>();
	}

	public boolean isAdjacentVertex(Vertex<E> vertex) {
		return adjacentVertices.contains(vertex.getID());
	}

	public boolean isAdjacentEdge(Edge<E> edge) {
		return adjacentEdges.contains(edge.getID());
	}

}