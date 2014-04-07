package search;

public abstract class SearchState {

	/** Required to avoid exploring the same state multiple times */
	@Override
	public abstract int hashCode();

	/** Required to avoid exploring the same state multiple times */
	@Override
	public abstract boolean equals(Object o);
}

