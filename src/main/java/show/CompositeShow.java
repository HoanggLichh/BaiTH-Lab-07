package show;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Exercise 1 (nền của Lab 07): một show gồm từ 2 đến 5 show khác (Composite).
 * Lớp này CHƯA hỗ trợ duyệt các show con; Ex 6 và Ex 7 thêm tính năng đó.
 */
public class CompositeShow implements Show {

	private final List<Show> aShows = new ArrayList<>();

	public CompositeShow(Show... pShows) {
		if (pShows.length < 2 || pShows.length > 5) {
			throw new IllegalArgumentException("Arguments should be between two and five shows inclusively");
		}
		aShows.addAll(Arrays.asList(pShows));
	}

	@Override
	public String description() {
		StringJoiner description = new StringJoiner("; ", "[", "]");
		for (Show show : aShows) {
			description.add(show.description());
		}
		return description.toString();
	}

	@Override
	public int runningTime() {
		int time = 0;
		for (Show show : aShows) {
			time += show.runningTime();
		}
		return time;
	}

	@Override
	public Show copy() {
		List<Show> copies = new ArrayList<>();
		for (Show show : aShows) {
			copies.add(show.copy());
		}
		return new CompositeShow(copies.toArray(new Show[copies.size()]));
	}
}
