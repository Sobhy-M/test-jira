package com.masary.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author hammad
 */
public class MasaryEncryption
{

	public static String encrypt(String data) throws Exception
	{
		String bytesEncoded = new BASE64Encoder().encode(data.getBytes());
		return bytesEncoded;

	}

	public static String decrypt(String encryptedData) throws Exception
	{
		byte[] bytesDecoded = new BASE64Decoder().decodeBuffer(encryptedData);
		return new String(bytesDecoded);
	}

}