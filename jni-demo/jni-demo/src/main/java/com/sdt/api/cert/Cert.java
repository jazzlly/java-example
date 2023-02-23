//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sdt.api.cert;

import com.sdt.api.jni.JniApi;

public class Cert {
    private static JniApi sjy = new JniApi();
    int ret = -1;

    public Cert() {
    }

    public int SJY_JNI_GenReqP10(int index, byte[] SubjectDn, int iSubjectDnLen, int CertUseType, byte[] reqP10, int[] reqP10_len) {
        this.ret = -1;
        this.ret = sjy.SJY_JNI_GenReqP10(index, SubjectDn, iSubjectDnLen, CertUseType, reqP10, reqP10_len);
        return this.ret;
    }

    public int SJY_JNI_ParseCert(byte[] pCert, int uiCertLen, byte[] pCertNo, int[] uiCertNoLen, byte[] pOid, int[] uiOidLen, byte[] pBegintime, int[] uiBegintimeLen, byte[] PEndtime, int[] uiEndtimeLen, byte[] PSubjectDn, int[] uiSubjectDnLen, byte[] PubKey, int[] pubkeyLen) {
        this.ret = -1;
        this.ret = sjy.SJY_JNI_ParseCert(pCert, uiCertLen, pCertNo, uiCertNoLen, pOid, uiOidLen, pBegintime, uiBegintimeLen, PEndtime, uiEndtimeLen, PSubjectDn, uiSubjectDnLen, PubKey, pubkeyLen);
        return this.ret;
    }

    public int SJY_JNI_VerifyCert(byte[] pcCert, int iCertLen, byte[] pcRoot, int iRootLen) {
        this.ret = -1;
        this.ret = sjy.SJY_JNI_VerifyCert(pcCert, iCertLen, pcRoot, iRootLen);
        return this.ret;
    }

    public int SJY_JNI_ParseP12Cert(byte[] pucCertP12, int iCertP12Len, byte[] pKeyPin, int iKeyPin_len, int[] iKeyIndex, byte[] ucCertBuffer, int[] uiCertBufferLen) {
        this.ret = -1;
        this.ret = sjy.SJY_JNI_ParseP12Cert(pucCertP12, iCertP12Len, pKeyPin, iKeyPin_len, iKeyIndex, ucCertBuffer, uiCertBufferLen);
        return this.ret;
    }
}
