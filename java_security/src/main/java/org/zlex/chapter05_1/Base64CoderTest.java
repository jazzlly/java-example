/**
 * 2009-8-20
 */
package org.zlex.chapter05_1;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Base64编码与解码测试类
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class Base64CoderTest {

	/**
	 * 测试Base64编码与解码
	 */
	@Test
	public final void test() throws Exception {

		String inputStr = "Java加密与解密的艺术";

		System.err.println("原文:\n\t" + inputStr);

		// 进行Base64编码
		String code = Base64Coder.encode(inputStr);

		System.err.println("Base64编码后:\n\t" + code);
		// echo -n "Java加密与解密的艺术" |base64
		//SmF2YeWKoOWvhuS4juino+WvhueahOiJuuacrw==

		// 进行Base64解码
		String outputStr = Base64Coder.decode(code);
		System.err.println("Base64解码后:\n\t" + outputStr);

		// echo -n "Java加密与解密的艺术" |base64 |base64 -d

		// 验证Base64编码解码一致性
		Assertions.assertThat(inputStr).isEqualTo(outputStr);

	}

	/**
	 * 演示
	 * 
	 * @throws Exception
	 */
	@Test
	public final void demo() throws Exception {
		String str = "Java加密与解密的艺术";
		System.err.println("原文:\n\t" + str);
		byte[] input = str.getBytes();

		// Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		String data = encoder.encodeBuffer(input);
		System.err.println("编码后:\n\t" + data);

		// Base64解码
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] output = decoder.decodeBuffer(data);
		System.err.println("解码后:\n\t" + new String(output));

	}

	/**
	 * 注意base64编码不是url安全的
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void base64UrlEncode() throws UnsupportedEncodingException {
		String str = "Java加密与解密的艺术";
		System.err.println("原文:\n\t" + str);
		byte[] input = str.getBytes();

		// Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		String data = encoder.encodeBuffer(input);
		System.err.println("编码后:\n\t" + data);

		String name=java.net.URLEncoder.encode(data, "UTF-8");
		System.out.println("url encode: " + name);
	}
}
