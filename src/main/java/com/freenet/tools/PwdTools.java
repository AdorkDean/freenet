package com.freenet.tools;
/**
 * 密码加密工具类  MD5
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;



public class PwdTools {
	
	private static Logger log=Logger.getLogger(PwdTools.class);
	
	/**
	   * 单向信息加密(信息摘要)，支持：md5、md2、SHA(SHA-1，SHA1)、SHA-256、SHA-384、SHA-512, <br>
	   * 生成时间：2014年5月2日 上午11:13:44 <br>
	   * 返回值：String 加密后的密文 <br>
	   * 
	   * @param src
	   *        传入加密字符串(明文) <br>
	   * @param method
	   *        指定算法(md5、md2、SHA(SHA-1，SHA1)、SHA-256、SHA-384、SHA-512) <br>
	   * @return
	   */
	public static String ecodingPwd(String src, String method){
		try {
			MessageDigest md5=MessageDigest.getInstance(method);
			md5.update(src.getBytes());
			byte[] encoding=md5.digest();
			//32加密
			return Hex.encodeHexString(encoding);
			//64编码
			//return new String(Base64.byteArrayToBase64(encoding));
		} catch (NoSuchAlgorithmException e) {
			log.warn("密码加密失败!--->" + src);
			e.printStackTrace();
			return src;
		}
	}
	
	
	
	public static void main(String agrs[]){
		System.out.println(PwdTools.ecodingPwd("admin","md5"));
	}

}
