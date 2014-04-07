package graph;

import gnu.trove.list.TIntList;

import java.util.Iterator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EdgeIterator<E>
		implements Iterator<Edge<E>>, Iterable<Edge<E>> {

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
	public Edge<E> next() {
		return parent.getEdge(list.get(i++));
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public void reset() {
		i = 0;
	}

	@Override
	public Iterator<Edge<E>> iterator() {
		reset();
		return this;
	}

}
