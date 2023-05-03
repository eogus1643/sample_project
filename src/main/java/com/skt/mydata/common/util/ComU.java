package com.skt.mydata.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;



public class ComU {

    /**
     * CommonUtil 기본 생성자
     */
    public ComU() {
        super();
    }

    /**
     * <p>문자열이 비어있거나("") null인 경우를 검사합니다</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str  검사할 문자열
     * @return 문자열이 비어있거나 null인 경우<code>true</code>를 반환
     */
    public static boolean isEmpty(String str) {
         return StringUtils.isEmpty(str);
    }
    /**
     * <p>문자열이 비어있지 않거나 null이 아닌경우를 검사합니다</p>
     *
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param str  검사할 문자열
     * @return 문자열이 비어있지 않거나 null이 아닌 경우<code>true</code>를 반환
     */
    public static boolean isNotEmpty(String str) {
         return StringUtils.isNotEmpty(str);
    }

    /**
     * <p>문자열이 공백문자 , 빈 문자열 또는 null인지 검사합니다</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str  검사할 문자열
     * @return 문자열이 공백문자 ,빈 문자열 또는 null이면<code>true</code> 를 반환합니다
     */
    public static boolean isBlank(String str) {
         return StringUtils.isBlank(str);
    }

    /**
     * <p>문자열이 공백문자 , 빈 문자열 또는 null가 아닌지 검사합니다</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str  검사할 문자열
     * @return 문자열이 공백문자 ,빈 문자열 또는 null이 아니면 <code>true</code> 를 반환합니다
     */
    public static boolean isNotBlank(String str) {
         return StringUtils.isNotBlank(str);
    }


    public static boolean isNotNull(String str) {
        boolean isExit;

        if(str != null) {
        	isExit = true;
        }else {
        	isExit = false;
        }

    	return isExit;
   }

    public static Object isNotNullInit(Object obj) {
    	Object rtnVal = null;
    	boolean isNumChk = true;

       	//숫자인지 확인.
    	try {
    		Double.parseDouble((String) obj);
    	} catch (NumberFormatException e) {
    		isNumChk = false;
    	}



    	return rtnVal;
    }


    /****************************************************************************************************************************
    *** Trim 관련
    ****************************************************************************************************************************/

    /**
     * <p>문자열 앞 뒤에 불필요한 null이나 빈 문자열이 있다면 삭제합니다.</p>
     *
     * <pre>
     * StringUtils.trim(null)          = null
     * StringUtils.trim("")            = ""
     * StringUtils.trim("     ")       = ""
     * StringUtils.trim("abc")         = "abc"
     * StringUtils.trim("    abc    ") = "abc"
     * </pre>
     *
     * @param str  공백을 삭제할 문자열
     * @return 공백을 제거한 문자열, 만약 null을 입력했으면 <code>null</code> 반환합니다
     */
    public static String trim(String str) {
         return StringUtils.trim(str);
    }

    /**
     *<p>문자열 앞 뒤에 불필요한 null이나 빈 문자열이 있다면 삭제합니다.</p>
     *
     * <pre>
     * StringUtils.trimToNull(null)          = null
     * StringUtils.trimToNull("")            = null
     * StringUtils.trimToNull("     ")       = null
     * StringUtils.trimToNull("abc")         = "abc"
     * StringUtils.trimToNull("    abc    ") = "abc"
     * </pre>
     *
     * @param str  공백을 삭제할 문자열
     * @return the 공백을 제거한 문자열,만약 빈문자열이나 null 또는 공백 문자을 입력한 경우 <code>null</code>을 반환합니다
     */
    public static String trimToNull(String str) {
         return StringUtils.trimToNull(str);
    }

    /**
     * <p>문자열 앞 뒤에 불필요한 null이나 빈 문자열이 있다면 삭제합니다.</p>
     *
     * <pre>
     * StringUtils.trimToEmpty(null)          = ""
     * StringUtils.trimToEmpty("")            = ""
     * StringUtils.trimToEmpty("     ")       = ""
     * StringUtils.trimToEmpty("abc")         = "abc"
     * StringUtils.trimToEmpty("    abc    ") = "abc"
     * </pre>
     *
     * @param str  공백을 삭제할 문자열
     * @return the 공백을 제거한 문자열g, 만약 <code>null</code> 을 입력하면 빈 문자열을 반환합니다.
     */
    public static String trimToEmpty(String str) {
         return StringUtils.trimToEmpty(str);
    }



    /****************************************************************************************************************************
    *** Equals 관련
    ****************************************************************************************************************************/


    /**
     * <p>두 문자열을 비교해서 문자열이 같다면 <code>true</code> 를 반환합니다</p>
     *
     * <p>예외없이 <code>null</code>을 처리합니다 두개의 <code>null</code> 레퍼런스는 같은 것으로 간주됩니다</p>
     *
     * <pre>
     * StringUtils.equals(null, null)   = true
     * StringUtils.equals(null, "abc")  = false
     * StringUtils.equals("abc", null)  = false
     * StringUtils.equals("abc", "abc") = true
     * StringUtils.equals("abc", "ABC") = false
     * </pre>
     *
     * @see java.lang.String#equals(Object)
     * @param str1  비교할 첫번째 문자열
     * @param str2  비교할 두번째 문자열
     * @return 두개의 문자열이 같거나 두 문자열이 둘다 null일 경우 true을 반환합니다.
     */
    public static boolean equals(String str1, String str2) {
         return StringUtils.equals(str1, str2);
    }

