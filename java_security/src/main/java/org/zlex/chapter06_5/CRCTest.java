/**
 * 2009-9-13
 */
package org.zlex.chapter06_5;

import java.util.zip.CRC32;

import org.junit.Test;

/**
 * 测试CRC-32
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class CRCTest {
	/**
	 * 测试CRC-32
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCRC32() throws Exception {
		String str = "测试CRC-32";

		CRC32 crc32 = new CRC32();

		crc32.update(str.getBytes());
		
		String hex = Long.toHexString(crc32.getValue());
		// echo -n "LongString" | gzip -c | tail -c8 | hexdump -n4 -e '"%u"'

		System.err.println("原文：\t" + str);
		System.err.println("CRC-32：\t" + hex);
	}

}
