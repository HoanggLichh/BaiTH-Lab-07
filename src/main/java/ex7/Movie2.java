/*******************************************************************************
 * Companion code for the book "Introduction to Software Design with Java" 
 * by Martin P. Robillard.
 *
 * Copyright (C) 2026 by Martin P. Robillard
 *
 * This code is licensed under a Creative Commons 
 * Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * 
 * See http://creativecommons.org/licenses/by-nc-nd/4.0/
 *******************************************************************************/
package ex7;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

/**
 * Represents a show that consists of the screening of a single movie.
 */
public class Movie2 implements Show2 {

	private String aTitle;
	private int aYear;
	private int aRunningTime;

	/**
	 * @param pTitle The title of the movie
	 * @param pYear  The release year of the movie
	 * @param pTime  The running time of the movie.
	 */
	public Movie2(String pTitle, int pYear, int pTime) {
		aTitle = pTitle;
		aYear = pYear;
		aRunningTime = pTime;
	}

	public Movie2(Movie2 pMovie) {
		aTitle = pMovie.aTitle;
		aYear = pMovie.aYear;
		aRunningTime = pMovie.aRunningTime;
	}

	@Override
	public String description() {
		return String.format("%s (%d)", aTitle, aYear);
	}

	@Override
	public int runningTime() {
		return aRunningTime;
	}

	/** Show lá không có show con: iterator rỗng (cái giá của việc sửa interface Show). */
	@Override
	public Iterator<Show2> iterator() {
		return Collections.emptyIterator();
	}

	@Override
	public Show2 copy() {
		return new Movie2(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(aRunningTime, aTitle, aYear);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie2 other = (Movie2) obj;
		return aRunningTime == other.aRunningTime && Objects.equals(aTitle, other.aTitle) && aYear == other.aYear;
	}
}
