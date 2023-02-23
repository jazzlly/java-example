//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sdt.api.asyalg;

import com.sdt.api.jni.JniApi;

public class AsyAlgorithm {
    private static JniApi sjy = new JniApi();
    int ret = -1;

    public AsyAlgorithm() {
    }

    public int SJY_JNI_Index_Sign(int keyIndex, byte[] hash, int hash_len, byte[] sign, int[] sign_len) {
        this.ret = -1;
        this.ret = sjy.SJY_SCE_Index_Sign(keyIndex, hash, sign);
        return this.ret;
    }

    public int SJY_JNI_External_Verify(byte[] pPubKey, byte[] hash, int hash_len, byte[] sig, int sig_len) {
        this.ret = -1;
        this.ret = sjy.SJY_SCE_External_Verify(pPubKey, hash, sig);
        return this.ret;
    }

    public int SJY_JNI_SCE_External_Encrypt(byte[] pPubKey, byte[] data, int dataLen, byte[] out, int[] outLen) {
        this.ret = -1;
        this.ret = sjy.SJY_SCE_External_Encrypt(pPubKey, data, dataLen, out, outLen);
        return this.ret;
    }

    public int SJY_JNI_SCE_External_Decrypt(byte[] pPriKey, byte[] data, int dataLen, byte[] out, int[] outLen) {
        this.ret = -1;
        this.ret = sjy.SJY_SCE_External_Decrypt(pPriKey, data, dataLen, out, outLen);
        return this.ret;
    }
}
