package com.skt.mydata.common.util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

public class DateUtils {
	public static final long ONE_DAY = 1000 * 60 * 60 * 24;
	public static final long ONE_TIME = 1000 * 60 * 60;

	@SuppressWarnings("unused")
	private long responseTime;

	/**
	 * 날짜 스트링을 Date 형으로 변환
	 * @param yymmdd 연-월-일 (2004-01-01)
	 * @return java.util.Date
	 */
	public static Date getDate(String yymmdd) {
		Calendar cal = setCalendar(yymmdd, "-");
		return cal.getTime();
	}

	/**
	 * 날짜 스트링을 Date 형으로 변환
	 * @param yymmdd 연-월-일 (2004-01-01)
	 * @param hmm 시-분 (12:15)
	 * @return	java.util.Date
	 */
	public static Date getDate(String yymmdd, String hmm) {
		Calendar cal = setDetailCalendar(yymmdd, hmm);
		return cal.getTime();
	}

	/**
	 * 날짜 스트링을 Date 형으로 변환
	 * @param yymmdd yymmdd 연-월-일 (2004-01-01)
	 * @param hmm 시-분 (12:15)
	 * @param second 초 (45)
	 * @return java.util.Date
	 */
	public static Date getDate(String yymmdd, String hmm, String second) {
		Calendar cal = setDetailCalendar(yymmdd, hmm);
		return cal.getTime();
	}

	/**
	 * 날짜 스트링을 Date 형으로 변환
	 * @param yymmdd yymmdd 연-월-일 (2004-01-01)
	 * @param hmm 시-분 (12:15)
	 * @param second 초 (45)
	 * @return java.util.Date
	 */
	public static Date getDateEx(String yymmdd, String hmm, String second) {
		Calendar cal = setDetailCalendar(yymmdd, hmm, second);
		return cal.getTime();
	}


