//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sdt.api.hash;

import com.sdt.api.jni.JniApi;

public class Hash {
    private static JniApi sjy = new JniApi();
    int ret = -1;

    public Hash() {
    }

    public int SJY_JNI_SM3_Hash(byte[] msg, int msglen, byte[] hash, int[] hash_len) {
        this.ret = -1;
        this.ret = sjy.SJY_SCE_SM3(msg, msglen, hash);
        return this.ret;
    }

    public int JNI_Hash_Init(byte[] ctx, byte[] pucPublicKey) {
        this.ret = -1;
        this.ret = sjy.Hash_Init(ctx, pucPublicKey);
        return this.ret;
    }

    public int JNI_Hash_Update(byte[] ctx, byte[] pucData, int uiDataLength) {
        this.ret = -1;
        this.ret = sjy.Hash_Update(ctx, pucData, uiDataLength);
        return this.ret;
    }

    public int JNI_Hash_Final(byte[] ctx, byte[] pucHash, int[] puiHashLength) {
        this.ret = -1;
        this.ret = sjy.Hash_Final(ctx, pucHash, puiHashLength);
        return this.ret;
    }
}
