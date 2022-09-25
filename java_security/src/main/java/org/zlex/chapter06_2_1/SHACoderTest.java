/**
 * 2009-9-3
 */
package org.zlex.chapter06_2_1;

import static org.junit.Assert.*;

import org.apache.commons.codec.binary.Hex;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA校验
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class SHACoderTest {

	/**
	 * 测试SHA-1
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testEncodeSHA() throws Exception {
		String str = "SHA1消息摘要";

		// 获得摘要信息
		byte[] data1 = SHACoder.encodeSHA(str.getBytes());
		byte[] data2 = SHACoder.encodeSHA(str.getBytes());

		// 校验
		Assertions.assertThat(data1).isEqualTo(data2);
		System.out.println(Hex.encodeHexString(data1));
	}

	/**
	 * 测试SHA-256
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testEncodeSHA256() throws Exception {
		String str = "SHA256消息摘要";

		// 获得摘要信息
		byte[] data1 = SHACoder.encodeSHA256(str.getBytes());
		byte[] data2 = SHACoder.encodeSHA256(str.getBytes());

		// 校验
		assertArrayEquals(data1, data2);
		System.out.println(data1.length);
		System.out.println(Hex.encodeHexString(data1));

		// # bash
		// echo -n 'SHA256消息摘要' |sha256sum
	}

	/**
	 * 测试SHA-384
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testEncodeSHA384() throws Exception {
		String str = "SHA384消息摘要";

		// 获得摘要信息
		byte[] data1 = SHACoder.encodeSHA384(str.getBytes());
		byte[] data2 = SHACoder.encodeSHA384(str.getBytes());

		// 校验
		assertArrayEquals(data1, data2);
	}

	/**
	 * 测试SHA-512
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testEncodeSHA512() throws Exception {
		String str = "SHA512消息摘要";

		// 获得摘要信息
		byte[] data1 = SHACoder.encodeSHA512(str.getBytes());
		byte[] data2 = SHACoder.encodeSHA512(str.getBytes());

		// 校验
		assertArrayEquals(data1, data2);
	}

	@Test
	public void testDigestUpdateAndReset() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");

		digest.update("你好xixihaha".getBytes("utf-8"));
		digest.update("娃哈哈".getBytes("utf-8"));
		byte[] data1 = digest.digest();

		digest.reset();
		byte[] data2 = digest.digest("你好xixihaha娃哈哈".getBytes("utf-8"));

		System.out.println(Hex.encodeHexString(data2));
		Assertions.assertThat(data1).isEqualTo(data2);

		// # bash
		// echo -n '你好xixihaha娃哈哈'|sha256sum
		// echo -n '你好xixihaha娃哈哈'|openssl sha256
	}
}
