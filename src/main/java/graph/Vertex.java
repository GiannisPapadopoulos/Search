package graph;

//@Getter
//@Setter
public class Vertex<E> extends Element<E> {

	protected Vertex(Graph<E> parent, int ID, E data) {
		super(parent, ID, data);
	}

	/** Returns the other vertex of the given edge or null if this vertex is not part of the edge */
	public Vertex<E> getNeighbor(Edge<E> edge) {
		Vertex<E> v1 = parent.getVertex(edge.getAdjacentVertices().get(0));
		Vertex<E> v2 = parent.getVertex(edge.getAdjacentVertices().get(1));
		if (v1 != this && v2 != this)
			return null;
		return v1 == this ? v2 : v1;
	}

	// public void notifyVertexAddition(Edge<E> newEdge) {
	// if (!adjacentEdges.contains(newEdge))
	// adjacentEdges.add(newEdge);
	// else
	// System.out.println("Already neighboring...");
	// }

	// public void notifyVertexDeletion(Edge<E> deletedEdge) {
	// if (adjacentEdges.contains(deletedEdge))
	// adjacentEdges.remove(deletedEdge);
	// else
	// System.out.println("Vertex to be deleted is not neighbor of this!");
	// }


	// public void destroy() {
	// for (val e : neighboringEdges) {
	// //e.destroy();
	// neighboringEdges = null;
	// }
	// }

	@Override
	public String toString() {
		return "Vertex " + getID() + " " + data.toString();
	}
}
