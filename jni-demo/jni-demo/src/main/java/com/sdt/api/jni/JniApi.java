//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sdt.api.jni;

public class JniApi {
    static {
        System.load("/usr/lib64/libsjy_x64.so");
        /*
        String OS = System.getProperty("sun.arch.data.model");
        String a = "64";
        String b = "32";
        if (OS.equals(a)) {
            System.loadLibrary("sjy_x64");
        } else if (OS.equals(b)) {
            System.loadLibrary("sjy_x86");
        }*/
    }

    public JniApi() {
    }

    public int SJY_Get_KeyIndex(byte[] pcKeyType, int[] index) {
        int ret = this.nSJY_Get_KeyIndex(pcKeyType, index);
        return ret;
    }

    public int SJY_SCE_Index_GenKeyPair(int index) {
        int ret = this.nSJY_SCE_Index_GenKeyPair(index);
        return ret;
    }

    public int SJY_AsyKey_Del(int index) {
        int ret = this.nSJY_AsyKey_Del(index);
        return ret;
    }

    public int SJY_Get_ECC_Pubkey(int index, byte[] pPubkey) {
        int ret = this.nSJY_Get_ECC_Pubkey(index, pPubkey);
        return ret;
    }

    public int SJY_SCE_External_ImportKeyPair(int uiKeyIndex, byte[] pPubKey, byte[] pPrivKey) {
        int ret = this.nSJY_SCE_External_ImportKeyPair(uiKeyIndex, pPubKey, pPrivKey);
        return ret;
    }

    public int SJY_SCE_External_GenPKeyBySkey(byte[] pPubKey, byte[] pPrivKey) {
        int ret = this.nSJY_SCE_External_GenPKeyBySkey(pPubKey, pPrivKey);
        return ret;
    }

    public int SJY_SCE_External_GenKeyPair(byte[] pPrivKey, byte[] pPubKey) {
        int ret = this.nSJY_SCE_External_GenKeyPair(pPrivKey, pPubKey);
        return ret;
    }

    public int SJY_SymKey_GetIndex(int[] puiKeyIndex) {
        int ret = this.nSJY_SymKey_GetIndex(puiKeyIndex);
        return ret;
    }

    public int SJY_Gen_Key_Index(int uiKeyIndex) {
        int ret = this.nSJY_Gen_Key_Index(uiKeyIndex);
        return ret;
    }

    public int SJY_SymKey_Del(int uiKeyIndex) {
        int ret = this.nSJY_SymKey_Del(uiKeyIndex);
        return ret;
    }

    public int SJY_Get_Key_Count(int[] puiCount) {
        int ret = this.nSJY_Get_Key_Count(puiCount);
        return ret;
    }

    public int SJY_Gen_Key(int uiKeyIndex, byte ucKeyLen, byte ucKeyType) {
        int ret = this.nSJY_Gen_Key(uiKeyIndex, ucKeyLen, ucKeyType);
        return ret;
    }

    public int SJY_Gen_Key_External(int uiKeyLen, byte[] pucKey) {
        int ret = this.nSJY_Gen_Key_External(uiKeyLen, pucKey);
        return ret;
    }

    public int SJY_Del_Index(int uiKeyType) {
        
        int ret = this.nSJY_Del_Index(uiKeyType);
        return ret;
    }

    public int SJY_Wrap_Key(int uiMechanism, byte ucKekMode, byte ucKekType, int uiKekIndex, int uiKekLen, byte[] pucKek, byte ucKeyMode, byte ucKeyType, int uiKeyIndex, int uiKeyLen, byte[] pucKey, int uiIvLen, byte[] pucIv, int[] puiWrapkeyLen, byte[] pucWrapkey) {
        
        int ret = this.nSJY_Wrap_Key(uiMechanism, ucKekMode, ucKekType, uiKekIndex, uiKekLen, pucKek, ucKeyMode, ucKeyType, uiKeyIndex, uiKeyLen, pucKey, uiIvLen, pucIv, puiWrapkeyLen, pucWrapkey);
        return ret;
    }

    public int SJY_UnWrap_Key(int uiMechanism, byte ucKekMode, byte ucKekType, int uiKekIndex, int uiKekLen, byte[] pucKek, byte ucKeyMode, byte ucKeyType, int uiIvLen, byte[] pucIv, int uiWrapkeyLen, byte[] pucWrapkey, int[] puiKeyIndex, int[] unwrapkeylen, byte[] unwrapkey) {
        
        int ret = this.nSJY_UnWrap_Key(uiMechanism, ucKekMode, ucKekType, uiKekIndex, uiKekLen, pucKek, ucKeyMode, ucKeyType, uiIvLen, pucIv, uiWrapkeyLen, pucWrapkey, puiKeyIndex, unwrapkeylen, unwrapkey);
        return ret;
    }

