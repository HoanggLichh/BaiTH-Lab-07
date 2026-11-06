package ex7;

import java.util.ArrayList;
import java.util.List;

/** Client của Ex 7: duyệt trực tiếp mọi Show2, KHÔNG cần instanceof. */
public class Ex7Demo {

	/** Các show con trực tiếp (một cấp); show lá cho danh sách rỗng. */
	public static List<Show2> unpack(Show2 pShow) {
		List<Show2> result = new ArrayList<>();
		for (Show2 sub : pShow) {
			result.add(sub);
		}
		return result;
	}

	/** Các show lá, mở gói đệ quy: lá là show không có con. */
	public static List<Show2> leaves(Show2 pShow) {
		List<Show2> result = new ArrayList<>();
		collectLeaves(pShow, result);
		return result;
	}

	private static void collectLeaves(Show2 pShow, List<Show2> pOut) {
		boolean hasChildren = false;
		for (Show2 sub : pShow) {
			hasChildren = true;
			collectLeaves(sub, pOut);
		}
		if (!hasChildren) {
			pOut.add(pShow);
		}
	}

	public static void main(String[] args) {
		Concert2 concert = new Concert2("Concert 1", "Artist X", 100);
		Movie2 movie1 = new Movie2("Movie 1", 2017, 120);
		Movie2 movie2 = new Movie2("Movie 2", 2018, 130);
		Show2 show = new CompositeShow3(concert, new CompositeShow3(movie1, movie2));

		System.out.println("Show goc        : " + show.description() + " / " + show.runningTime() + " phut");
		System.out.println("Cac show con (1 cap), khong can instanceof:");
		for (Show2 sub : show) {
			System.out.println("  - " + sub.description());
		}
		System.out.println("Cac show la (de quy):");
		for (Show2 leaf : leaves(show)) {
			System.out.println("  - " + leaf.description());
		}
		System.out.println("unpack(Movie)   : " + unpack(movie1));
	}
}
