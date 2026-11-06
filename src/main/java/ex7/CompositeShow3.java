package ex7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

/** Exercise 7: composite cài đặt Show2 (đã là Iterable<Show2>). */
public class CompositeShow3 implements Show2 {

	private final List<Show2> aShows = new ArrayList<>();

	public CompositeShow3(Show2... pShows) {
		if (pShows.length < 2 || pShows.length > 5) {
			throw new IllegalArgumentException("Arguments should be between two and five shows inclusively");
		}
		aShows.addAll(Arrays.asList(pShows));
	}

	@Override
	public Iterator<Show2> iterator() {
		return Collections.unmodifiableList(aShows).iterator();
	}

	@Override
	public String description() {
		StringJoiner description = new StringJoiner("; ", "[", "]");
		for (Show2 show : aShows) {
			description.add(show.description());
		}
		return description.toString();
	}

	@Override
	public int runningTime() {
		int time = 0;
		for (Show2 show : aShows) {
			time += show.runningTime();
		}
		return time;
	}

	@Override
	public Show2 copy() {
		List<Show2> copies = new ArrayList<>();
		for (Show2 show : aShows) {
			copies.add(show.copy());
		}
		return new CompositeShow3(copies.toArray(new Show2[copies.size()]));
	}
}