    public int Backup_ECCFile(byte[] pcFileName) {
        
        int ret = this.nBackup_ECCFile(pcFileName);
        return ret;
    }

    public int Restore_ECCFile(byte[] pcFileName) {
        
        int ret = this.nRestore_ECCFile(pcFileName);
        return ret;
    }

    public int Backup_SymFile(byte[] pcFileName) {
        
        int ret = this.nBackup_SymFile(pcFileName);
        return ret;
    }

    public int Restore_SymFile(byte[] pcFileName) {
        
        int ret = this.nRestore_SymFile(pcFileName);
        return ret;
    }

    public int SJY_Get_Random(int uiLen, byte[] pucRandom) {
        
        int ret = this.nSJY_Get_Random(uiLen, pucRandom);
        return ret;
    }

    public int SJY_SCE_Index_Encrypt(int uiKeyIndex, byte[] pucdata, int uiDataLen, byte[] pucEncData, int[] puiEncDataLen) {
        
        int ret = this.nSJY_SCE_Index_Encrypt(uiKeyIndex, pucdata, uiDataLen, pucEncData, puiEncDataLen);
        return ret;
    }

    public int SJY_SCE_Index_Decrypt(int uiKeyIndex, byte[] pucEncData, int uiEncDataLen, byte[] pucData, int[] puiDataLen) {
        
        int ret = this.nSJY_SCE_Index_Decrypt(uiKeyIndex, pucEncData, uiEncDataLen, pucData, puiDataLen);
        return ret;
    }

    public int SJY_SCE_Index_Sign(int uiKeyIndex, byte[] pucHash, byte[] pucSign) {
        
        int ret = this.nSJY_SCE_Index_Sign(uiKeyIndex, pucHash, pucSign);
        return ret;
    }

    public int SJY_SCE_Index_Verify(int uiKeyIndex, byte[] pucHash, byte[] pucSign) {
        
        int ret = this.nSJY_SCE_Index_Verify(uiKeyIndex, pucHash, pucSign);
        return ret;
    }

    public int SJY_Index_Encrypt(int uiKeyIndex, int uiAlgId, byte[] pucIv, byte[] pucData, int uiDataLen, byte[] pucEncData, int[] puiEncDataLen) {
        
        int ret = this.nSJY_Index_Encrypt(uiKeyIndex, uiAlgId, pucIv, pucData, uiDataLen, pucEncData, puiEncDataLen);
        return ret;
    }

    public int SJY_Index_Decrypt(int uiKeyIndex, int uiAlgId, byte[] pucIv, byte[] pucEncData, int uiEncDataLen, byte[] pucData, int[] puiDataLen) {
        
        int ret = this.nSJY_Index_Decrypt(uiKeyIndex, uiAlgId, pucIv, pucEncData, uiEncDataLen, pucData, puiDataLen);
        return ret;
    }

    public int SJY_External_Encrypt(byte[] pucKey, int uiKeyLen, int uiAlgId, byte[] pucIv, byte[] pucData, int uiDataLen, byte[] pucEncData, int[] puiEncDataLen) {
        
        int ret = this.nSJY_External_Encrypt(pucKey, uiKeyLen, uiAlgId, pucIv, pucData, uiDataLen, pucEncData, puiEncDataLen);
        return ret;
    }

    public int SJY_External_Decrypt(byte[] pucKey, int uiKeyLen, int uiAlgId, byte[] pucIv, byte[] pucEncData, int uiEncDataLen, byte[] pucData, int[] puiDataLen) {
        
        int ret = this.nSJY_External_Decrypt(pucKey, uiKeyLen, uiAlgId, pucIv, pucEncData, uiEncDataLen, pucData, puiDataLen);
        return ret;
    }

    public int SJY_SCE_External_Encrypt(byte[] pPubKey, byte[] pucdata, int uiDataLen, byte[] pucEncData, int[] puiEncDataLen) {
        
        int ret = this.nSJY_SCE_External_Encrypt(pPubKey, pucdata, uiDataLen, pucEncData, puiEncDataLen);
        return ret;
    }

