package com.sdt.SJYjniTest;

import com.sdt.api.asyalg.AsyAlgorithm;
import com.sdt.api.hash.Hash;
import com.sdt.api.jni.JniApi;
import com.sdt.api.keymanage.KeyManager;
import com.sdt.api.symalg.SymAlgorithm;

import java.util.Arrays;

public class Smoke {

    private static KeyManager km = new KeyManager();
    private static Hash hs = new Hash();
    private static AsyAlgorithm aa = new AsyAlgorithm();
    private static JniApi ja = new JniApi();
    private static SymAlgorithm sa = new SymAlgorithm();

    public static void main(String[] args) {
        System.out.println("wahaha");
        Test_SJY_Get_Sm2Index();
        System.out.println("hahaha");
    }

    private static int Test_SJY_Get_Sm2Index ()
    {
        int iRet = -1; //返回值
        int index[] = new int[10]; //输出，密钥索引号
        iRet = km.SJY_Get_Sm2Index(index);

        System.out.printf(" SJY_Get_Sm2Index iRet=%08x\n",iRet);
        System.out.println(Arrays.toString(index));

        return iRet;
    }
}
