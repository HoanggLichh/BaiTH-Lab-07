package ex6;

import java.util.ArrayList;
import java.util.List;

import show.Concert;
import show.Movie;
import show.Show;

/** Client của Ex 6: phải kiểm tra instanceof trước khi "mở gói". */
public class Ex6Demo {

	/** Các show con trực tiếp (một cấp); show lá thì không có con. */
	public static List<Show> unpack(Show pShow) {
		List<Show> result = new ArrayList<>();
		if (pShow instanceof CompositeShow2 composite) {
			for (Show sub : composite) {
				result.add(sub);
			}
		}
		return result;
	}

	/** Các show lá, mở gói đệ quy; cần instanceof ở MỌI cấp. */
	public static List<Show> leaves(Show pShow) {
		List<Show> result = new ArrayList<>();
		collectLeaves(pShow, result);
		return result;
	}

	private static void collectLeaves(Show pShow, List<Show> pOut) {
		if (pShow instanceof CompositeShow2 composite) {
			for (Show sub : composite) {
				collectLeaves(sub, pOut);
			}
		} else {
			pOut.add(pShow);
		}
	}

	public static void main(String[] args) {
		Concert concert = new Concert("Concert 1", "Artist X", 100);
		Movie movie1 = new Movie("Movie 1", 2017, 120);
		Movie movie2 = new Movie("Movie 2", 2018, 130);
		Show show = new CompositeShow2(concert, new CompositeShow2(movie1, movie2));

		System.out.println("Show goc        : " + show.description() + " / " + show.runningTime() + " phut");
		System.out.println("Cac show con (1 cap):");
		for (Show sub : unpack(show)) {
			System.out.println("  - " + sub.description());
		}
		System.out.println("Cac show la (de quy):");
		for (Show leaf : leaves(show)) {
			System.out.println("  - " + leaf.description());
		}
		System.out.println("unpack(Movie)   : " + unpack(movie1));
	}
}
