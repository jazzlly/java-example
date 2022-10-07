/**
 * 2009-9-11
 */
package org.zlex.chapter06_3_1;

import static org.junit.Assert.*;

import org.apache.commons.codec.binary.Hex;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/**
 * MAC校验
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class MACCoderTest {

	/**
	 * 测试HmacMD5
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testEncodeHmacMD5() throws Exception {
		String str = "HmacMD5消息摘要";

		// 初始化密钥
		byte[] key = MACCoder.initHmacMD5Key();

		System.out.println(Hex.encodeHexString(key));
		// 5183248bb5448d0a2b4965efd0d09101341e774c5316c2caf98954193a65f9e2d4a2e5291f84ab85f75f0863d3f5b9b5041333c490b29b40ea08ddf07515c0a3
		// 36f28f84751ed6995d9ad9d7c8756d68b024b173a64e5cbdf8b8d7eea9ff50059232b76500c5c94d6bb3342db001e31eb02b79f1c51800ca42403c35b4cd16e9

		// 获得摘要信息
		byte[] data1 = MACCoder.encodeHmacMD5(str.getBytes(), key);
		byte[] data2 = MACCoder.encodeHmacMD5(str.getBytes(), key);

		// 校验
		assertArrayEquals(data1, data2);
	}

	@Test
	public void testHmacMdTextKey() throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec("xixihaha".getBytes(StandardCharsets.UTF_8), "HmacMD5");
		byte[] key = keySpec.getEncoded();

		byte[] bytes1 = MACCoder.encodeHmacMD5("haha".getBytes(StandardCharsets.UTF_8), key);
		byte[] bytes2 = MACCoder.encodeHmacMD5("haha".getBytes(StandardCharsets.UTF_8), key);
		System.out.println(Hex.encodeHexString(bytes1));
		System.out.println(Hex.encodeHexString(bytes2));

		Assertions.assertThat(bytes1).isEqualTo(bytes2);
	}

	@Test
	public final void testEncodeHmacMD52() throws Exception {
		String str = "HmacMD5消息摘要";

		// 初始化密钥
		byte[] key = MACCoder.initHmacMD5Key("just for test");
		byte[] key1 = MACCoder.initHmacMD5Key("just for test");

		assertArrayEquals(key1, key);

		// 获得摘要信息
		byte[] data1 = MACCoder.encodeHmacMD5(str.getBytes(), key);
		byte[] data2 = MACCoder.encodeHmacMD5(str.getBytes(), key);

		// 校验
		assertArrayEquals(data1, data2);
	}

	/**
	 * 测试HmacSHA1
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testEncodeHmacSHA() throws Exception {
		String str = "HmacSHA1消息摘要";

		// 初始化密钥
		byte[] key = MACCoder.initHmacSHAKey();

		// 获得摘要信息
		byte[] data1 = MACCoder.encodeHmacSHA(str.getBytes(), key);
		byte[] data2 = MACCoder.encodeHmacSHA(str.getBytes(), key);

		// 校验
		assertArrayEquals(data1, data2);
	}

	/**
	 * 测试HmacSHA256
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testEncodeHmacSHA256() throws Exception {
		String str = "HmacSHA256消息摘要";

		// 初始化密钥
		byte[] key = MACCoder.initHmacSHA256Key();

		// 获得摘要信息
		byte[] data1 = MACCoder.encodeHmacSHA256(str.getBytes(), key);
		byte[] data2 = MACCoder.encodeHmacSHA256(str.getBytes(), key);

		// 校验
		assertArrayEquals(data1, data2);
	}

	/**
	 * 测试HmacSHA384
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testEncodeHmacSHA384() throws Exception {
		String str = "HmacSHA384消息摘要";

		// 初始化密钥
		byte[] key = MACCoder.initHmacSHA384Key();

		// 获得摘要信息
		byte[] data1 = MACCoder.encodeHmacSHA384(str.getBytes(), key);
		byte[] data2 = MACCoder.encodeHmacSHA384(str.getBytes(), key);

		// 校验
		assertArrayEquals(data1, data2);
	}

	/**
	 * 测试HmacSHA512
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testEncodeHmacSHA512() throws Exception {
		String str = "HmacSHA512消息摘要";

		// 初始化密钥
		byte[] key = MACCoder.initHmacSHA512Key();

		// 获得摘要信息
		byte[] data1 = MACCoder.encodeHmacSHA512(str.getBytes(), key);
		byte[] data2 = MACCoder.encodeHmacSHA512(str.getBytes(), key);

		// 校验
		assertArrayEquals(data1, data2);
	}

	@Test
	public void testHmacSha256() throws GeneralSecurityException {
		System.out.println(Hex.encodeHexString(
				hmacSha256("xixi".getBytes(StandardCharsets.UTF_8),
				"hello hmac256".getBytes(StandardCharsets.UTF_8))));

		// # bash
		// echo -n 'hello hmac256'| openssl sha256 -hex -mac HMAC -macopt hexkey:$(echo -n 'xixi'|xxd -p)
		// echo -n 'hello hmac256'| openssl sha256 -hex -mac HMAC -macopt key:xixi
		// 1c1aa0df20e633bb149bd86d4b6103b26efab69875dbf685a3f182b8456cd368
		// 1c1aa0df20e633bb149bd86d4b6103b26efab69875dbf685a3f182b8456cd368
	}

	private byte[] hmacSha256(byte[] key, byte[] payload) throws GeneralSecurityException {
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(new SecretKeySpec(key, "HmacSHA256"));
		mac.update(payload);
		return mac.doFinal();
	}

}
