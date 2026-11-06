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
public class Concert2 implements Show2 {

	private String aTitle;
	private String aPerformer;
	private int aRunningTime;

	/**
	 * @param pTitle     The title of the concert
	 * @param pPerformer The name of the artist or orchestra performing
	 * @param pTime      The running time of the concert.
	 */
	public Concert2(String pTitle, String pPerformer, int pTime) {
		aTitle = pTitle;
		aPerformer = pPerformer;
		aRunningTime = pTime;
	}

	public Concert2(Concert2 pConcert) {
		this(pConcert.aTitle, pConcert.aPerformer, pConcert.aRunningTime);
	}

	@Override
	public String description() {
		return String.format("%s by %s", aTitle, aPerformer);
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
		return new Concert2(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(aPerformer, aRunningTime, aTitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Concert2 other = (Concert2) obj;
		return Objects.equals(aPerformer, other.aPerformer) && aRunningTime == other.aRunningTime &&
				Objects.equals(aTitle, other.aTitle);
	}
}
