/**
 * 
 */
package com.ceridian.demo.democeridian.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Arjoon
 *
 */
public class DateUtils {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * Private constructor to disable instantiation.
	 */
	private DateUtils() {

	}

	public static LocalDate stringToDate(String date) {
		return LocalDate.parse(date, FORMATTER);
	}

}
