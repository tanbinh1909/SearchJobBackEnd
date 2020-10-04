package com.searchJob.utit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	public static final String SHORT_JANUARY = "Jan";
	public static final String SHORT_FEBRUARY = "Feb";
	public static final String SHORT_MARCH = "Mar";
	public static final String SHORT_APRIL = "Apr";
	public static final String SHORT_MAY = "May";
	public static final String SHORT_JUNE = "Jun";
	public static final String SHORT_JULY = "Jul";
	public static final String SHORT_AUGUST = "Aug";
	public static final String SHORT_SEPTEMBER = "Sep";
	public static final String SHORT_OCTOBER = "Oct";
	public static final String SHORT_NOVEMBER = "Nov";
	public static final String SHORT_DECEMBER = "Dec";

	public static final String PT_MM_DD_YYYY = "MM/dd/yyyy";
	public static final String PT_MMM_YY = "MMM-yy";
	public static final String PT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String PT_MM_DD_YYYY_HH_MM_SS = "MM-dd-yyyy HH:mm:ss";
	public static final String PT_DD_MMM_YYYY = "dd MMM yyyy";
	public static final String PT_DD_MM_YYYY = "dd/MM/yyyy";
	public static final String PT_DD_MM_YYYY_HH_MM_A = "dd/MM/yyyy hh:mm a";
	public static final String PT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String PT_DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	public static final String PT_YYYY_MM_DD_HH_MM_A = "yyyy-MM-dd hh:mm a";
	public static final String PT_YYYY_MM_DD_T_HH_MM_SS_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String PT_YYYY_MM_DD_T_HH_MM_SS_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static Date getFormatDateIso(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		if (date != null) {
			try {
				sdf.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
				return sdf.parse(date);
			} catch (ParseException e) {
				System.out.println("ParseException: " + date);
			}
		}
		return null;
	}

	public static String getFormatDateIso(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		if (date != null) {
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
			return sdf.format(date);
		} else {
			return "";
		}
	}

	public static Date getFormatDate(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
		if (date != null) {
			try {
				sdf.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
				return sdf.parse(date);
			} catch (ParseException e) {
				System.out.println("ParseException: " + date);
			}
		}
		return null;
	}
	
	public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
		try {
			if (date != null) {
				formatter.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
			    Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
			    String parsedDate = formatter.format(initDate);
			    return parsedDate;
			}
			
		} catch (ParseException e) {
			System.out.println("ParseException: " + date);
		}
	    return null;
	}
	
	public static Date formatStringToDate(String date, String initDateFormat, String endDateFormat) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
		try {
			if (date != null) {
				formatter.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
			    Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
			    
			    return formatter.parse(formatter.format(initDate));
			}
		} catch (ParseException e) {
			System.out.println("ParseException: " + date);
		}
	    return null;
	}

	public static Date getFormatDateVN(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//    	sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
		if (date != null) {
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				System.out.println("ParseException: " + date);
			}
		}
		return null;
	}

	public static String getFormatDateVN(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//    	sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
		if (date != null) {
			return sdf.format(date);
		} else {
			return "";
		}
	}

	public static String getFormatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
		if (date != null) {
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
			return sdf.format(date);
		} else {
			return "";
		}
	}

	public static String CalculatorAge(String date) {
		String[] arr = date.split("/");
		LocalDate dob = LocalDate.parse(arr[2] + "-" + arr[1] + "-" + arr[0]);
		return String.valueOf(getAge(dob));
	}

	public static int getAge(LocalDate dob) {
		LocalDate curDate = LocalDate.now();
		int age = curDate.getYear() - dob.getYear();
		return age;
	}
	
	public static String plusHour(int hour) {
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(new Date()); // sets calendar time/date
	    cal.add(Calendar.HOUR_OF_DAY, hour); // adds one hour
	    cal.getTime(); // retu
	    return getFormatDateVN(cal.getTime(), PT_DD_MM_YYYY_HH_MM_SS);
	}
	
}
