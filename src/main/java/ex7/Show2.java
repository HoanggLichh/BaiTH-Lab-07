package ex7;

/**
 * Exercise 7: interface Show được SỬA để extends Iterable<Show2>.
 * Mọi Show đều duyệt được; show lá trả về iterator rỗng.
 * All implementations of this interface should be immutable.
 */
public interface Show2 extends Iterable<Show2> {

	/** @return A description of the show. */
	String description();

	/** @return The running time of the show, in minutes. */
	int runningTime();

	Show2 copy();
}
