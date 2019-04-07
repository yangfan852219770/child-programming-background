package com.child.programming.base.util;

import sun.misc.BASE64Decoder;

import java.io.IOException;

public class Base64Util {

	//base64转化
	public static BASE64DecodedMultipartFile base64ToMultipart(String base64) {
		String[] baseStr = base64.split(",");
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = new byte[0];
		try {
			b = decoder.decodeBuffer(baseStr[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {
				b[i] += 256;
			}
		}

		return new BASE64DecodedMultipartFile(b, baseStr[0]);
	}
	
}
