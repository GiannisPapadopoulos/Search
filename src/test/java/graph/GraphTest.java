package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {

	Graph<String> graph;

	Vertex<String> v1, v2, v3, v4;
	Edge<String> e1, e2, e3, e4, e5;

	@Before
	public void setup() {
		graph = new Graph<String>();

		// add some vertices to the graph
		v1 = graph.addVertex("v1");
		v2 = graph.addVertex("v2");
		v3 = graph.addVertex("v3");
		v4 = graph.addVertex("v4");

		// add some undirected edges
		e1 = graph.addEdge("1-3", v1, v3, false);
		e2 = graph.addEdge("2-3", v2, v3, false);

		// add some directed edges
		e3 = graph.addEdge("4-1", v4, v1, true);
		e4 = graph.addEdge("3-4", v3, v4, true);
		e5 = graph.addEdge("1-2", v1, v2, true);
	}

	@Test
	public void testCounts() {
		assertEquals("graph should have 4 vertices", graph.getVertexCount(), 4);
		assertEquals("graph should have 5 edges", graph.getEdgeCount(), 5);
	}

	@Test
	public void testNeighbors() {
		// test adjacent vertices
		assertTrue("1 and 3 should be eachothers Adjacents", v1.isAdjacentVertex(v3) && v3.isAdjacentVertex(v1));
		assertTrue("2 and 3 should be eachothers Adjacents", v2.isAdjacentVertex(v3) && v3.isAdjacentVertex(v2));
		assertTrue("Only 4 should have 1 as Adjacent", !v1.isAdjacentVertex(v4) && v4.isAdjacentVertex(v1));
		assertTrue("Only 3 should have 4 as Adjacent", !v4.isAdjacentVertex(v3) && v3.isAdjacentVertex(v4));
		assertTrue("Only 1 should have 2 as Adjacent", !v2.isAdjacentVertex(v1) && v1.isAdjacentVertex(v2));

		// test adjacent edges
		assertTrue("1 and 3 should both have edge 1-3 as Adjacent", v1.isAdjacentEdge(e1) && v3.isAdjacentEdge(e1));
		assertTrue("2 and 3 should both have edge 2-3 as Adjacent", v2.isAdjacentEdge(e2) && v3.isAdjacentEdge(e2));
		assertTrue(!v1.isAdjacentEdge(e3));
		assertTrue("Only 4 should have edge 4-1 as Adjacent", v4.isAdjacentEdge(e3) && !v1.isAdjacentEdge(e3));
		assertTrue("Only 3 should have edge 3-4 as Adjacent", v3.isAdjacentEdge(e4) && !v4.isAdjacentEdge(e4));
		assertTrue("Only 1 should have edge 1-2 as Adjacent", v1.isAdjacentEdge(e5) && !v2.isAdjacentEdge(e5));
	}

	@Test
	public void testDirected() {
		// test undirected
		assertTrue("Edge 1-3 should be undirected", !e1.isDirected());
		assertTrue("Edge 2-3 should be undirected", !e2.isDirected());

		// test directed
		assertTrue("Edge 4-1 should be directed", e3.isDirected());
		assertTrue("Edge 3-4 should be directed", e4.isDirected());
		assertTrue("Edge 1-2 should be directed", e5.isDirected());
	}

	@Test
	public void testGetNeighbour() {
		assertEquals("neighbor vertex should be v1", v4.getNeighbor(e3), v1);
		assertEquals("neighbor vertex should be v4", v1.getNeighbor(e3), v4);
		assertEquals("neighbor vertex should be v1", v3.getNeighbor(e4), v4);
		assertEquals("neighbor vertex should be v4", v4.getNeighbor(e4), v3);
		assertEquals("neighbor vertex should be v2", v1.getNeighbor(e5), v2);
		assertEquals("neighbor vertex should be v1", v2.getNeighbor(e5), v1);

		assertEquals("neighbor vertex should be v3", v1.getNeighbor(e1), v3);
		// Edge is directed, should it return null? maybe not
		assertEquals("neighbor vertex should be v1", v3.getNeighbor(e1), v1);

	}

}
