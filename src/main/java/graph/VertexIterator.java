package graph;

import gnu.trove.list.TIntList;

import java.util.Iterator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VertexIterator<E>
		implements Iterator<Vertex<E>>, Iterable<Vertex<E>> {

	@NonNull
	private Graph<E> parent;

	@NonNull
	private TIntList list;

	private int i = 0;
	

	@Override
	public boolean hasNext() {
		return i < list.size();
	}

	@Override
	public Vertex<E> next() {
		return parent.getVertex(list.get(i++));
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public void reset() {
		i = 0;
	}

	@Override
	public Iterator<Vertex<E>> iterator() {
		reset();
		return this;
	}

}
