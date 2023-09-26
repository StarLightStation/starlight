package com.spring.biz.common;

import java.util.UUID;



public class Common {
	public static String randomFileName(String fileName, int size) {

		String ext = fileName.substring(fileName.lastIndexOf("."));
		String randFileName = UUID.randomUUID().toString() + size + ext;
		return randFileName;
	}
	
	
}
