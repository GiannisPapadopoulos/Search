package graph;

import gnu.trove.list.TIntList;

import java.util.Iterator;

public class ElementIterator<E, A extends Element<E>>
		implements Iterator<A> {

	private Graph<E> graph;

	private TIntList list;

	int i = 0;

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return i < list.size();
	}

	@Override
	public A next() {
		// TODO Auto-generated method stub
		return null;
		// return A.class == Vertex.class ? null : null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

}