    /**
     * <p>두 문자열을 대소문자 구분없이 비교해서 문자열이 같다면 <code>true</code> 를 반환합니다</p>
     *
     * <p>예외없이 <code>null</code>을 처리합니다 두개의 <code>null</code> 레퍼런스는 같은 것으로 간주됩니다</p>
     *
     * <pre>
     * StringUtils.equalsIgnoreCase(null, null)   = true
     * StringUtils.equalsIgnoreCase(null, "abc")  = false
     * StringUtils.equalsIgnoreCase("abc", null)  = false
     * StringUtils.equalsIgnoreCase("abc", "abc") = true
     * StringUtils.equalsIgnoreCase("abc", "ABC") = true
     * </pre>
     *
     * @see java.lang.String#equalsIgnoreCase(String)
     * @param str1  비교할 첫번째 문자열
     * @param str2  비교할 두번째 문자열
     * @return 두개의 문자열이 같거나 두 문자열이 둘다 null일 경우 true을 반환합니다.
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
         return StringUtils.equalsIgnoreCase(str1, str2);
    }

    /****************************************************************************************************************************
    *** Date/Time 관련
    ****************************************************************************************************************************/

    /**
     * <p>dateType 별로 날짜/시간을 반환합니다.</p>
     *
     * <pre>
     * dateType : A   = yyyy/MM/dd hh:mm:ss
     * dateType : D   = yyyy/MM/dd
     * dateType : H   = hh:mm:ss
     * dateType : HN   = hhmmss
     * </pre>
     *
     * @param dateType  비교할 첫번째 문자열
     * @return dateType 별로 날짜/시간을 반환합니다.
     */
    public static String getDateTime(String dateType) {
    	String nowDate = null;
    	Date date = new Date();

    	if(ComU.equals("A", dateType)) {
    		//여기서 MM은 대문자로 써줘야지 month(월)이 제대로 표시된다.
    		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    		nowDate = dayTime.format(date);

    	} else if(ComU.equals("D", dateType)){
    		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy/MM/dd");
    		nowDate = dayTime.format(date);

    	} else if(ComU.equals("H", dateType)){
    		SimpleDateFormat dayTime = new SimpleDateFormat("hh:mm:ss");
    		nowDate = dayTime.format(date);
    	} else if(ComU.equals("HN", dateType)){
    		SimpleDateFormat dayTime = new SimpleDateFormat("hhmmss");
    		nowDate = dayTime.format(date);
    	} else if(ComU.equals("DN", dateType)){
    		SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMdd");
    		nowDate = dayTime.format(date);
    	}

    	return nowDate;
	}

    /**
     * <p>dateType 별로 오늘 또는 이전 날짜를 반환합니다.</p>
     *
     * <pre>
     * dateType : N, dateCount: "" = 오늘 기준 날짜.
     * dateType : D, dateCount: "1" = 날짜 단위 기준으로 이전날짜. (하루전 날짜)
     * dateType : W, dateCount: "1" = 주   단위 기준으로 이전날짜. (일주일전 날짜)
     * dateType : M, dateCount: "1" = 월   단위 기준으로 이전날짜. (한달전 날짜)
     * dateType : Y, dateCount: "1" = 년   단위 기준으로 이전날짜. (일년전 날짜)
     * </pre>
     *
     * @param dateType   오늘, 일 ,주, 월, 년 단위 선택.
     * @param dateCount  구하고자 하는 수.
     * @return 단위에 따라 이전 일자를 구해서 리턴한다.
     */
    public static String GetCalendarDate (String dateType, String dateCount) {

    	Calendar cal = Calendar.getInstance();
    	String toDay = null;
    	int rtnVal;

    	// 오늘 날짜 인지 Check
    	if (!ComU.equals("N", dateType)) {

	    	// Day 인지 Check
	    	if (ComU.equals("D", dateType)) {
		    	rtnVal = Integer.parseInt(dateCount);
		    	cal.add(Calendar.DATE, -rtnVal);
	    	}

	    	// Week 인지 Check
	    	if (ComU.equals("W", dateType)) {
		    	rtnVal = Integer.parseInt(dateCount) * 7;
		    	cal.add(Calendar.DATE, -rtnVal);
	    	}

	    	// Month 인지 Check
	    	if (ComU.equals("M", dateType)) {
		    	rtnVal = Integer.parseInt(dateCount);
		    	cal.add(Calendar.MONTH, -rtnVal);
	    	}

	    	// Year 인지 Check
	    	if (ComU.equals("Y", dateType)) {
		    	rtnVal = Integer.parseInt(dateCount);
		    	cal.add(Calendar.YEAR, -rtnVal);
	    	}
    	}

    	String year = Integer.toString(cal.get(Calendar.YEAR));
    	String mon = Integer.toString(cal.get(Calendar.MONTH)+1);
    	String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));

    	// Month 가 2자리 이하일때 공백을 0 으로 채워준다.
    	if (mon.length() < 2 ) {
    		mon = "0" + mon;
    	}
    	// Day 가 2자리 이하일때 공백을 0 으로 채워준다.
    	if (day.length() < 2 ) {
    		day = "0" + day;
    	}