    public int SJY_SCE_External_Decrypt(byte[] pPriKey, byte[] pucEncData, int uiEncDataLen, byte[] pucData, int[] puiDataLen) {
        
        int ret = this.nSJY_SCE_External_Decrypt(pPriKey, pucEncData, uiEncDataLen, pucData, puiDataLen);
        return ret;
    }

    public int SJY_SCE_External_Sign(byte[] pPriKey, byte[] pucHash, byte[] pucSign) {
        
        int ret = this.nSJY_SCE_External_Sign(pPriKey, pucHash, pucSign);
        return ret;
    }

    public int SJY_SCE_External_Verify(byte[] pPubKey, byte[] pucHash, byte[] pucSign) {
        
        int ret = this.nSJY_SCE_External_Verify(pPubKey, pucHash, pucSign);
        return ret;
    }

    public int SJY_SCE_SM3(byte[] pucMsg, int uiMsgLen, byte[] pucHash) {
        
        int ret = this.nSJY_SCE_SM3(pucMsg, uiMsgLen, pucHash);
        return ret;
    }

    public int Hash_Init(byte[] ctx, byte[] pucPublicKey) {
        
        int ret = this.nHash_Init(ctx, pucPublicKey);
        return ret;
    }

    public int Hash_Update(byte[] ctx, byte[] pucData, int uiDataLength) {
        
        int ret = this.nHash_Update(ctx, pucData, uiDataLength);
        return ret;
    }

    public int Hash_Final(byte[] ctx, byte[] pucHash, int[] puiHashLength) {
        
        int ret = this.nHash_Final(ctx, pucHash, puiHashLength);
        return ret;
    }

    public int SJY_SM3_MAC(byte[] pData, int uiDatalen, int uiKeyIndex, int[] puiMacLen, byte[] pucMac) {
        
        int ret = this.nSJY_SM3_MAC(pData, uiDatalen, uiKeyIndex, puiMacLen, pucMac);
        return ret;
    }

    public int SJY_JNI_GenReqP10(int index, byte[] SubjectDn, int iSubjectDnLen, int CertUseType, byte[] reqP10, int[] reqP10_len) {
        
        int ret = this.nSJY_JNI_GenReqP10(index, SubjectDn, iSubjectDnLen, CertUseType, reqP10, reqP10_len);
        return ret != 0 ? ret : 0;
    }

    public int SJY_JNI_ParseCert(byte[] pCert, int uiCertLen, byte[] pCertNo, int[] uiCertNoLen, byte[] pOid, int[] uiOidLen, byte[] pBegintime, int[] uiBegintimeLen, byte[] PEndtime, int[] uiEndtimeLen, byte[] PSubjectDn, int[] uiSubjectDnLen, byte[] PubKey, int[] pubkeyLen) {
        
        int ret = this.nSJY_JNI_ParseCert(pCert, uiCertLen, pCertNo, uiCertNoLen, pOid, uiOidLen, pBegintime, uiBegintimeLen, PEndtime, uiEndtimeLen, PSubjectDn, uiSubjectDnLen, PubKey, pubkeyLen);
        return ret != 0 ? ret : 0;
    }

    public int SJY_JNI_VerifyCert(byte[] pcCert, int iCertLen, byte[] pcRoot, int iRootLen) {
        
        int ret = this.nSJY_JNI_VerifyCert(pcCert, iCertLen, pcRoot, iRootLen);
        return ret != 0 ? ret : 0;
    }

    public int SJY_JNI_ParseP12Cert(byte[] pucCertP12, int iCertP12Len, byte[] pKeyPin, int iKeyPin_len, int[] iKeyIndex, byte[] ucCertBuffer, int[] uiCertBufferLen) {
        
        int ret = this.nSJY_JNI_ParseP12Cert(pucCertP12, iCertP12Len, pKeyPin, iKeyPin_len, iKeyIndex, ucCertBuffer, uiCertBufferLen);
        return ret != 0 ? ret : 0;
    }

    public int SJY_SYM_MAC(int uiAlgID, int uiKeyIndex, byte[] IV, byte[] pucData, int uiDataLen, int[] puiMacLen, byte[] pucMac) {
        
        int ret = this.nSJY_SYM_MAC(uiAlgID, uiKeyIndex, IV, pucData, uiDataLen, puiMacLen, pucMac);
        return ret != 0 ? ret : 0;
    }

    private native int nSJY_Get_KeyIndex(byte[] var1, int[] var2);

    private native int nSJY_SCE_Index_GenKeyPair(int var1);