	// change 1999-08-15 to Calendar
	public static Calendar setCalendar(String str, String delim) {
		StringTokenizer st = new StringTokenizer(str, delim);
		int year = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken());
		int day = Integer.parseInt(st.nextToken());

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month - 1, day);

		return cal;
	}

	public static Calendar setDetailCalendar(String yymmdd, String hmm) {
		StringTokenizer st = new StringTokenizer(yymmdd, "-");
		int year = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken());
		int day = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(hmm, ":");

		int hour = Integer.parseInt(st.nextToken());
		int minute = Integer.parseInt(st.nextToken());

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month - 1, day, hour, minute);
		//cal.set

		return cal;
	}

	public static Calendar setDetailCalendar(String yymmdd, String hmm, String sec) {
		StringTokenizer st = new StringTokenizer(yymmdd, "-");
		int year = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken());
		int day = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(hmm, ":");

		int hour = Integer.parseInt(st.nextToken());
		int minute = Integer.parseInt(st.nextToken());

		int second = Integer.parseInt(sec);

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month - 1, day, hour, minute, second);

		return cal;
	}


	/**
	   * "-" 구분자로 토크나이징 후 원하는 토근을 반환한다.
	   * @param     strSrc  문자열
	   * @param     nCount  인덱스
	   * @return    해당 토큰
	   */
	public static String getTokenDate(String strSrc, int nCount)
		throws Exception {
		String strRet = "";

		try {
			if (strSrc.trim().equals("")) {
				return "";
			}

			StringTokenizer tokenTemp = new StringTokenizer(strSrc, "-");

			for (int i = 1; tokenTemp.hasMoreTokens() && (i <= nCount); i++) {
				String strTemp = tokenTemp.nextToken();

				if (i == nCount) {
					strRet = strTemp;
				}
			}
		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	/**
	   * 현재 날짜, 시간을 반환한다.
	   * 형태(YYYY년 MM월 DD일 HH:mm)
	   * @return    조합된 현재 날짜 및 시간
	   */
	public static String getCurrentDate() throws Exception {
		String strRet = "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm",
					Locale.KOREA);
			String sdfs = sdf.format(new Date());

			//			logger.debug("getCurrentDate : " + sdfs);
			strRet = sdfs.substring(0, 4) + "년 " + sdfs.substring(4, 6) + "월 " +
				sdfs.substring(6, 8) + "일 " + sdfs.substring(8, 10) + "시 " +
				sdfs.substring(10, 12) + "분";
		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	public static String getCurrentDate2() throws Exception {
		String strRet = "";
		Calendar cal = Calendar.getInstance();

		String [] dateKor = {"일","월","화","수","목","금","토"};

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm",
					Locale.KOREA);
			String sdfs = sdf.format(new Date());

			//			logger.debug("getCurrentDate : " + sdfs);
			strRet = sdfs.substring(0, 4) + "년 " + sdfs.substring(4, 6) + "월 " +
				sdfs.substring(6, 8) + "일 " + " ("+dateKor[cal.get(Calendar.DAY_OF_WEEK)-1]+")";
		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	/**
	   * 현재 날짜, 시간을 반환한다.
	   * 형태(yyyyMMddHHmmss)
	   * @return    조합된 현재 날짜 및 시간
	   */
	public static String getCurrentFullDate() throws Exception {
		String strRet = "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			strRet = sdf.format(new Date());

		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	public static String getYesterdayFullDate() throws Exception {
		String strRet = "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			strRet = sdf.format(getAddDate(-1, getCurrentFullDate()));

		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	public static String getCurrentDateTitle() throws Exception {
		String strRet = "";
		Calendar cal = Calendar.getInstance();

		String [] dateKor = {"일","월","화","수","목","금","토"};

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm",
					Locale.KOREA);
			String sdfs = sdf.format(new Date());

			//			logger.debug("getCurrentDate : " + sdfs);
			strRet = sdfs.substring(0, 4) + "년 " + sdfs.substring(4, 6) + "월 " +
				sdfs.substring(6, 8) + "일 " + " ("+dateKor[cal.get(Calendar.DAY_OF_WEEK)]+")";
		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	/**
	   * 현재 시간을 yyyy-MM-dd-HH-mm 단위로 반환한다.
	   * @return     현재 시간
	   */
	public static String getCurrentTime() throws Exception {
		String strRet = "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm",
					Locale.KOREA);
			strRet = sdf.format(new Date());
		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	/**
	 * 현재 날짜를 얻는다.
	 * @return '2006-01-08'
	 */
	public static String getCurrentDate3() throws Exception {
		String strRet = "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);
			strRet = sdf.format(new Date());
		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	/**
	 * 현재 날짜를 얻는다.
	 * 	 * @return '08월 06일 13시 42분'
	 */
	public static String getCurrentDate4() throws Exception {
		String strRet = "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일 HH시 mm분",Locale.KOREA);
			strRet = sdf.format(new Date());
		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	/**
	 * String str에 yyyy-MM-dd형의 String을 넣어주면 Date형을 반환.
	 * from gwLibUtil.java
	 */
	public static Date changeToDate(String str) {
		String _str = str;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date date = dateformat.parse(_str, pos);

		return date;
	}

	/**
	 * Date형을 넣으면 yyyy-MM-dd형의 String 으로 반환.
	 * from gwLibUtil.java
	 */
	public static String changeToString(Date dt) {
		if (dt == null) {
			return " ";
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(dt);

			return dateString;
		}
	}

	/**
	 * Format String을 Date형으로 변환
	 * from gwLibUtil.java
	 */
	public static Date StringToDate(String str, String strFromat) {
		String _str = str;
		SimpleDateFormat dateformat = new SimpleDateFormat(strFromat);
		ParsePosition pos = new ParsePosition(0);
		Date date = dateformat.parse(_str, pos);

		return date;
	}

	/**
	 * Date형을 넣으면 Format String 으로 반환
	 * from gwLibUtil.java
	 */
	public static String DateToString(Date dt, String strFromat) {
		if (dt == null) {
			return " ";
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat(strFromat);
			String dateString = formatter.format(dt);

			return dateString;
		}
	}

	public static String getDateStr(long milli) {
		Date date = new Date(milli);

		return getDateStr(date);
	}

	// change Date to 1999-8-15.
	public static String getDateStr(Date date) {
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        return sdf.format(date);
	    }catch(Exception e) {
	        return "";
	    }
	}

	public static String getDateStr(Calendar cal) {
		return getDateStr(cal.getTime());
	}

	/**
	 * Calendar형을 "yyyy-mm-dd"형으로 바꾼다.
	 * @param cal
	 * @return "yyyy-mm-dd"
	 */
	public static String getDateStr_(Calendar cal) {
        StringBuffer sb = new StringBuffer();
		sb.append(cal.get(Calendar.YEAR)).append("-").
		append(String.valueOf(cal.get(Calendar.MONTH)+1)).
		append("-").append(String.valueOf(cal.get(Calendar.DATE)));

		return sb.toString();
	}

	// 입력값 '2005-6-7'에 대해서 월,일을 2자리로 만들어 준다. '2005-06-07'
	public static String make2digit(String str){
        StringBuffer sb = new StringBuffer();
		String [] arr = str.split("-");
		sb.append(arr[0]);
		sb.append("-");
		if(arr[1].length()== 1) sb.append("0");
		sb.append(arr[1]);
		sb.append("-");
		if(arr[2].length()== 1) sb.append("0");
		sb.append(arr[2]);
		return sb.toString();
	}

	public static String getTimeMinStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		return sdf.format(date);
	}

	public static String getTimeMinStr(Calendar cal) {
		return getTimeMinStr(getDate(cal));
	}

	public static String getTimeMinStr(long milli) {
		return getTimeMinStr(getCalendar(milli));
	}

	public static int getTimeStr(long milli) {
		return getTimeStr(getDate(milli));
	}

	public static int getTimeStr(Calendar cal) {
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	// hour : 0 to 24
	public static int getTimeStr(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return getTimeStr(cal);
	}

	public static Calendar getCalendar(long milli) {
		return getCalendar(getDate(milli));
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal;
	}

	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	public static Date getDate(Calendar cal) {
		return cal.getTime();
	}

	public static Date getDate(long milli) {
		return new Date(milli);
	}

	public static long getLong(Calendar cal) {
		return getLong(cal.getTime());
	}

	public static long getLong(Date date) {
		return date.getTime();
	}

	// change 1999-08-15 to milliseconds
	public static long getLong(String str) {
		return getLong(getDate(str));
	}

	public static String getFormattedDate(Date date) {
		String result;
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,
				Locale.KOREA);
		result = df.format(date);

		return result;
	}

	public static String getFormattedDateTime(Date date) {
		String result;
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL,
				DateFormat.LONG, Locale.KOREA);
		result = df.format(date);

		return result;
	}

	public static String getDateTime(java.util.Date date) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd H:mm:ss");

		return sdf.format(date);
	}

	public static String getSimpleDate(java.util.Date date) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");

		return sdf.format(date);
	}

	public static String getSimpleDateTime(java.util.Date date) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd H:mm");

		return sdf.format(date);
	}

	public static String getSimpleDateHour(java.util.Date date) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH");

		return sdf.format(date);
	}

	public static String getDateTimeNoSpace(java.util.Date date) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd/H:mm:ss");

		return sdf.format(date);
	}

	//return GMT datetime
	public static String getTimeZoneDate(Date date, String timezone) {
		TimeZone tz = TimeZone.getTimeZone(timezone);
		Calendar ca = Calendar.getInstance(tz);
		ca.setTime(date);

		String ret = ca.get(Calendar.YEAR) + "-" +
			getZero(ca.get(Calendar.MONTH) + 1) + "-" +
			getZero(ca.get(Calendar.DATE)) + " " +
			getZero(ca.get(Calendar.HOUR_OF_DAY)) + ":" +
			getZero(ca.get(Calendar.MINUTE)) + ":" +
			getZero(ca.get(Calendar.SECOND));

		return ret;
	}

	//date format yyyy-mm-dd HH:mm:ss
	public static String getGMTDate(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		return dateformat.format(date);
	}

	//return GMT datetime
	//date format yyyy-mm-dd/HH:mm:ss
	public static String getGMTDateNoSpace(Date date) {
		TimeZone tz = TimeZone.getTimeZone("GMT");
		Calendar ca = Calendar.getInstance(tz);
		ca.setTime(date);

		String ret = ca.get(Calendar.YEAR) + "-" +
			getZero(ca.get(Calendar.MONTH) + 1) + "-" +
			getZero(ca.get(Calendar.DATE)) + "/" +
			getZero(ca.get(Calendar.HOUR_OF_DAY)) + ":" +
			getZero(ca.get(Calendar.MINUTE)) + ":" +
			getZero(ca.get(Calendar.SECOND));

		return ret;
	}

	//for use getGMTDate()
	public static String getZero(int hour) {
		String ret;
		ret = Integer.toString(hour);

		if (hour < 10) {
			ret = "0" + hour;
		}

		return ret;
	}

	public static String getDateTimeStr(Calendar cal) {
		return getDateStr(cal) + " " + getTimeStr(cal);
	}

	public static String getDateTimeMinStr(Calendar cal) {
		return getDateStr(cal) + " " + getTimeMinStr(cal);
	}

	// change 1999-8-15 to Aug/15/1999.
	public static String strMediumToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.US);

		return sdf.format(getDate(str));
	}

	// change 15/Aug/1999 to 1999-08-15
	public static String getStrDateTime(String str) {
		StringTokenizer st = new StringTokenizer(str, "/");
		String day = st.nextToken();
		String month = monthStr2Int(st.nextToken());
		String year = st.nextToken();

		return year + "-" + month + "-" + day;
	}
	// change 15/Aug/1999:19:39:32 to calendar type.
	public static Calendar strFullToCalendar(String str) {
		StringTokenizer st = new StringTokenizer(str, ":");
		String date = st.nextToken();
		StringTokenizer st1 = new StringTokenizer(date, "/");
		int day = Integer.parseInt(st1.nextToken());
		int month = Integer.parseInt(monthStr2Int(st1.nextToken()));
		int year = Integer.parseInt(st1.nextToken());
		int hour = Integer.parseInt(st.nextToken());
		int minute = Integer.parseInt(st.nextToken());
		int milli = Integer.parseInt(st.nextToken());

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month - 1, day, hour, minute, milli);

		return cal;
	}

	// change 19/Aug/1999:19:39:32 to long type.
	public static long StrFullToLong(String str) {
		Calendar cal = strFullToCalendar(str);

		return getLong(cal);
	}

	public static boolean isValid(String logTime, long start, long end) {
		long date = StrFullToLong(logTime);

		if ((date > start) && (date < end)) {
			return true;
		}

		return false;
	}

	/* change 10/Aug/1999 to calendar type */
	public static Calendar strDateToCalendar(String str) {
		StringTokenizer st = new StringTokenizer(str, ":");
		String date = st.nextToken();
		StringTokenizer st1 = new StringTokenizer(date, "/");
		int day = Integer.parseInt(st1.nextToken());
		int month = Integer.parseInt(monthStr2Int(st1.nextToken()));
		int year = Integer.parseInt(st1.nextToken());

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month - 1, day);

		return cal;
	}

	/* change 10/Aug/1999 to long type */
	public static long strDateToLong(String str) {
		Calendar cal = strDateToCalendar(str);

		return getLong(cal);
	}

	/*
	 * change month string to integer form
	 */
	public static String monthStr2Int(String matchMonth) {
		String[] defaultMonths = {
			"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
			"Oct", "Nov", "Dec"
		};

		for (int i = 1; i < defaultMonths.length; i++) {
			if (matchMonth.equals(defaultMonths[i])) {
				if (i < 10) {
					return new String(new StringBuffer().append("0").append(i));
				} else {
					return new String(new StringBuffer().append(i));
				}
			}
		}

		return null;
	}

	/*
	 * formatting date
	 */
	public static String dateFormat(String year, String month, String day) {
		StringBuffer format;
		format = new StringBuffer().append(year).append("-").append(month)
								   .append("-").append(day);

		return new String(format);
	}

	/*
	 * return starttime of modifiedTime
	 *
	 * @param modifiedTime   String
	 *
	 * ex. modifiedTime = Aug/10/1999:10:10:10
	 *     starttime of modifiedTime = Aug/10/1999:10:10:10
	 *     interval of start and end time = 59 sec.
	 */
	public static long getStartTime(String modifiedTime) {
		return StrFullToLong(modifiedTime);
	}

	/*
	 * return endtime of modifiedTime
	 *
	 * @param modifiedTime String
	 *
	 * ex. modifiedTime = Aug/10/1999:10:10:10
	 *     endtime of modifiedTime = Aug/10/1999:11:09:10
	 */
	public static long getEndTime(String modifiedTime) {
		return (StrFullToLong(modifiedTime) + ONE_TIME) - 1; //3599999
	}

	public static long getEndTime(long modifiedTime) {
		return (modifiedTime + ONE_TIME) - 1;
	}

	/*
	 * return FullCalendar form
	 *
	 * ex. Aug/10/1999:10:10:10
	 */
	public static String getFullCalendar(long modifiedTime) {
		String Date = strMediumToDate(getDateStr(modifiedTime));
		String Time = getTimeMinStr(modifiedTime);

		return (Date + ":" + Time);
	}

	// change yyyy-mm-dd HH:mm:ss to calendar type. //author: Woo Seok Jeong
	public static Calendar dateTimeToCalendar(String str) {
		StringTokenizer st = new StringTokenizer(str, " ");
		String date = st.nextToken();
		String time = st.nextToken();
		StringTokenizer st1 = new StringTokenizer(date, "-");
		int year = Integer.parseInt(st1.nextToken());
		int month = Integer.parseInt(st1.nextToken());
		int day = Integer.parseInt(st1.nextToken());
		StringTokenizer st2 = new StringTokenizer(time, ":");
		int hour = Integer.parseInt(st2.nextToken());
		int minute = Integer.parseInt(st2.nextToken());
		int milli = (int) Double.parseDouble(st2.nextToken());

		TimeZone tz = TimeZone.getTimeZone("GMT");
		Calendar cal = Calendar.getInstance(tz);
		cal.clear();
		cal.set(year, month - 1, day, hour, minute, milli);

		return cal;
	}

	// calculate duration days between two calendar. //author: Woo Seok Jeong
	public static int durationDays(Calendar src, Calendar dest) {
		int duration = 0;
		src.set(Calendar.HOUR, 0);
		src.set(Calendar.MINUTE, 0);
		src.set(Calendar.SECOND, 0);
		src.set(Calendar.MILLISECOND, 0);
		dest.set(Calendar.HOUR, 0);
		dest.set(Calendar.MINUTE, 0);
		dest.set(Calendar.SECOND, 0);
		dest.set(Calendar.MILLISECOND, 0);

		long srcm = getLong(src);
		long destm = getLong(dest);
		long mili_per_day = 1000 * 60 * 60 * 24;

		if (srcm < destm) {
			duration = (int) ((destm - srcm) / mili_per_day);
		}

		return duration;
	}

	public static int getMaxMonday(int year, int month) {
		int nMax = 30;

		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			nMax = 31;

			break;

		case 2:
			nMax = 28;

			break;

		case 4:
		case 6:
		case 9:
		case 11:
			nMax = 30;

			break;
		}

		if (((year % 4) == 0) && (month == 2)) {
			if ((year % 100) == 0) {
				if ((year % 400) == 0) {
					nMax = 28;
				}
			} else {
				nMax = 29;
			}
		}

		return nMax;
	}


	/**
	 * 입력한 날짜에 대하여 지나간 최근 일요일 날짜를 구한다.
	 * @param ymd 특정 날짜
	 * @param delim 구분자
	 * @return 지나간 최근 일요일 날짜
	 */
	public static Calendar getFisrtDayOfWeek(String ymd, String delim)
	{
		Calendar now;
		if( ymd == null)
			now = Calendar.getInstance();
		else
			now = DateUtils.setCalendar(ymd,delim);// change 1999-08-15 to Calendar

		now.add(Calendar.DATE,(now.get(Calendar.DAY_OF_WEEK)-1)* -1 );
		return now;
	}

	public static int getDayOfWeekFromYMD(String ymd) {
		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nYear = Integer.valueOf(ymd.substring(0, 4)).intValue(); // DB 상의 시작일 일

		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nMonth = Integer.valueOf(ymd.substring(4, 6)).intValue(); // DB 상의 시작일 일

		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nDay = Integer.valueOf(ymd.substring(6, 8)).intValue(); // DB 상의 시작일 일

		// 시작일의 그레고리력
		Calendar calendar = new GregorianCalendar(nYear, nMonth - 1, nDay);

		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static String addDate(int days, String dateStr) {
		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nStartYear = Integer.valueOf(dateStr.substring(0, 4)).intValue(); // DB 상의 시작일 일

		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nStartMonth = Integer.valueOf(dateStr.substring(5, 7)).intValue(); // DB 상의 시작일 일

		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nStartDay = Integer.valueOf(dateStr.substring(8, 10)).intValue(); // DB 상의 시작일 일

		// 시작일의 그레고리력
		Calendar calendar = new GregorianCalendar(nStartYear, nStartMonth - 1,
				nStartDay);

		// 그레고리력을 이용해서
		calendar.add(Calendar.DATE, days);

		String addedDate = Integer.toString(calendar.get(Calendar.YEAR)) + "-" +
			get00(calendar.get(Calendar.MONTH) + 1) + "-" +
			get00(calendar.get(Calendar.DAY_OF_MONTH));

		return addedDate;
	}
	/**
	 * 현재시간 기준 일주일 후의 날짜를 YYYYMMDD 포멧으로 리턴
	 * @return
	 */
	public static String oneWeekLater() {
		Calendar calendar = Calendar.getInstance(Locale.KOREA);
		calendar.add(Calendar.DATE, 7);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(calendar.getTime());
	}


	public static Date getAddDate(int days, String dateStr) {
		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nStartYear = Integer.valueOf(dateStr.substring(0, 4)).intValue(); // DB 상의 시작일 일
		int nStartMonth = Integer.valueOf(dateStr.substring(4, 6)).intValue(); // DB 상의 시작일 일
		int nStartDay = Integer.valueOf(dateStr.substring(6, 8)).intValue(); // DB 상의 시작일 일

		// 시작일의 그레고리력
		Calendar calendar = new GregorianCalendar(nStartYear, nStartMonth - 1,	nStartDay);

		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	public static String addYear(int years, String dateStr) {
		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nYear = Integer.valueOf(dateStr.substring(0, 4)).intValue(); // DB 상의 시작일 일

		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nMonth = Integer.valueOf(dateStr.substring(4, 6)).intValue(); // DB 상의 시작일 일

		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nDay = Integer.valueOf(dateStr.substring(6, 8)).intValue(); // DB 상의 시작일 일

		// 시작일의 그레고리력
		Calendar calendar = new GregorianCalendar(nYear, nMonth - 1, nDay);

		// 그레고리력을 이용해서
		calendar.add(Calendar.YEAR, years);
		String strMonth = Integer.toString(calendar.get(Calendar.MONTH)+1);
		if(strMonth.length()<2) strMonth = "0"+strMonth;

		String addedDate = Integer.toString(calendar.get(Calendar.YEAR)) +
							strMonth +
                            get00(calendar.get(Calendar.DAY_OF_MONTH));

		return addedDate;
	}
	public static String addMonth(int months, String dateStr) {
		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nStartYear = Integer.valueOf(dateStr.substring(0, 4)).intValue(); // DB 상의 시작일 일

		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nStartMonth = Integer.valueOf(dateStr.substring(4, 6)).intValue(); // DB 상의 시작일 일

		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nStartDay = Integer.valueOf(dateStr.substring(6, 8)).intValue(); // DB 상의 시작일 일

		// 시작일의 그레고리력
		Calendar calendar = new GregorianCalendar(nStartYear, nStartMonth - 1,
				nStartDay);

		// 그레고리력을 이용해서
		calendar.add(Calendar.MONTH, months);

		String addedDate = Integer.toString(calendar.get(Calendar.YEAR)) +
			get00(calendar.get(Calendar.MONTH) + 1) +
			get00(calendar.get(Calendar.DAY_OF_MONTH));

		return addedDate;
	}

	public static String addMonthDate(int months, String dateStr) {
		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nStartYear = Integer.valueOf(dateStr.substring(0, 4)).intValue(); // DB 상의 시작일 일

		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nStartMonth = Integer.valueOf(dateStr.substring(5, 7)).intValue(); // DB 상의 시작일 일

		// dateStr 은 "YYYYMMDD"식으로 전달되는 것을 가정함.
		int nStartDay = Integer.valueOf(dateStr.substring(8, 10)).intValue(); // DB 상의 시작일 일

		// 시작일의 그레고리력
		Calendar calendar = new GregorianCalendar(nStartYear, nStartMonth - 1,
				nStartDay);

		// 그레고리력을 이용해서
		calendar.add(Calendar.MONTH, months);

		String addedDate = Integer.toString(calendar.get(Calendar.YEAR)) + "-" +
			get00(calendar.get(Calendar.MONTH) + 1) + "-" +
			get00(calendar.get(Calendar.DAY_OF_MONTH));

		return addedDate;
	}

	public static int dateDiff(String startYMD, String endYMD) {
		int nStartYear = Integer.valueOf(startYMD.substring(0, 4)).intValue();
		int nStartMonth = Integer.valueOf(startYMD.substring(4, 6)).intValue();
		int nStartDay = Integer.valueOf(startYMD.substring(6, 8)).intValue();

		// 시작일의 그레고리력
		Calendar start = new GregorianCalendar(nStartYear, nStartMonth - 1,
				nStartDay);

		int nEndYear = Integer.valueOf(endYMD.substring(0, 4)).intValue();
		int nEndMonth = Integer.valueOf(endYMD.substring(4, 6)).intValue();
		int nEndDay = Integer.valueOf(endYMD.substring(6, 8)).intValue();

		// 시작일의 그레고리력
		Calendar end = new GregorianCalendar(nEndYear, nEndMonth - 1, nEndDay);

		return getDaysBetween(start, end);
	}

	public static int minuteDiff(String startDateTime, String endDateTime) {
		int minuteDiff = 0;
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date startDT = transFormat.parse(startDateTime);
			Date endDT = transFormat.parse(endDateTime);

			long millisec = endDT.getTime() - startDT.getTime();
			minuteDiff = (int)(millisec / (60 * 1000));

		} catch(Exception e) {
			minuteDiff = 0;
		}

		return minuteDiff;
	}

	/**
	 * Calculates the number of days between two calendar days in a manner
	 * which is independent of the Calendar type used.
	 *
	 * @param d1    The first date.
	 * @param d2    The second date.
	 *
	 * @return      The number of days between the two dates.  Zero is
	 *              returned if the dates are the same, one if the dates are
	 *              adjacent, etc.  The order of the dates
	 *              does not matter, the value returned is always >= 0.
	 *              If Calendar types of d1 and d2
	 *              are different, the result may not be accurate.
	 */
	public static int getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
			// swap dates so that d1 is start and d2 is end
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}

		int days = d2.get(java.util.Calendar.DAY_OF_YEAR) -
			d1.get(java.util.Calendar.DAY_OF_YEAR);

		int y2 = d2.get(java.util.Calendar.YEAR);

		if (d1.get(java.util.Calendar.YEAR) != y2) {
			d1 = (java.util.Calendar) d1.clone();

			do {
				days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				d1.add(java.util.Calendar.YEAR, 1);
			} while (d1.get(java.util.Calendar.YEAR) != y2);
		}

		return days;
	}
	 // getDaysBetween()

	public static Calendar getCalendarFromYMD(String ymd) {
		Calendar cal;

		int nYear = Integer.valueOf(ymd.substring(0, 4)).intValue();
		int nMonth = Integer.valueOf(ymd.substring(4, 6)).intValue();
		int nDay = Integer.valueOf(ymd.substring(6, 8)).intValue();

		// 시작일의 그레고리력
		cal = new GregorianCalendar();
		cal.set(nYear, nMonth - 1, nDay);

		return cal;
	}

	public static String get00(int n) {
		if ((n >= 0) && (n < 10)) {
			return "0" + Integer.toString(n);
		} else if (n >= 10) {
			return Integer.toString(n);
		} else {
			return "00";
		}
	}

	public static int getWeekDay(int java) {
		int msStyle = 1;

		if (java == Calendar.MONDAY) {
			msStyle = 1;
		} else if (java == Calendar.TUESDAY) {
			msStyle = 2;
		} else if (java == Calendar.WEDNESDAY) {
			msStyle = 3;
		} else if (java == Calendar.THURSDAY) {
			msStyle = 4;
		} else if (java == Calendar.FRIDAY) {
			msStyle = 5;
		} else if (java == Calendar.SATURDAY) {
			msStyle = 6;
		} else if (java == Calendar.SUNDAY) {
			msStyle = 7;
		}

		return msStyle;
	}

	public static boolean getBoolean(int n) {
		if(n == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   * 현재 날짜 기준으로 Path를 반환한다.
	   * 형태(YYYY/MM/DD)
	   * @return    조합된 현재 날짜 Path
	   */
	public static String getDatePath() {
		Calendar calendar = Calendar.getInstance(Locale.KOREA);

		// 2004/3/28
		return calendar.get(Calendar.YEAR) + File.separator +
		(calendar.get(Calendar.MONTH) + 1) + File.separator +
		calendar.get(Calendar.DAY_OF_MONTH) + File.separator + calendar.get(Calendar.DATE);
	}

	public static String getYMDatePath() {
		Calendar calendar = Calendar.getInstance(Locale.KOREA);

		// 2004/3
		return calendar.get(Calendar.YEAR) + File.separator +
		(calendar.get(Calendar.MONTH) + 1) + File.separator +
		calendar.get(Calendar.DAY_OF_MONTH);
	}
    /**
     *
     * @param fmtDt yyyyMMddHHmmss
     * @param format
     * @return 입력받은 시간을 형태에 맞게 변환
     */
	public static String getStrDateFormat(String dt, String format) {
        if( dt == null || dt.trim().equals("")) return "";
        String fmtDt = dt;
        String hour = "";
        String min = "";
        String sec = "";

        if( dt.length() >= 8 ) {
            fmtDt = fmtDt.substring(0,4).concat("-").concat(fmtDt.substring(4,6)).concat("-").concat(fmtDt.substring(6,8));
        }
        if( dt.length() >= 10 ) {
            hour = dt.substring(8,10);
        }else{
            hour = "00";
        }
        if( dt.length() >= 12 ) {
            min = dt.substring(10,12);
        }else {
            min = "00";
        }
        if( dt.length() >= 14 ) {
            sec = dt.substring(12,14);
        }else{
            sec = "00";
        }

        return DateToString( getDateEx(fmtDt, hour + ":" + min, sec ) , format );
    }

	/**
     * 날짜 String 변환
     * @param Cal
     * @return 날짜 String
     * @throws BusinessException
     */
    public static String getCalendarDate(Calendar Cal) {

    	String scheduleYear = String.valueOf(Cal.get(Calendar.YEAR));
    	String scheduleMonth =  String.valueOf(Cal.get(Calendar.MONTH)+1);
    	String scheduleDate =  String.valueOf(Cal.get(Calendar.DATE));
    	String scheduleDay = "";

    	if(scheduleMonth.length()== 1){

    		scheduleMonth = "0"+ scheduleMonth;
		}

		if(scheduleDate.length()== 1){

			scheduleDate = "0"+ scheduleDate;
		}

		scheduleDay = scheduleYear + scheduleMonth + scheduleDate ;

    	return scheduleDay;
    }

    /**
     * yyyyMM을 입력하면 해당월의 마지막날 리턴
     * @param strDate
     * @return 해당 월의 마지막 날
     */
    public static int getLastDate(String strDate) {
    	Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, Integer.parseInt(strDate.substring(0,4)));
        cal.set(Calendar.MONTH, Integer.parseInt(strDate.substring(4,strDate.length())) - 1);

        int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        return lastDate;
    }

    @SuppressWarnings("unused")
	public static boolean checkDate(String str) {
		boolean dateValidity = true;

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		df.setLenient(false);

		try {
			Date dt = df.parse(str);
		} catch(ParseException pe){
			dateValidity = false;
		} catch(IllegalArgumentException ae){
			dateValidity = false;
		}

		return dateValidity;
	}

    /**
     * pattern에 맞는 해당 월의 첫째날로 리턴 ex) 2015-07-01, 20150701 등...
     * @param pattern
     * @return 해당월의 첫째날
     * @throws Exception
     */
    public static String getCurrentDateFirst(String pattern) throws Exception {
    	SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREA);
        Calendar cal1 = Calendar.getInstance();

        cal1.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDay = cal1.getTime();
        String date = formatter.format(firstDay);

        return date;
	}

    /**
     * pattern에 맞는 현재일로 부터 1주일 전일자 가져오기
     * @param pattern
     * @return 1주일 전 날짜
     */
    @SuppressWarnings("static-access")
	public static String getFormatLastWeekDate(String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.add( Calendar.DATE, -6 );
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(cal.getTime());
    }

    /**
     * pattern에 맞는 지난 1개월
     * @param pattern
     * @return
     * @throws Exception
     */
	public static String getLastMonth(String pattern) {
    	Calendar cal = Calendar.getInstance();
        cal.add( Calendar.MONTH, -1 );
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(cal.getTime());
	}

    /**
     * pattern에 맞는 지난 1개월 마지막일자 가져오기
     * @param pattern
     * @return 지난 달 마지막일자
     */
    public static String getLastMonthLastDate(String pattern) {
    	Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(cal.getTime());
    }

    /**
     * pattern에 맞는 지난 2개월
     * @param pattern
     * @return 지난 2개월 날짜
     * @throws Exception
     */
    public static String getLast2Month(String pattern) {
    	Calendar cal = Calendar.getInstance();
        cal.add( Calendar.MONTH, -2 );
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(cal.getTime());
	}

    /**
     * pattern에 맞는 지난 5개월
     * @param pattern
     * @return 지난 5개월 날짜
     * @throws Exception
     */
    public static String getLast5Month(String pattern) {
    	Calendar cal = Calendar.getInstance();
        cal.add( Calendar.MONTH, -5 );
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(cal.getTime());
	}

    /**
     * pattern에 맞는 지난 월
     * @param pattern
     * @return 받은 값 만큼 뺀 달
     * @throws Exception
     */
    public static String getLastYearMonth(String pattern, int num) {
    	Calendar cal = Calendar.getInstance();
        cal.add( Calendar.MONTH, -num );
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(cal.getTime());
	}

	/**
	 * 기본 포멧을 이용하여 날짜를 돌려준다.
	 * yyyyMMdd HHddss
	 * @Mehtod Name : formatYmdhms
	 * @return
	 */
	public static String formatYmdhms(String s) {
		return DateUtils.format(s, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @Mehtod Name : format
	 * @param format
	 * @return
	 */
	public static String format(long time, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(time);
	}

	/**
	 * @Mehtod Name : format
	 * @param cal
	 * @param format
	 * @return
	 */
	public static String format(Calendar cal, String format) {

		if (cal == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	/**
	 * @Mehtod Name : format
	 * @param s
	 * @param format
	 * @return
	 */
	public static String format(String s, String format) {

		if (s == null) {
			return null;
		}

		Calendar c = toCalendar(s);
		return format(c, format);
	}

	/**
	 * @Mehtod Name : toCalendar
	 * @param date
	 * @return
	 */
	public static Calendar toCalendar(String date) {

		Calendar cal = Calendar.getInstance();

		if (date.length() == 8) {
			cal.setTime(parse(date, "yyyyMMdd"));
		} else if (date.length() == 7) {
			cal.setTime(parse(date, "yyyy-MM"));
		} else if (date.length() == 10) {
			cal.setTime(parse(date, "yyyy-MM-dd"));
		} else if (date.length() == 12) {
			cal.setTime(parse(date, "yyyyMMddHHmm"));
		} else if (date.length() == 14) {
			cal.setTime(parse(date, "yyyyMMddHHmmss"));
		}

		return cal;
	}

	/**
	 * @Mehtod Name : parse
	 * @param date
	 * @return
	 */
	public static Date parse(String date) {

		date = date.replaceAll("\\W", "");

		if (date.length() == 8) {
			return DateUtils.parse(date, "yyyyMMdd");
		} else if (date.length() == 12) {
			return DateUtils.parse(date, "yyyyMMddHHmm");
		} else if (date.length() == 14) {
			return DateUtils.parse(date, "yyyyMMddHHmmss");
		}
		return null;
	}

	/**
	 * @Mehtod Name : parse
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date parse(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 현재 날짜에 입력한 월을 더해 String yyyy-MM 형태로 반환한다.
	 * @return
	 * @throws Exception
	 */
	public static String currentDateAddMonth(int month) throws Exception {
		String strRet = "";

		try {
			DateFormat sdf = new SimpleDateFormat("yyyy-MM"); // 데이터 포맷 설정
		    Date date = new Date();

		   // 날짜 더하기
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(date);
		   cal.add(Calendar.MONTH, month);

			strRet = sdf.format(cal.getTime());
		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	/**
	 * 현재 날짜에 입력한 월을 더해 String yyMM 형태로 반환한다.
	 * @return
	 * @throws Exception
	 */
	public static String currentDateAddMonth2(int month) throws Exception {
		String strRet = "";

		try {
			DateFormat sdf = new SimpleDateFormat("yyMM"); // 데이터 포맷 설정
		    Date date = new Date();

		   // 날짜 더하기
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(date);
		   cal.add(Calendar.MONTH, month);

			strRet = sdf.format(cal.getTime());
		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	/**
	 * 현재 날짜에 입력한 월을 더해 String yyyyMM 형태로 반환한다.
	 * @return
	 * @throws Exception
	 */
	public static String currentDateAddMonth3(int month) throws Exception {
		String strRet = "";

		try {
			DateFormat sdf = new SimpleDateFormat("yyyyMM"); // 데이터 포맷 설정
		    Date date = new Date();

		   // 날짜 더하기
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(date);
		   cal.add(Calendar.MONTH, month);

			strRet = sdf.format(cal.getTime());
		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	public static String getTodayDate() throws Exception {
		String strRet = "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			strRet = sdf.format(getAddDate(0, getCurrentFullDate()));

		} catch (Exception e) {
			//			logger.error( e.getMessage() );
		}

		return strRet;
	}

	/**
	 * 입력한 날짜에서 선택한 시간을 뺀다.
	 * @param date
	 * @param hour
	 * @return date
	 */
	public static Date minusHour(Date date, int hour) {

		Calendar	cal	= Calendar.getInstance();

		cal.setTime(date);
		cal.add(Calendar.HOUR, hour);

		return cal.getTime();
	}


	/**
	 * 소요 시간 계산
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getIntervalTime(Date startTime, Date endTime) {

		long	interval	= (endTime.getTime() - startTime.getTime());

		long	hour		= interval / 1000 / 60 / 60;
		long	min			= interval / 1000 / 60 % 60;
		long	sec			= interval / 1000 % 60;
		long	miliSec		= interval % 1000;

		return String.format("%d : %02d : %02d : %03d", hour, min, sec, miliSec);
	}

	/**
	 * 현재 월 출력
	 * @return (String)yyyymm
	 */
	public static String getNowMonth() {
		LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		return now.format(formatter);
	}

	/**
	 * 년월일시분초밀리초 출력
	 * @return (String)yyyymmddhhmmssms
	 */
	public static String getNowTimeMillis () {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String str = sdf.format(new Date());
		return str;
	}
}

