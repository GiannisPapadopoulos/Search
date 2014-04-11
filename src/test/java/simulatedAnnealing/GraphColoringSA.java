package simulatedAnnealing;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.set.hash.TIntHashSet;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.io.File;
import java.util.Scanner;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

public class GraphColoringSA {

	private Graph<Void> graph;

	@Before
	public void setUp() throws Exception {
		String[] problems = { "data/gc_50_3", "data/gc_70_7", "data/gc_100_5", "data/gc_250_9", "data/gc_500_1",
								"data/gc_1000_5" };

		// int[] best =    {6, 17, 16, 89, 14, 112};
		// int[] optimal = {6, 17, 15, 73, 12, 81};

		int n = 0;
		String fileName = problems[n];

		Scanner scanner = new Scanner(new File(fileName));
		int N = scanner.nextInt();
		int E = scanner.nextInt();

		graph = new Graph<Void>();

		for (int i = 0; i < N; i++) {
			graph.addVertex(null);
		}

		for (int i = 0; i < E; i++) {
			Vertex<Void> v1 = graph.getVertex(scanner.nextInt());
			Vertex<Void> v2 = graph.getVertex(scanner.nextInt());
			graph.addEdge(null, v1, v2, false);
		}
		scanner.close();

	}

	@Test
	public void test() {
		TIntList coloring = new TIntArrayList();
		for (int i = 0; i < graph.getVertexCount(); i++) {
			coloring.add(i);
		}
		GraphColoringState startState = new GraphColoringState(graph, coloring);

		TemperatureSchedule schedule = new FactorTemperatureSchedule(30, 0.99999);
		PerformanceMeasure<GraphColoringState> pm = new MinimizeColorClassesMeasure();
		System.out.println(startState.getColoring());
		//@formatter:off
		SimulatedAnnealingSearch<GraphColoringState, GraphColoringMove, 
								 AbstractMoveGenerator<GraphColoringState,GraphColoringMove>, 
								 PerformanceMeasure<GraphColoringState>> SA = 
								 	new SimulatedAnnealingSearch<>(new GraphColoringMoveGenerator(), pm, 
		                                                           AcceptanceStrategy.DEFAULT_STRATEGY,
		                                                           schedule, 10000);
		//@formatter:on
		val result = SA.search(startState);

		System.out.println(result.left);
		System.out.println(result.right);

		System.out.println(result.getLeft().getColoring());
		System.out.println("colors used " + new TIntHashSet(result.getLeft().getColoring()).size());

		System.out.println(isValid(graph, result.getLeft().getColoring()));
	}

	private boolean isValid(Graph<Void> graph, TIntList coloring) {
		for (Edge<?> edge : graph.getEdgeIterator()) {
			int v1 = edge.getAdjacentVertices().get(0);
			int v2 = edge.getAdjacentVertices().get(1);
			if (coloring.get(v1) == coloring.get(v2)) {
				System.out.println(edge + " " + coloring.get(v1) + " " + coloring.get(v2));
				return false;
			}
		}
		return true;
	}

}
