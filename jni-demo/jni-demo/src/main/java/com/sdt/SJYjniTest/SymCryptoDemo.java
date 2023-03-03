package com.sdt.SJYjniTest;

import com.sdt.api.symalg.SymAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.Assert;

import java.nio.charset.StandardCharsets;

import static com.sdt.SJYjniTest.Demo.SGD_SM4_ECB;

@Slf4j
public class SymCryptoDemo {


    private static SymAlgorithm sa = new SymAlgorithm();

    // 对称加密外部秘钥
    private static byte sym_outside_key[] = {
            (byte)0x04,(byte)0x7b,(byte)0x1e,(byte)0x10,(byte)0x47,(byte)0x92,
            (byte)0x61,(byte)0x54,(byte)0x53,(byte)0x56,(byte)0x14,(byte)0x34,
            (byte)0x46,(byte)0x41,(byte)0xef,(byte)0x2a};
    private static int sym_outside_key_len = sym_outside_key.length;

    // 对称加密IV
    private static byte IV[] = {(byte)0x01,(byte)0x2c,(byte)0x1c,(byte)0x15,(byte)0x33,(byte)0x91,
            (byte)0x06,(byte)0x43,(byte)0x55,(byte)0x54,(byte)0x32,(byte)0x31,
            (byte)0x22,(byte)0x56,(byte)0x1d,(byte)0xaa};

    private static int sym_index[] = {28};

    public static void main(String[] args) {
        testSymCrypto("你好，哈哈哈1234@@#$@!@");
        testSymCrypto("你好，哈哈哈1234@@#$@!@");
        testSymCrypto("");
        testSymCrypto("H");
        testSymCrypto("hahaa");
        testSymCrypto("a long string, 你好，哈哈哈1234@@#$@!@你好，哈哈哈1234@@#$@!@你好，哈哈哈1234@@#$@!@你好，哈哈哈1234@@#$@!@你好，哈哈哈1234@@#$@!@你好，哈哈哈1234@@#$@!@你好，哈哈哈1234@@#$@!@你好，哈哈哈1234@@#$@!@你好，哈哈哈1234@@#$@!@你好，哈哈哈1234@@#$@!@");
    }

    private static String symEncText(String plainText) {
        if (plainText == null || plainText.length() == 0) {
            return "";
        }

        byte[] textBytes = plainText.getBytes(StandardCharsets.UTF_8);
        System.out.println("=====================================");

        int cryptoLen;
        if (textBytes.length % 16 == 0) {
            cryptoLen = textBytes.length;
        } else {
            cryptoLen = (textBytes.length / 16 + 1) * 16;
        }

        System.out.println("原始消息: " + plainText);
        System.out.println("加密前消息长度: " + textBytes.length);
        System.out.println("加密后消息长度: " + cryptoLen);

        // 写入消息的原始长度
        ByteBuf buffer = Unpooled.buffer(8);
        buffer.writeInt(textBytes.length);

        // 对称加密的数据
        byte inputData[] = new byte[cryptoLen];
        System.arraycopy(textBytes, 0, inputData, 0, textBytes.length);
        int inputData_len = inputData.length;

        byte sym_enData[] = new byte[inputData_len];
        int sym_enData_len[] = {inputData_len};

        // 进行对称加密
        int ret = sa.SJY_JNI_External_Encrypt(
                sym_outside_key, sym_outside_key_len, SGD_SM4_ECB,
                IV, inputData, inputData_len, sym_enData, sym_enData_len);
        System.out.println(ret + "SJY_JNI_External_Encrypt_SGD_SM4_ECB");

        /*
            写入加密消息。buffer中数据:
            原始消息长度 (4 byte) + 加密数据byte + padding (16 bytes倍数)
         */
        buffer.writeBytes(sym_enData);

        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);

        Base64 base64 = new Base64(true);
        String base64EncString = base64.encodeAsString(bytes);
        System.out.println("数据库组合加密字段: " + base64EncString);
        return base64EncString;
    }

    private static String symDecText(String encText) {
        if (encText == null || encText.length() == 0) {
            return "";
        }

        Base64 base64 = new Base64(true);

        byte[] decodeBytes = base64.decode(encText);
        ByteBuf buffer = Unpooled.buffer(8);
        buffer.writeBytes(decodeBytes);

        int rawPlainMsgLen = buffer.readInt();
        byte[] encBytesWithPaddings = new byte[buffer.readableBytes()];
        byte[] decBytesWithPaddings = new byte[buffer.readableBytes()];
        byte[] decBytes = new byte[rawPlainMsgLen];

        buffer.readBytes(encBytesWithPaddings);

        int ret = sa.SJY_JNI_External_Decrypt(
                sym_outside_key, sym_outside_key_len, SGD_SM4_ECB,
                IV, encBytesWithPaddings, encBytesWithPaddings.length,
                decBytesWithPaddings, new int[] {decBytesWithPaddings.length});
        if (ret != 0) {
            throw new RuntimeException("decrypt failed!");
        }

        System.arraycopy(decBytesWithPaddings, 0, decBytes, 0, rawPlainMsgLen);

        String decPlainText = new String(decBytes, StandardCharsets.UTF_8);
        System.out.println("解密后字符串: " + decPlainText);
        System.out.println("解密后字符串长度: " + decPlainText.length());
        return decPlainText;
    }

    private static void testSymCrypto(String plainText) {
        testSm3Mac(plainText);
        testSm3Mac(plainText);
        testSm3Mac(plainText);

        String encText = symEncText(plainText);
        String symDecText = symDecText(encText);

        Assert.assertEquals(plainText, symDecText);
    }

    private static void testSm3Mac(String plainText) {
        // 3 mac(mac32)
        byte MAC_32[] = new byte[32];
        int MAC_32_len[] ={32};

        byte[] bytes = plainText.getBytes(StandardCharsets.UTF_8);
        System.out.println("=====================================");


        int ret = sa.SJY_JNI_SM3_MAC(bytes, bytes.length,
                sym_index[0], MAC_32_len, MAC_32);
        System.out.println(ret + ", SJY_JNI_SM3_MAC MAC_len == 32");

        Base64 base64 = new Base64(true);
        System.out.println("msg sm3 mac: " + base64.encodeAsString(MAC_32));
    }
}
