package ex6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import show.Show;

/**
 * Exercise 6: CompositeShow có thể "mở gói" bằng iterator, KHÔNG đổi interface Show.
 * Chỉ CompositeShow2 cài đặt Iterable<Show>; các lá (Movie, Concert) không bị ảnh hưởng.
 */
public class CompositeShow2 implements Show, Iterable<Show> {

	private final List<Show> aShows = new ArrayList<>();

	public CompositeShow2(Show... pShows) {
		if (pShows.length < 2 || pShows.length > 5) {
			throw new IllegalArgumentException("Arguments should be between two and five shows inclusively");
		}
		aShows.addAll(Arrays.asList(pShows));
	}

	/** Iterator chỉ đọc: remove() ném UnsupportedOperationException, nên không phá được composite. */
	@Override
	public Iterator<Show> iterator() {
		return Collections.unmodifiableList(aShows).iterator();
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

	/**
	 * Lưu ý: lời giải mẫu ở đây trả về "new CompositeShow(...)" (lớp gốc của Ex 1),
	 * làm bản sao mất khả năng duyệt. Em trả về CompositeShow2 để bản sao cùng kiểu.
	 */
	@Override
	public Show copy() {
		List<Show> copies = new ArrayList<>();
		for (Show show : aShows) {
			copies.add(show.copy());
		}
		return new CompositeShow2(copies.toArray(new Show[copies.size()]));
	}
}
