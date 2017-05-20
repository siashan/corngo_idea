package com.corngo.base.support.utils.security;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Title: CipherUtil
 * Description:
 * Copyright: Copyright (c)2010
 * Company: pay
 * @author Administrator
 */

public class CipherUtil {

	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public static String encryptDES(String encryptString, String encryptKey){
		String result = null;
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes("utf-8"), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));
			result = Base64.encode(encryptedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String decryptDES(String decryptString, String decryptKey){
		String result = null;
		try {
			byte[] byteMi = Base64.decode(decryptString);
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes("utf-8"), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte decryptedData[] = cipher.doFinal(byteMi);
			result = new String(decryptedData,"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
	}
}
