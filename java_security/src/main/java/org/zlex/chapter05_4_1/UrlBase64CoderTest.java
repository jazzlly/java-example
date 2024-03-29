/**
 * 2009-8-20
 */
package org.zlex.chapter05_4_1;

import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Url Base64编码与解码测试类
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class UrlBase64CoderTest {

	/**
	 * 测试Base64编码与解码
	 */
	@Test
	public final void test() throws Exception {

		String inputStr = "Java加密与解密的艺术";

		System.err.println("原文:\t" + inputStr);

		// 进行Base64编码
		String code = UrlBase64Coder.encode(inputStr);

		System.err.println("编码后:\t" + code);
		// SmF2YeWKoOWvhuS4juino-WvhueahOiJuuacrw.. urlbase64
		// SmF2YeWKoOWvhuS4juino+WvhueahOiJuuacrw== base64

		// 进行Base64解码
		String outputStr = UrlBase64Coder.decode(code);

		System.err.println("解码后:\t" + outputStr);

		// 验证Base64编码解码一致性
		Assertions.assertThat(inputStr).isEqualTo(outputStr);

	}

}
