package com.skt.mydata.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class CommUtils {

	/**
	 * 9자리 난수 생성
	 * @param len
	 * @return
	 */
	public static String randomNumber (int len) {
		Random rand = new Random();
		String numStr = ""; //난수가 저장될 변수


		for(int i=0; i<len; i++) {
			//0~9 까지 난수 생성
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}

		return numStr;
	}

	/**
	 * 랜덤숫자 + 현재날짜,시간으로 임의값 생성
	 * @return
	 */
	public static String makeServerFileName () {
		String randomString = CommUtils.randomNumber(9);
		String dateString = DateUtils.getNowTimeMillis();

		return randomString + "_" + dateString;
	}
	
	/**
	 * sha256 암호화
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encrypt_sha256(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(str.getBytes());

        return bytesToHex(md.digest());
	}
	
	/**
	 * sha256 암호화 관련 포맷 변경
	 * @param bytes
	 * @return
	 */
	private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
	
	/**
	 * 클라이언트 IP 주소 반환
	 * @param request
	 * @return
	 */
	public static String getClientIP(HttpServletRequest request) {
		 String ip = null;

        ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-Real-IP"); 
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-RealIP"); 
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("REMOTE_ADDR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }

		return ip;
	    
	}
	
	/**
	 * 페이지네이션 관련하여 총 페이지 수 반
	 * @param int
	 * @return
	 */
	public static int totalPage(int totalCount,int size) {
    	int totalPages = totalCount/size;
    	int remainder = totalCount%size;
    	if(remainder>0) {
    		totalPages = totalPages+1;
    	}
    	return totalPages;
    }
	
	/**
	 * 테이블 정렬기능 파라미터 변환
	 * 1 : ASC
	 * 2 : DESC
	 * @param String("1" or "2")
	 * @return
	 */
	public static String changeSortDirection(String sortDirection) {
		String sortDir = "";
		if(sortDirection.equals("1")) {
			sortDir = "ASC";
		}else if(sortDirection.equals("2")) {
			sortDir = "DESC";
		}else {
			sortDir = null;
		}
		return sortDir;
	}
	

}
