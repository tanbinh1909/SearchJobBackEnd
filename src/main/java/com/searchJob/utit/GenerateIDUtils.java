package com.searchJob.utit;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class GenerateIDUtils {

	public static String getUUID(String firstChar) {
		UUID idOne = UUID.randomUUID();
		int uid = idOne.hashCode();
		StringBuilder sb = new StringBuilder();
		if(!StringUtils.isEmpty(firstChar)) {
			sb.append(firstChar);
		}
		sb.append(String.valueOf(uid).replaceAll("-", ""));
		return sb.toString();
	}
}