//    	toDay = year + "-"+ mon + "-"+ day;
    	toDay = year + "" + mon + "" + day;

    	return toDay;

    }

    /****************************************************************************************************************************
    *** Replacing 관련
    ****************************************************************************************************************************/

    /**
     * <p>문자열에 지정한 문자중에서 첫번째 문자를 다른 문자로 대체해서 반환합니다.</p>
     *
     * <pre>
     * StringUtils.replaceOnce(null, *, *)        = null
     * StringUtils.replaceOnce("", *, *)          = ""
     * StringUtils.replaceOnce("any", null, *)    = "any"
     * StringUtils.replaceOnce("any", *, null)    = "any"
     * StringUtils.replaceOnce("any", "", *)      = "any"
     * StringUtils.replaceOnce("aba", "a", null)  = "aba"
     * StringUtils.replaceOnce("aba", "a", "")    = "ba"
     * StringUtils.replaceOnce("aba", "a", "z")   = "zba"
     * </pre>
     *
     * @see #replace(String text, String searchString, String replacement, int max)
     * @param text  피 문자열
     * @param searchString  바뀔 문자열
     * @param replacement  대체할 문자열
     * @return 바뀐 문자열
     */
    public static String replaceOnce(String text, String searchString, String replacement) {
         return StringUtils.replaceOnce(text, searchString, replacement);
    }

    /**
     * <p>문자열에 지정한 문자를 모두 다른 문자로 대체해서 반환합니다.</p>
     *
     * <pre>
     * StringUtils.replace(null, *, *)        = null
     * StringUtils.replace("", *, *)          = ""
     * StringUtils.replace("any", null, *)    = "any"
     * StringUtils.replace("any", *, null)    = "any"
     * StringUtils.replace("any", "", *)      = "any"
     * StringUtils.replace("aba", "a", null)  = "aba"
     * StringUtils.replace("aba", "a", "")    = "b"
     * StringUtils.replace("aba", "a", "z")   = "zbz"
     * </pre>
     *
     * @see #replace(String text, String searchString, String replacement, int max)
     * @param text  피 문자열
     * @param searchString  바뀔 문자열
     * @param replacement  대체할 문자열
     * @return 바뀐 문자열
     */
    public static String replace(String text, String searchString, String replacement) {
         return StringUtils.replace(text, searchString, replacement);
    }

    /**
     * <p>문자열에 지정한 문자중에서 첫번째 문자부터 지정한 인덱스까지 다른 문자로 대체해서 반환합니다.</p>
     *
     * <pre>
     * StringUtils.replace(null, *, *, *)         = null
     * StringUtils.replace("", *, *, *)           = ""
     * StringUtils.replace("any", null, *, *)     = "any"
     * StringUtils.replace("any", *, null, *)     = "any"
     * StringUtils.replace("any", "", *, *)       = "any"
     * StringUtils.replace("any", *, *, 0)        = "any"
     * StringUtils.replace("abaa", "a", null, -1) = "abaa"
     * StringUtils.replace("abaa", "a", "", -1)   = "b"
     * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     *
     * @param text  피 문자열
     * @param searchString  바뀔 문자열
     * @param replacement  대체할 문자열
     * @param max  대체할 최대 인덱스, 만약 지정된 값이 없으면 -1로 모든 문자를 치환합니다.
     * @return the text with any replacements processed,
     *  <code>null</code> if null String input
     */
    public static String replace(String text, String searchString, String replacement, int max) {
         return StringUtils.replace(text, searchString, replacement, max);
    }

    /**
     * <p>
     * 문자열에서 searchList에 있는 문자열들을 replacementList에 있는 문자열로 모두 대체합니다.
     * </p>
     *
     * <pre>
     *  StringUtils.replaceEach(null, *, *)        = null
     *  StringUtils.replaceEach("", *, *)          = ""
     *  StringUtils.replaceEach("aba", null, null) = "aba"
     *  StringUtils.replaceEach("aba", new String[0], null) = "aba"
     *  StringUtils.replaceEach("aba", null, new String[0]) = "aba"
     *  StringUtils.replaceEach("aba", new String[]{"a"}, null)  = "aba"
     *  StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{""})  = "b"
     *  StringUtils.replaceEach("aba", new String[]{null}, new String[]{"a"})  = "aba"
     *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"})  = "wcte"
     *  (example of how it does not repeat)
     *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"})  = "dcte"
     * </pre>
     *
     * @param text 피 문자열
     * @param searchList 검색할 문자열이 있는 배열
     * @param replacementList 대체할 문자열이 있는 배열
     * @return 변환된 문자열
     * @throws 배열이 길이가 0이거나 null일 경우 IndexOutOfBoundsException이 발생
     * @since 2.4
     */
    public static String replaceEach(String text, String[] searchList, String[] replacementList) {
         return StringUtils.replaceEach(text, searchList, replacementList);
    }

    /**
     * <p>
     * 문자열에서 searchList에 있는 문자열들을 replacementList에 있는 문자열로 모두 대체합니다.
     * </p>
     *
     * <pre>
     *  StringUtils.replaceEach(null, *, *)        = null
     *  StringUtils.replaceEach("", *, *)          = ""
     *  StringUtils.replaceEach("aba", null, null) = "aba"
     *  StringUtils.replaceEach("aba", new String[0], null) = "aba"
     *  StringUtils.replaceEach("aba", null, new String[0]) = "aba"
     *  StringUtils.replaceEach("aba", new String[]{"a"}, null)  = "aba"
     *  StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{""})  = "b"
     *  StringUtils.replaceEach("aba", new String[]{null}, new String[]{"a"})  = "aba"
     *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"})  = "wcte"
     *  (example of how it does not repeat)
     *  StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"})  = "dcte"
     * </pre>
     *
     * @param text 피 문자열
     * @param searchList 검색할 문자열이 있는 배열
     * @param replacementList 대체할 문자열이 있는 배열
     * @return 변환된 문자열
     * @throws 배열이 길이가 0이거나 null일 경우 IndexOutOfBoundsException이 발생
     * @since 2.4
     */
    public static String replaceEachRepeatedly(String text, String[] searchList, String[] replacementList) {
         return StringUtils.replaceEachRepeatedly(text, searchList, replacementList);
    }

    /****************************************************************************************************************************
    *** Replace, character based 관련
    ****************************************************************************************************************************/
    /**
     * <p>문자열의 문자들을 지정한 문자로 모두 바꿉니다.</p>
     *
     * <p>문자열이 null일 경우 null을 반환합니다
     * 빈 문자열일 경우 빈 문자열을 반환합니다.</p>
     *
     * <pre>
     * StringUtils.replaceChars(null, *, *)        = null
     * StringUtils.replaceChars("", *, *)          = ""
     * StringUtils.replaceChars("abcba", 'b', 'y') = "aycya"
     * StringUtils.replaceChars("abcba", 'z', 'y') = "abcba"
     * </pre>
     *
     * @param str  변화할 문자열
     * @param searchChar  바뀔 문자
     * @param replaceChar  대체할 문자
     * @return 수정된 문자열
     */
    public static String replaceChars(String str, char searchChar, char replaceChar) {
         return StringUtils.replaceChars(str, searchChar, replaceChar);
    }

    /**
     * <p>문자열에서 지정한 문자열을 다른 문자열을 모두 바꿉니다.</p>
     *
     * <p>예를들어:<br />
     * <code>replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly</code>.</p>
     *
     * <p>문자열이 null일 경우 null을 반환합니다
     * 빈 문자열일 경우 빈 문자열을 반환합니다.</p>
     * null이나 빈문자열로 대체하려는 경우 원래 문자가 반환됩니다.</p>
     *
     *
     * <pre>
     * StringUtils.replaceChars(null, *, *)           = null
     * StringUtils.replaceChars("", *, *)             = ""
     * StringUtils.replaceChars("abc", null, *)       = "abc"
     * StringUtils.replaceChars("abc", "", *)         = "abc"
     * StringUtils.replaceChars("abc", "b", null)     = "ac"
     * StringUtils.replaceChars("abc", "b", "")       = "ac"
     * StringUtils.replaceChars("abcba", "bc", "yz")  = "ayzya"
     * StringUtils.replaceChars("abcba", "bc", "y")   = "ayya"
     * StringUtils.replaceChars("abcba", "bc", "yzx") = "ayzya"
     * </pre>
     *
     * @param str  변화할 문자열
     * @param searchChars  바뀔 문자열
     * @param replaceChars  대체할 문자열
     * @return 수정된 문자열
     */
    public static String replaceChars(String str, String searchChars, String replaceChars) {
         return StringUtils.replaceChars(str, searchChars, replaceChars);
    }

    /**
     * <p>true,false 문자를 true 일 경우 Y, false 일 경을 반환합니다.</p>
     *
     * <pre>
     * getTrueFalseChange("TRUE") = Y
     * getTrueFalseChange("true") = Y
     * getTrueFalseChange("FALSE") = N
     * getTrueFalseChange("false") = N
     * </pre>
     *
     * @param str  true,false 문자
     * @return true 일 경우 Y, false 일 경을 반환합니다.
     */
    public static String getTrueFalseChange(String str) {
        String rtnStr = "";
        String chkStr = "";

        chkStr = str.toUpperCase();

        if(chkStr.equals("TRUE")) {
        	rtnStr = "Y";
        } else if(chkStr.equals("FALSE")) {
        	rtnStr = "N";
        } else {
        	rtnStr = "N";
        }

    	return rtnStr;
   }

    /****************************************************************************************************************************
    *** Substring 관련
    ****************************************************************************************************************************/

    /**
     * <p>문자열에서 주어진 위치부터 문자를 잘라서 반환합니다.</p>
     *문자열이 null인 경우 null을 리턴하고 빈 문자열일 경우 빈 문자열을 반환합니다
     * <pre>
     * StringUtils.substring(null, *)   = null
     * StringUtils.substring("", *)     = ""
     * StringUtils.substring("abc", 0)  = "abc"
     * StringUtils.substring("abc", 2)  = "c"
     * StringUtils.substring("abc", 4)  = ""
     * StringUtils.substring("abc", -2) = "bc"
     * StringUtils.substring("abc", -4) = "abc"
     * </pre>
     *
     * @param str  피 문자열
     * @param start  시작위치
     *
     * @return 주어진 위치에서 부터 잘라낸 문자열
     */
    public static String substring(String str, int start) {
         return StringUtils.substring(str, start);
    }

    /**
     * <p>문자열을 주어진 시작위치 부터 끝위치까지 잘라냅니다.</p>
     *
     * <pre>
     * StringUtils.substring(null, *, *)    = null
     * StringUtils.substring("", * ,  *)    = "";
     * StringUtils.substring("abc", 0, 2)   = "ab"
     * StringUtils.substring("abc", 2, 0)   = ""
     * StringUtils.substring("abc", 2, 4)   = "c"
     * StringUtils.substring("abc", 4, 6)   = ""
     * StringUtils.substring("abc", 2, 2)   = ""
     * StringUtils.substring("abc", -2, -1) = "b"
     * StringUtils.substring("abc", -4, 2)  = "ab"
     * </pre>
     *
     * @param str  피 문자열
     * @param start  시작위치
     * @param end  끝 위치
     * @return 주어진 위치에 의해 잘라진 문자열, 문자열이 null일 경우 null을 반환합니다
     *  <code>null</code> if null String input
     */
    public static String substring(String str, int start, int end) {
         return StringUtils.substring(str, start, end);
    }

    /****************************************************************************************************************************
    *** Left/Right/Mid 관련
    ****************************************************************************************************************************/

    /**
     * <p>문자열을 왼쪽에서 부터 주어진 길이 만큼 잘라냅니다</p>
     *
     * <p>만약 길이가 문자열의 길이가 존재하지 않거나 문자열이 null이여도 예외없이 문자열을 반환합니다
     *     예외의 문자열의 길이에 의해서 발생됩니다.</p>
     *
     * <pre>
     * StringUtils.left(null, *)    = null
     * StringUtils.left(*, -ve)     = ""
     * StringUtils.left("", *)      = ""
     * StringUtils.left("abc", 0)   = ""
     * StringUtils.left("abc", 2)   = "ab"
     * StringUtils.left("abc", 4)   = "abc"
     * </pre>
     *
     * @param str  피 문자열
     * @param len  문자열을 잘라낼 길이, 길이는 0 이거나 반드시 존재해야 합니다.
     * @return 왼쪽에서 부터 주어진 길이 만큼 잘라낸 문자열, 문자열이 null일 경우 null을 반환합니다
     */
    public static String left(String str, int len) {
         return StringUtils.left(str, len);
    }

    /**
     ***
     * <p>문자열을 오른쪽에서 부터 주어진 길이 만큼 잘라냅니다</p>
     *
     * <p>만약 길이가 문자열의 길이가 존재하지 않거나 문자열이 null이여도 예외없이 문자열을 반환합니다
     *     예외의 문자열의 길이에 의해서 발생됩니다.</p>
     *
     * <pre>
     * StringUtils.right(null, *)    = null
     * StringUtils.right(*, -ve)     = ""
     * StringUtils.right("", *)      = ""
     * StringUtils.right("abc", 0)   = ""
     * StringUtils.right("abc", 2)   = "bc"
     * StringUtils.right("abc", 4)   = "abc"
     * </pre>
     *
     * @param str  피 문자열
     * @param len  문자열을 잘라낼 길이, 길이는 0 이거나 반드시 존재해야 합니다.
     * @return 오른쪽에서 부터 주어진 길이 만큼 잘라낸 문자열, 문자열이 null일 경우 null을 반환합니다
     */
    public static String right(String str, int len) {
         return StringUtils.right(str, len);
    }

    /**
     * <p>문자열을 주어진 위치부터 지정한 길이까지 잘라냅니다.</p>
     *
     * <p>만약 길이가 문자열의 길이가 존재하지 않거나 문자열이 null이여도 예외없이 문자열을 반환합니다
     *     예외의 문자열의 길이에 의해서 발생됩니다.</p>
     *
     * <pre>
     * StringUtils.mid(null, *, *)    = null
     * StringUtils.mid(*, *, -ve)     = ""
     * StringUtils.mid("", 0, *)      = ""
     * StringUtils.mid("abc", 0, 2)   = "ab"
     * StringUtils.mid("abc", 0, 4)   = "abc"
     * StringUtils.mid("abc", 2, 4)   = "c"
     * StringUtils.mid("abc", 4, 2)   = ""
     * StringUtils.mid("abc", -2, 2)  = "ab"
     * </pre>
     *
     * @param str  피 문자열
     * @param pos  시작 위치
     * @param len  문자열을 잘라낼 길이
     * @return 중간에서 부터 잘라낸 문자열, 문자열이 null일 경우 null을 반환합니다
     */
    public static String mid(String str, int pos, int len) {
         return StringUtils.mid(str, pos, len);
    }

    /**
     * <p>구분자로 배열에 담아서 원하는 인덱스의 값을 리턴한다.</p>
     *
     * <pre>
     * getSplitIdxValue("100/2", "/", 0)    = 100
     * </pre>
     *
     * @param str  피 문자열
     * @param searchVal  구분자
     * @param idx  인덱스
     * @return 구분자로 배열에 담아서 원하는 인덱스의 값을 반환합니다
     */
    public static String getSplitIdxValue(String str, String searchVal, int idx) {
    	String[] strChkAry = str.trim().split(searchVal);
    	return strChkAry[idx];
    }


    /**
     * <p>형변환 후 리턴한다.</p>
     *
     * <pre>
     * getTypeChange("100.0", "str")    = 100
     * </pre>
     *
     * @param str  피 문자열
     * @param type 변환할 타입
     * @return 형변환 후 리턴한다.
     */
    public static String getTypeChange(String str, String type) {
    	String rtnStr = " ";
    	boolean isNumChk = true;

    	//숫자인지 확인.
    	try {
    		Double.parseDouble(str);
    	} catch (NumberFormatException e) {
    		isNumChk = false;
    	}

    	if(ComU.equals("str", type)){
    		if(isNumChk){
    			//더블을 인트로 변경하고 다시 문자로 변경
    			rtnStr = Integer.toString(Integer.parseInt(String.valueOf(Math.round(Double.parseDouble(str)))));
    		} else {
    			rtnStr = str;
    		}

    		return rtnStr;

    	} else {
    		return rtnStr;
    	}


    }

    /*
     *	체크 : 길이 , 문자 , 숫자
     *
     */

    //문자길이 체크
    public static boolean isStringlength(String data,int datalength){
    	data.trim();
    	if( data.length() > datalength ){
    		return true;
    	}
    	return false;
    }



    // 숫자 이고 길이가 맞으면 체크
    public static boolean isStringDouble(String data, String type) {
    	boolean b_Chk = false;

    	/**
    	 *  Case (A) : [ null, "", " ", 0 ]
    	 */
		if(ComU.equals("A", type)) {

			try {
				if (ComU.isNotBlank(data) && !"0".equals(data)){
					Double.parseDouble(data);
					b_Chk =  false;
				}else{
					b_Chk  = true;
				}
			} catch (NumberFormatException e) {
				b_Chk  = true;
			}

		/**
		 *  Case (B) : [ null, "", " "]
		 */
		}else if(ComU.equals("B", type)) {

			try {
				if (ComU.isNotBlank(data)){
					Double.parseDouble(data);
					b_Chk =  false;
				}else{
					b_Chk  = true;
				}
			} catch (NumberFormatException e) {
				b_Chk  = true;
			}

		/**
		 *  Case (C) : [ "," ]
		 */
		}else if(ComU.equals("C", type)) {

			try {
				if (data.indexOf(",") != -1){
					b_Chk  = true;
				}else{
					b_Chk =  false;
				}
			} catch (NumberFormatException e) {
				b_Chk  = true;
			}

		}else {
			b_Chk =  false;
		}

    	return b_Chk;
    }

    /**
     * <p>trim 후에 값이 null이거나 빈값이면 다른 값으로 치환한다.</p>
     *
     * <pre>
     * StringUtils.nvl(null, " ")     = " "
     * StringUtils.nvl("", "*")       = "*"
     * StringUtils.nvl("     ", "")   = ""
     * StringUtils.nvl("abc")         = "abc"
     * StringUtils.nvl("    abc    ") = "abc"
     * </pre>
     *
     * @param str  공백을 삭제할 문자열
     * @return the 공백을 제거한 문자열g, 만약 <code>null</code> 을 입력하면 빈 문자열을 반환합니다.
     */
    public static String nvl(String str, String reStr) {
    	String result = StringUtils.trimToEmpty(str);
    	if( "".equals( result ) ) result = reStr;

    	return result;
    }


    /**
     * <p>지정한 시간만큼 잠시 lock을 건다.</p>
     *
     * <pre>
     * sleep(1000)
     * </pre>
     *
     * @param time 락을 걸시간
     */
    public static void sleep(int time){
        try {
          Thread.sleep(time);
        } catch (InterruptedException e) { }
    }


    //WAS 의 IP 를 구해서 반환한다.
    @SuppressWarnings("finally")
	public static String wasIpCheck(){

    	String rtnValue = " ";

    	try{
    		rtnValue = InetAddress.getLocalHost().getHostAddress();
    	} catch( UnknownHostException e ){
    		e.printStackTrace();
    	}finally{
    		return rtnValue;
    	}

    }


    /**
     * <p>값 비교후 큰값을 리턴한다.</p>
     *
     * <pre>
     * getMax(100, 90)    = 100
     * </pre>
     *
     * @param chkVal01 비교값1
     * @param chkVal02 비교값2
     * @return 값 비교후 큰값을 리턴한다.
     */
    public static double getMax(double chkVal01, double chkVal02) {
    	double rtnVal = 0;

    	if(chkVal01 > chkVal02) {
    		rtnVal = chkVal01;
    	} else if(chkVal01 < chkVal02) {
    		rtnVal = chkVal02;
    	} else {
    		rtnVal = chkVal01;
    	}

    	return rtnVal;

    }

    public static long getMax(long chkVal01, long chkVal02) {
    	long rtnVal = 0;

    	if(chkVal01 > chkVal02) {
    		rtnVal = chkVal01;
    	} else if(chkVal01 < chkVal02) {
    		rtnVal = chkVal02;
    	} else {
    		rtnVal = chkVal01;
    	}

    	return rtnVal;

    }

    public static int getMax(int chkVal01, int chkVal02) {
    	int rtnVal = 0;

    	if(chkVal01 > chkVal02) {
    		rtnVal = chkVal01;
    	} else if(chkVal01 < chkVal02) {
    		rtnVal = chkVal02;
    	} else {
    		rtnVal = chkVal01;
    	}

    	return rtnVal;

    }

    /**
     * <p>값 비교후 작은값을 리턴한다.</p>
     *
     * <pre>
     * getMin(100, 90)    = 90
     * </pre>
     *
     * @param chkDbl01 비교값1
     * @param chkVal02 비교값2
     * @return 값 비교후 작은값을 리턴한다.
     */
    public static double getMin(double chkVal01, double chkVal02) {
    	double rtnVal = 0;

    	if(chkVal01 > chkVal02) {
    		rtnVal = chkVal02;
    	} else if(chkVal01 < chkVal02) {
    		rtnVal = chkVal01;
    	} else {
    		rtnVal = chkVal01;
    	}

    	return rtnVal;
    }

    public static long getMin(long chkVal01, long chkVal02) {
    	long rtnVal = 0;

    	if(chkVal01 > chkVal02) {
    		rtnVal = chkVal02;
    	} else if(chkVal01 < chkVal02) {
    		rtnVal = chkVal01;
    	} else {
    		rtnVal = chkVal01;
    	}

    	return rtnVal;
    }

    public static int getMin(int chkVal01, int chkVal02) {
    	int rtnVal = 0;

    	if(chkVal01 > chkVal02) {
    		rtnVal = chkVal02;
    	} else if(chkVal01 < chkVal02) {
    		rtnVal = chkVal01;
    	} else {
    		rtnVal = chkVal01;
    	}

    	return rtnVal;
    }

    /**
     * <p>지정된 자릿수 만큼 반올림 하여 리턴한다.</p>
     *
     * <pre>
     * round(1.345, 2)    = 1.35
     * </pre>
     *
     * @param chkVal 반올림 할 값
     * @param chkType 반올림 할 자릿수
     * @return 지정된 자릿수 만큼 반올림 하여 리턴한다.
     */
    public static double roundDbl(double chkVal, int chkType) {
    	double rtnVal = 0;

    	rtnVal = Double.parseDouble(String.format("%."+chkType+"f",  chkVal));

    	return rtnVal;
    }

    /**
     * <p>만원단위로 변경하여 리턴한다..</p>
     *
     * <pre>
     * changeUnitTenThs(10000) = 1
     * </pre>
     *
     * @param chkVal 변경이 필요한 금액.
     * @return 만원단위로 변경하여 리턴한다.
     */
    public static int changeUnitTenThs(int chkVal) {
    	int rtnVal = 0;

    	rtnVal = (int) Math.floor(chkVal / 10000);

    	return rtnVal;
    }

    public static int changeUnitTenThs(double chkVal) {
    	int rtnVal = 0;

    	rtnVal = (int) Math.floor(chkVal / 10000);

    	return rtnVal;
    }

    public static long changeUnitTenThs(long chkVal) {
    	long rtnVal = 0;

    	rtnVal = (long) Math.floor(chkVal / 10000);

    	return rtnVal;
    }

    /**
     * <p>원단위로 변경하여 리턴한다..</p>
     *
     * <pre>
     * changeUnitOne(1) = 10000
     * </pre>
     *
     * @param chkVal 변경이 필요한 금액.
     * @return 원단위로 변경하여 리턴한다.
     */
    public static long changeUnitOne(long chkVal) {
    	long rtnVal = 0;

    	rtnVal = chkVal * 10000;

    	return rtnVal;
    }

    public static int changeUnitOne(double chkVal) {
    	int rtnVal = 0;

    	rtnVal = (int) Math.floor(chkVal * 10000);

    	return rtnVal;
    }

    /**
     * <p>조회기간을 리턴한다.</p>
     *
     * <pre>
     * ComU.getSrhDate("start",3)
     * ComU.getSrhDate("end",1)
     * ComU.getSrhDate("now",0)
     * </pre>
     *
     * @param chkType 조회 구분
     * @param chkVal 이전달, 이전날
     */
    public static String getSrhDate(String chkType, int chkVal) {
        String strSrhDate;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        if("start".equals(chkType)) {
        	Calendar fromCal = Calendar.getInstance();
            fromCal.add(Calendar.MONTH ,-chkVal);
            fromCal.set(fromCal.get(Calendar.YEAR), fromCal.get(Calendar.MONTH), fromCal.getMinimum(Calendar.DAY_OF_MONTH));
            strSrhDate = dateFormat.format(fromCal.getTime());
        } else if("end".equals(chkType)) {
        	Calendar toCal   = Calendar.getInstance();
            toCal.add(Calendar.MONTH ,- chkVal);
            toCal.set(toCal.get(Calendar.YEAR), toCal.get(Calendar.MONTH), toCal.getActualMaximum(Calendar.DAY_OF_MONTH));
            strSrhDate = dateFormat.format(toCal.getTime());
        } else if("now".equals(chkType)) {
        	Calendar nowCal = Calendar.getInstance();
        	nowCal.set(nowCal.get(Calendar.YEAR), nowCal.get(Calendar.MONTH), nowCal.get(Calendar.DAY_OF_MONTH));
            strSrhDate = dateFormat.format(nowCal.getTime());
        } else if("ago".equals(chkType)) {
        	Calendar nowCal = Calendar.getInstance();
        	nowCal.add(Calendar.DATE ,- chkVal);
        	nowCal.set(nowCal.get(Calendar.YEAR), nowCal.get(Calendar.MONTH), nowCal.get(Calendar.DATE));
            strSrhDate = dateFormat.format(nowCal.getTime());
        } else {
        	Calendar nowCal = Calendar.getInstance();
        	nowCal.set(nowCal.get(Calendar.YEAR), nowCal.get(Calendar.MONTH), nowCal.get(Calendar.DAY_OF_MONTH));
            strSrhDate = dateFormat.format(nowCal.getTime());
        }

        return strSrhDate;
    }

    /**
     * <p>조회날짜01이 조회날짜02 보다 이후 이면 true 리턴한다.</p>
     *
     * <pre>
     * ComU.getAfterDate("20220202", "20220201") = true
     * ComU.getAfterDate("20220202", "20220203") = false
     * </pre>
     *
     * @param chkVal01 조회날짜01
     * @param chkVal02 조회날짜02
     */
    public static boolean getAfterDate(String chkVal01, String chkVal02) throws Exception {
    	boolean isAfter = false;

    	if(!"".equals(chkVal01) && chkVal01 != null && !"".equals(chkVal02) && chkVal02 != null) {
    		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
    		Date chkDate01 = sdFormat.parse(chkVal01);
    		Date chkDate02 = sdFormat.parse(chkVal02);
    		if (chkDate01.after(chkDate02)) {
    			isAfter = true;
    		}
    	}

        return isAfter;
    }

    /**
     * <p>조회날짜01이 조회날짜02 보다 이전 이면 true 리턴한다.</p>
     *
     * <pre>
     * ComU.getAfterDate("20220202", "20220203") = true
     * ComU.getAfterDate("20220202", "20220201") = false
     * </pre>
     *
     * @param chkVal01 조회날짜01
     * @param chkVal02 조회날짜02
     */
    public static boolean getBeforeDate(String chkVal01, String chkVal02) throws Exception {
    	boolean isBefore = false;

    	if(!"".equals(chkVal01) && chkVal01 != null && !"".equals(chkVal02) && chkVal02 != null) {
    		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
    		Date chkDate01 = sdFormat.parse(chkVal01);
    		Date chkDate02 = sdFormat.parse(chkVal02);
    		if (chkDate01.before(chkDate02)) {
    			isBefore = true;
    		}
    	}

        return isBefore;
    }


    public static String getQuarterYear() {
    	String result = "";

    	result = ComU.getSrhDate("now",0).substring(0, 4);

    	result = "2021"; //Test_Data

    	return result;
    }

    public static int getQuarterRound() {
    	int result = 0;

    	String chkMonth = ComU.getSrhDate("now",0).substring(4, 6);

    	if("01".equals(chkMonth) || "02".equals(chkMonth) || "03".equals(chkMonth)) {
    		result = 1;
		} else if("04".equals(chkMonth) || "05".equals(chkMonth) || "06".equals(chkMonth)) {
			result = 2;
		} else if("07".equals(chkMonth) || "08".equals(chkMonth) || "09".equals(chkMonth)) {
			result = 3;
		} else if("10".equals(chkMonth) || "11".equals(chkMonth) || "12".equals(chkMonth)) {
			result = 4;
		}

    	result = 2; //Test_Data

    	return result;
    }
}
