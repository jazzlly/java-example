//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sdt.api.symalg;

import com.sdt.api.jni.JniApi;

public class SymAlgorithm {
    private static JniApi sjy = new JniApi();
    int ret = -1;

    public SymAlgorithm() {
    }

    public int SJY_JNI_External_Encrypt(byte[] key, int keyLen, int algId, byte[] iv, byte[] data, int dataLen, byte[] out, int[] outdataLen) {
        this.ret = -1;
        this.ret = sjy.SJY_External_Encrypt(key, keyLen, algId, iv, data, dataLen, out, outdataLen);
        return this.ret;
    }

    public int SJY_JNI_External_Decrypt(byte[] key, int keyLen, int algId, byte[] iv, byte[] data, int dataLen, byte[] out, int[] outdataLen) {
        this.ret = -1;
        this.ret = sjy.SJY_External_Decrypt(key, keyLen, algId, iv, data, dataLen, out, outdataLen);
        return this.ret;
    }

    public int SJY_JNI_SM3_MAC(byte[] pData, int datalen, int keyIndex, int[] outlen, byte[] out) {
        this.ret = -1;
        this.ret = sjy.SJY_SM3_MAC(pData, datalen, keyIndex, outlen, out);
        return this.ret;
    }
}
