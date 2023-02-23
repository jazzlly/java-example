//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sdt.api.keymanage;

import com.sdt.api.jni.JniApi;

public class KeyManager {
    private static JniApi sjy = new JniApi();
    int ret = -1;

    public KeyManager() {
    }

    public int SJY_Get_Sm2Index(int[] index) {
        this.ret = -1;
        byte[] alg = new byte[]{83, 77, 50, 0};
        this.ret = sjy.SJY_Get_KeyIndex(alg, index);
        return this.ret;
    }

    public int SJY_SM2_Index_GenKeyPair(int index) {
        this.ret = -1;
        this.ret = sjy.SJY_SCE_Index_GenKeyPair(index);
        return this.ret;
    }

    public int SJY_JNI_AsyKey_Del(int index) {
        this.ret = -1;
        this.ret = sjy.SJY_AsyKey_Del(index);
        return this.ret;
    }

    public int SJY_JNI_Export_Pubkey(int index, byte[] pub, int[] pub_len) {
        this.ret = -1;
        this.ret = sjy.SJY_Get_ECC_Pubkey(index, pub);
        pub_len[0] = pub.length;
        return this.ret;
    }

    public int SJY_JNI_Wrap_Key(int kekmode, int kektype, int kekindex, int keklen, byte[] kek, int keymode, int keytype, int keyindex, int keylen, byte[] key, int[] wrapkeylen, byte[] wrapkey) {
        int mechanism = 0;
        int ivlen = 0;
        byte[] iv = null;
        this.ret = -1;
        this.ret = sjy.SJY_Wrap_Key(mechanism, (byte)kekmode, (byte)kektype, kekindex, keklen, kek, (byte)keymode, (byte)keytype, keyindex, keylen, key, ivlen, (byte[])iv, wrapkeylen, wrapkey);
        return this.ret;
    }

    public int SJY_JNI_UnWrap_Key(int kekmode, int kektype, int kekindex, int keklen, byte[] kek, int keymode, int keytype, int wrapkeylen, byte[] wrapkey, int[] keyindex, int[] unwrapkeylen, byte[] unwrapkey) {
        int mechanism = 0;
        int ivlen = 0;
        byte[] iv = null;
        this.ret = -1;
        this.ret = sjy.SJY_UnWrap_Key(mechanism, (byte)kekmode, (byte)kektype, kekindex, keklen, kek, (byte)keymode, (byte)keytype, ivlen, (byte[])iv, wrapkeylen, wrapkey, keyindex, unwrapkeylen, unwrapkey);
        return this.ret;
    }
}