    private native int nSJY_AsyKey_Del(int var1);

    private native int nSJY_Get_ECC_Pubkey(int var1, byte[] var2);

    private native int nSJY_SCE_External_ImportKeyPair(int var1, byte[] var2, byte[] var3);

    private native int nSJY_SCE_External_GenPKeyBySkey(byte[] var1, byte[] var2);

    private native int nSJY_SCE_External_GenKeyPair(byte[] var1, byte[] var2);

    private native int nSJY_SymKey_GetIndex(int[] var1);

    private native int nSJY_Gen_Key_Index(int var1);

    private native int nSJY_SymKey_Del(int var1);

    private native int nSJY_Get_Key_Count(int[] var1);

    private native int nSJY_Gen_Key(int var1, byte var2, byte var3);

    private native int nSJY_Gen_Key_External(int var1, byte[] var2);

    private native int nSJY_Del_Index(int var1);

    private native int nSJY_Wrap_Key(int var1, byte var2, byte var3, int var4, int var5, byte[] var6, byte var7, byte var8, int var9, int var10, byte[] var11, int var12, byte[] var13, int[] var14, byte[] var15);

    private native int nSJY_UnWrap_Key(int var1, byte var2, byte var3, int var4, int var5, byte[] var6, byte var7, byte var8, int var9, byte[] var10, int var11, byte[] var12, int[] var13, int[] var14, byte[] var15);

    private native int nBackup_ECCFile(byte[] var1);

    private native int nRestore_ECCFile(byte[] var1);

    private native int nBackup_SymFile(byte[] var1);

    private native int nRestore_SymFile(byte[] var1);

    private native int nSJY_Get_Random(int var1, byte[] var2);

    private native int nSJY_SCE_Index_Encrypt(int var1, byte[] var2, int var3, byte[] var4, int[] var5);

    private native int nSJY_SCE_Index_Decrypt(int var1, byte[] var2, int var3, byte[] var4, int[] var5);

    private native int nSJY_SCE_Index_Sign(int var1, byte[] var2, byte[] var3);

    private native int nSJY_SCE_Index_Verify(int var1, byte[] var2, byte[] var3);

    private native int nSJY_Index_Encrypt(int var1, int var2, byte[] var3, byte[] var4, int var5, byte[] var6, int[] var7);

    private native int nSJY_Index_Decrypt(int var1, int var2, byte[] var3, byte[] var4, int var5, byte[] var6, int[] var7);

    private native int nSJY_External_Encrypt(byte[] var1, int var2, int var3, byte[] var4, byte[] var5, int var6, byte[] var7, int[] var8);

    private native int nSJY_External_Decrypt(byte[] var1, int var2, int var3, byte[] var4, byte[] var5, int var6, byte[] var7, int[] var8);

    private native int nSJY_SCE_External_Encrypt(byte[] var1, byte[] var2, int var3, byte[] var4, int[] var5);

    private native int nSJY_SCE_External_Decrypt(byte[] var1, byte[] var2, int var3, byte[] var4, int[] var5);

    private native int nSJY_SCE_External_Sign(byte[] var1, byte[] var2, byte[] var3);

    private native int nSJY_SCE_External_Verify(byte[] var1, byte[] var2, byte[] var3);

    private native int nSJY_SCE_SM3(byte[] var1, int var2, byte[] var3);

    private native int nHash_Init(byte[] var1, byte[] var2);

    private native int nHash_Update(byte[] var1, byte[] var2, int var3);

    private native int nHash_Final(byte[] var1, byte[] var2, int[] var3);

    private native int nSJY_SM3_MAC(byte[] var1, int var2, int var3, int[] var4, byte[] var5);

    private native int nSJY_JNI_GenReqP10(int var1, byte[] var2, int var3, int var4, byte[] var5, int[] var6);

    private native int nSJY_JNI_ParseCert(byte[] var1, int var2, byte[] var3, int[] var4, byte[] var5, int[] var6, byte[] var7, int[] var8, byte[] var9, int[] var10, byte[] var11, int[] var12, byte[] var13, int[] var14);

    private native int nSJY_JNI_VerifyCert(byte[] var1, int var2, byte[] var3, int var4);

    private native int nSJY_JNI_ParseP12Cert(byte[] var1, int var2, byte[] var3, int var4, int[] var5, byte[] var6, int[] var7);

    private native int nSJY_SYM_MAC(int var1, int var2, byte[] var3, byte[] var4, int var5, int[] var6, byte[] var7);
}
