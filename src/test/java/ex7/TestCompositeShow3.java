package ex7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

/** Test cho Exercise 7: Show2 extends Iterable<Show2>, mọi Show đều duyệt được. */
public class TestCompositeShow3 {

	private static final Concert2 CONCERT = new Concert2("Concert 1", "Artist X", 100);
	private static final Movie2 MOVIE1 = new Movie2("Movie 1", 2017, 120);
	private static final Movie2 MOVIE2 = new Movie2("Movie 2", 2018, 130);

	private final CompositeShow3 aInner = new CompositeShow3(MOVIE1, MOVIE2);
	private final CompositeShow3 aOuter = new CompositeShow3(CONCERT, aInner);

	@Test
	public void testIterationOrder() {
		Iterator<Show2> iterator = aOuter.iterator();
		assertSame(CONCERT, iterator.next());
		assertSame(aInner, iterator.next());
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testAnyShowIsIterableWithoutInstanceof() {
		Show2 composite = aOuter; // kiểu tĩnh là Show2, không cần ép kiểu
		int count = 0;
		for (Show2 ignored : composite) {
			count++;
		}
		assertEquals(2, count);
	}

	@Test
	public void testLeafHasEmptyIterator() {
		assertFalse(MOVIE1.iterator().hasNext());
		assertFalse(CONCERT.iterator().hasNext());
		assertTrue(Ex7Demo.unpack(MOVIE1).isEmpty());
	}

	@Test
	public void testShowIsUsableAsIterable() {
		Iterable<Show2> iterable = aOuter; // quan hệ kiểu con do Show2 extends Iterable<Show2>
		assertTrue(iterable.iterator().hasNext());
	}

	@Test
	public void testIteratorIsReadOnly() {
		Iterator<Show2> iterator = aOuter.iterator();
		iterator.next();
		assertThrows(UnsupportedOperationException.class, iterator::remove);
	}

	@Test
	public void testLeavesFlattenWithoutInstanceof() {
		List<Show2> leaves = Ex7Demo.leaves(aOuter);
		assertEquals(3, leaves.size());
		assertSame(CONCERT, leaves.get(0));
		assertSame(MOVIE1, leaves.get(1));
		assertSame(MOVIE2, leaves.get(2));
		assertEquals(1, Ex7Demo.leaves(MOVIE1).size()); // lá tự là lá của chính nó
	}

	@Test
	public void testLeafCopyIsStillIterable() {
		Show2 copy = MOVIE1.copy();
		assertNotSame(MOVIE1, copy);
		assertFalse(copy.iterator().hasNext());
	}

	@Test
	public void testCompositeCopyIsDeep() {
		Show2 copy = aOuter.copy();
		assertNotSame(aOuter, copy);
		assertEquals(aOuter.description(), copy.description());
		assertEquals(aOuter.runningTime(), copy.runningTime());
		List<Show2> originalChildren = Ex7Demo.unpack(aOuter);
		List<Show2> copiedChildren = Ex7Demo.unpack(copy);
		assertNotSame(originalChildren.get(0), copiedChildren.get(0));
		assertEquals(2, copiedChildren.size());
	}

	@Test
	public void testSizeBounds() {
		assertThrows(IllegalArgumentException.class, () -> new CompositeShow3(MOVIE1));
		List<Show2> six = new ArrayList<>(List.of(MOVIE1, MOVIE2, MOVIE1, MOVIE2, MOVIE1, MOVIE2));
		assertThrows(IllegalArgumentException.class, () -> new CompositeShow3(six.toArray(new Show2[0])));
	}
}
