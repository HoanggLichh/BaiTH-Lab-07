package ex6;

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

import show.Concert;
import show.Movie;
import show.Show;

/** Test cho Exercise 6: duyệt CompositeShow2 bằng iterator, không đổi interface Show. */
public class TestCompositeShow2 {

	private static final Concert CONCERT = new Concert("Concert 1", "Artist X", 100);
	private static final Movie MOVIE1 = new Movie("Movie 1", 2017, 120);
	private static final Movie MOVIE2 = new Movie("Movie 2", 2018, 130);

	private final CompositeShow2 aInner = new CompositeShow2(MOVIE1, MOVIE2);
	private final CompositeShow2 aOuter = new CompositeShow2(CONCERT, aInner);

	@Test
	public void testIterationOrder() {
		Iterator<Show> iterator = aOuter.iterator();
		assertSame(CONCERT, iterator.next());
		assertSame(aInner, iterator.next());
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testIterationIsOneLevelOnly() {
		List<Show> shows = new ArrayList<>();
		for (Show show : aOuter) {
			shows.add(show);
		}
		assertEquals(2, shows.size()); // composite con là MỘT phần tử, không bị trải phẳng
	}

	@Test
	public void testIteratorIsReadOnly() {
		Iterator<Show> iterator = aOuter.iterator();
		iterator.next();
		assertThrows(UnsupportedOperationException.class, iterator::remove);
		int count = 0;
		for (Show ignored : aOuter) {
			count++;
		}
		assertEquals(2, count); // composite vẫn nguyên vẹn
	}

	@Test
	public void testIteratorsAreIndependent() {
		Iterator<Show> first = aOuter.iterator();
		first.next();
		Iterator<Show> second = aOuter.iterator();
		assertSame(CONCERT, second.next());
	}

	@Test
	public void testClientUnpackCompositeAndLeaf() {
		assertEquals(2, Ex6Demo.unpack(aOuter).size());
		assertTrue(Ex6Demo.unpack(MOVIE1).isEmpty()); // lá: phải kiểm tra instanceof mới biết
	}

	@Test
	public void testClientLeavesFlatten() {
		List<Show> leaves = Ex6Demo.leaves(aOuter);
		assertEquals(3, leaves.size());
		assertSame(CONCERT, leaves.get(0));
		assertSame(MOVIE1, leaves.get(1));
		assertSame(MOVIE2, leaves.get(2));
	}

	@Test
	public void testSizeBoundsInherited() {
		assertThrows(IllegalArgumentException.class, () -> new CompositeShow2(MOVIE1));
		assertThrows(IllegalArgumentException.class,
				() -> new CompositeShow2(MOVIE1, MOVIE2, MOVIE1, MOVIE2, MOVIE1, MOVIE2));
	}

	@Test
	public void testExternalArrayDoesNotAffectComposite() {
		Show[] array = { MOVIE1, MOVIE2 };
		CompositeShow2 composite = new CompositeShow2(array);
		array[0] = CONCERT;
		assertSame(MOVIE1, composite.iterator().next());
	}

	@Test
	public void testCopyKeepsTypeAndIsDeep() {
		Show copy = aOuter.copy();
		assertTrue(copy instanceof CompositeShow2); // bản mẫu trả về CompositeShow (mất khả năng duyệt)
		assertNotSame(aOuter, copy);
		assertEquals(aOuter.description(), copy.description());
		assertEquals(aOuter.runningTime(), copy.runningTime());
		Iterator<Show> original = aOuter.iterator();
		Iterator<Show> copied = ((CompositeShow2) copy).iterator();
		assertNotSame(original.next(), copied.next()); // show con cũng là bản sao
	}

	@Test
	public void testDescriptionAndRunningTime() {
		assertEquals("[Concert 1 by Artist X; [Movie 1 (2017); Movie 2 (2018)]]", aOuter.description());
		assertEquals(350, aOuter.runningTime());
	}
}
