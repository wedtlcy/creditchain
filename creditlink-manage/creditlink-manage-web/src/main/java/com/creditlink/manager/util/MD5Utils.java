package com.creditlink.manager.util;

import java.security.MessageDigest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5加密
 * 
 * @version 2017年12月21日下午4:19:04
 * @author wuliu
 */
public class MD5Utils {
    
    private static final Logger log = LoggerFactory.getLogger(MD5Utils.class);
    
//	public static String encoder(String str) throws Exception{
//		if(null == str){
//			str = "";
//		}
//		MessageDigest md5=MessageDigest.getInstance("MD5");
//		return Base64Utils.encode(md5.digest(str.getBytes()));
//	}
	
   /**
     * md5小写加密32位
     * 
     * @version 2017年12月21日下午4:02:47
     * @author wuliu
     * @param str
     * @return
     * @throws Exception
     */
    public static String md5Lower32(String str) {
        String result = null;  
        if(StringUtils.isBlank(str)) {
            return result;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
            byte b[] = md5.digest();
            int i;
            StringBuffer buf = new StringBuffer("");  
            for(int offset=0; offset<b.length; offset++){  
                i = b[offset];  
                if(i<0){  
                    i+=256;  
                }  
                if(i<16){  
                    buf.append("0");  
                }  
                buf.append(Integer.toHexString(i));  
            }  
            result = buf.toString();
        }
        catch (Exception e) {
            log.error("md5加密异常",e);
        }
        return result;
    }
    
    /**
     * md5大写加密32位
     * 
     * @version 2017年12月21日下午4:02:47
     * @author wuliu
     * @param str
     * @return
     * @throws Exception
     */
    public static String md5Upper32(String str) {
        String result = "";  
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
            byte b[] = md5.digest();
            int i;
            StringBuffer buf = new StringBuffer("");  
            for(int offset=0; offset<b.length; offset++){  
                i = b[offset];  
                if(i<0){  
                    i+=256;  
                }  
                if(i<16){  
                    buf.append("0");  
                }  
                buf.append(Integer.toHexString(i));  
            }  
            result = buf.toString().toUpperCase();
        }
        catch (Exception e) {
            log.error("md5加密异常",e);
        }
        return result;
    }
    
    /**
     * md5小写加密16位
     * 
     * @version 2017年12月21日下午4:02:47
     * @author wuliu
     * @param str
     * @return
     * @throws Exception
     */
    public static String md5Lower16(String str) {
        String result = "";  
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
            byte b[] = md5.digest();
            int i;
            StringBuffer buf = new StringBuffer("");  
            for(int offset=0; offset<b.length; offset++){  
                i = b[offset];  
                if(i<0){  
                    i+=256;  
                }  
                if(i<16){  
                    buf.append("0");  
                }  
                buf.append(Integer.toHexString(i));  
            }  
            result = buf.toString().substring(8,24);
        }
        catch (Exception e) {
            log.error("md5加密异常",e);
        }
        return result;
    }
    
    /**
     * md5大写加密16位
     * 
     * @version 2017年12月21日下午4:02:47
     * @author wuliu
     * @param str
     * @return
     * @throws Exception
     */
    public static String md5Upper16(String str) {
        String result = "";  
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
            byte b[] = md5.digest();
            int i;
            StringBuffer buf = new StringBuffer("");  
            for(int offset=0; offset<b.length; offset++){  
                i = b[offset];  
                if(i<0){  
                    i+=256;  
                }  
                if(i<16){  
                    buf.append("0");  
                }  
                buf.append(Integer.toHexString(i));  
            }  
            result = buf.toString().substring(8,24).toUpperCase();
        }
        catch (Exception e) {
            log.error("md5加密异常",e);
        }
        return result;
    }
}
