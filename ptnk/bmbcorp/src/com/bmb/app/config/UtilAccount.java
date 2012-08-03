package com.bmb.app.config;

import java.security.MessageDigest;

/**
 * Class ini di gunkan untuk utility account, seperti memange password
 * atau segala sesuatu yang berkaitan dengan user account
 * @author toyib
 */
public class UtilAccount {
	public String md5(String passwd) throws Exception {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(passwd.getBytes("UTF8"));
		byte s[] = m.digest();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < s.length; i++) {
			result.append(Integer.toHexString((0x000000ff & s[i]) | 0xffffff00)
					.substring(6));
		}
		return result.toString();
	}
}
