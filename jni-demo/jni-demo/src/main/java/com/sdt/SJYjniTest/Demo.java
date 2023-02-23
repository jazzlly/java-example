package com.sdt.SJYjniTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import com.sdt.api.asyalg.AsyAlgorithm;
import com.sdt.api.cert.Cert;
import com.sdt.api.hash.Hash;
import com.sdt.api.jni.JniApi;
import com.sdt.api.keymanage.KeyManager;
import com.sdt.api.symalg.SymAlgorithm;

public class Demo {
		public static int SGD_SM1_ECB = 0x00000101;
		public static int SGD_SM1_CBC = 0x00000102;
		public static int SGD_SM4_ECB = 0x00000401;
		public static int SGD_SM4_CBC = 0x00000402;
		public static int SGD_SM3     = 0x00000001;
		
		public static int MAIN_KEY_INDEX = 100001;
		int ret = -1;
		int asy_index[] = new int[1];
		int sym_index[] = new int[1];
		int sym_kek_index[] = {0};
		int asy_kek_index[] = new int[1];
		byte inputData[] = "input_data_test_".getBytes();
		int inputData_len = inputData.length;
		byte sym_enData[] = new byte[inputData_len];
		int sym_enData_len[] = {inputData_len};
		byte outputData[] = new byte[inputData.length];
		int outdata_len[] = {inputData.length};
		byte sym_plainData[] = new byte[inputData.length];
		int sym_plainData_len[] = {inputData.length};
		byte asy_outputData[] = new byte[inputData.length+96];
		int asy_outputData_len[] = {asy_outputData.length};
		byte asy_plainData[] = new byte[inputData.length];
		int asy_plainData_len[] = {inputData.length};
		byte hashOut[] = new byte[32];
		int hashOutLen[] = {32};
		byte signOut[] = new byte[64];
		int signOutLen[] = {64};
		byte IV[] = {(byte)0x01,(byte)0x2c,(byte)0x1c,(byte)0x15,(byte)0x33,(byte)0x91,
				     (byte)0x06,(byte)0x43,(byte)0x55,(byte)0x54,(byte)0x32,(byte)0x31,
				     (byte)0x22,(byte)0x56,(byte)0x1d,(byte)0xaa};
		int iv_len = 16;
		byte sym_outside_key[] = {(byte)0x04,(byte)0x7b,(byte)0x1e,(byte)0x10,(byte)0x47,(byte)0x92,
								  (byte)0x61,(byte)0x54,(byte)0x53,(byte)0x56,(byte)0x14,(byte)0x34,
								  (byte)0x46,(byte)0x41,(byte)0xef,(byte)0x2a};
		int sym_outside_key_len = sym_outside_key.length;
		
		byte sym_outside_kek[] = {(byte)0x12,(byte)0x7b,(byte)0x3a,(byte)0x10,(byte)0x47,(byte)0x92,
								  (byte)0x63,(byte)0x55,(byte)0x53,(byte)0x56,(byte)0x99,(byte)0x50,
								  (byte)0x55,(byte)0x69,(byte)0x2d,(byte)0x12};
		int sym_outside_kek_len[] = {sym_outside_kek.length};
		
		byte asy_pri[] = {(byte)0x01,(byte)0x2c,(byte)0x1c,(byte)0x15,(byte)0x33,(byte)0x91,
					      (byte)0x06,(byte)0x43,(byte)0x55,(byte)0x54,(byte)0x32,(byte)0x31,
					      (byte)0x22,(byte)0x56,(byte)0x1d,(byte)0xaa,(byte)0x33,(byte)0x91,
					      (byte)0x06,(byte)0x43,(byte)0x55,(byte)0x54,(byte)0x32,(byte)0x31,
					      (byte)0x22,(byte)0x56,(byte)0x1d,(byte)0xaa,(byte)0x01,(byte)0x2c,
					      (byte)0x1c,(byte)0x15};
		int asy_pri_len = asy_pri.length;
		
		byte asy_pub_outside_key[] = {(byte)0X00, (byte)0X01, (byte)0X00, (byte)0X00, 
									  (byte)0XEB, (byte)0X93, (byte)0X3B, (byte)0XBB, 
							          (byte)0X05, (byte)0XBE, (byte)0X35, (byte)0XBA, 
							          (byte)0X05, (byte)0XD9, (byte)0XD9, (byte)0XC1, 
							          (byte)0X77, (byte)0XE3, (byte)0X14, (byte)0XEE,
							          (byte)0X5B, (byte)0X86, (byte)0X97, (byte)0X6F, 
							          (byte)0X70, (byte)0X3E, (byte)0XFA, (byte)0X04, 
							          (byte)0XA5, (byte)0XC0, (byte)0X4E, (byte)0X85, 
							          (byte)0XE8, (byte)0X91, (byte)0XB8, (byte)0X05,
							          (byte)0X78, (byte)0XC2, (byte)0X2B, (byte)0XE4, 
							          (byte)0X87, (byte)0X26, (byte)0X7C, (byte)0XE4, 
							          (byte)0X3B, (byte)0XAB, (byte)0X61, (byte)0X6C, 
							          (byte)0XC3, (byte)0X4B, (byte)0XE6, (byte)0X71,
							          (byte)0X7F, (byte)0X36, (byte)0XDD, (byte)0XFD, 
							          (byte)0X54, (byte)0X35, (byte)0X1E, (byte)0XB0,
							          (byte)0X39, (byte)0XA2, (byte)0XE1, (byte)0XEB, 
							          (byte)0XCB, (byte)0X67, (byte)0X18, (byte)0X8A};
		int asy_pub_outside_key_len = asy_pub_outside_key.length;
		
		byte asy_pri_outside_key[] = {(byte)0X00, (byte)0X01, (byte)0X00, (byte)0X00,
									  (byte)0X84, (byte)0XD9, (byte)0X34, (byte)0XDA, 
									  (byte)0X0A, (byte)0X06, (byte)0X9D, (byte)0X91, 
									  (byte)0X85, (byte)0X9B, (byte)0X12, (byte)0X0E, 
									  (byte)0XB4, (byte)0X9F, (byte)0X29, (byte)0XE1, 
									  (byte)0XB6, (byte)0X07, (byte)0XB8, (byte)0X26, 
									  (byte)0X6D, (byte)0X1B, (byte)0X56, (byte)0XF9, 
									  (byte)0XA5, (byte)0X6D, (byte)0XF6, (byte)0XBB, 
									  (byte)0X94, (byte)0XF8, (byte)0X60, (byte)0X4F};
		int asy_pri_outside_key_len = asy_pri_outside_key.length;
		
		byte asy_outside_kek[] = {(byte)0x12,(byte)0x7b,(byte)0x3a,(byte)0x10,(byte)0x47,(byte)0x92,
								  (byte)0x63,(byte)0x55,(byte)0x53,(byte)0x56,(byte)0x99,(byte)0x50,
								  (byte)0x55,(byte)0x69,(byte)0x2d,(byte)0x12};
		int asy_outside_kek_len[] = {asy_outside_kek.length}; 
		byte sym_warpkey[] = new byte[32];
		int sym_warpkey_len[] = {32}; 
		byte sym_unwarpkey[] = new byte[16];
		int sym_unwarpkey_len[] = {16}; 
		byte asy_warpkey[] = new byte[48];
		int asy_warpkey_len[] = {48}; 
		byte asy_unwarpkey[] = new byte[48];
		int asy_unwarpkey_len[] = {48};
		byte MAC_32[] = new byte[32];
		int MAC_32_len[] ={32}; 
		byte MAC_16[] = new byte[16];
		int MAC_16_len[] ={16}; 
		byte cxt[] = new byte[256];
		byte pubKey[] = new byte[68];
		int pubKey_len[] = {68};
		boolean result = false;
		int asy_unwarpedkey_index[] = {0};
		int sym_unwarpedkey_index[] = {0};
		
		byte[] SubjectDn = "/C=CN/ST=shanxi/L=xian/O=hangye/OU=sdt/CN=testuser/emailAddress=123@163.com\0".getBytes();
		int iSubjectDnLen = SubjectDn.length;
		int encyptCertType = 0;
		int signCertType = 1;
		byte[] reqP10 = new byte[2048];
		int[] reqP10_len = {reqP10.length};
		
		byte[] pCert;
		int uiCertLen;
		byte[]  pCertNo = new byte[256]; 
		int[] uiCertNoLen = {0};
		byte[]  pOid = new byte[256];
		int[] uiOidLen = {0}; 
		byte[] pBegintime = new byte[256];
		int[] uiBegintimeLen = {0};
		byte[] PEndtime = new byte[256]; 	
		int[] uiEndtimeLen = {0};
		byte[] PSubjectDn = new byte[1024];
		int[] uiSubjectDnLen = {0}; 
		byte[] PubKey = new byte[128];
		int[] pubkeyLen = {0};
		
		byte[] pcCert;
		int iCertLen;
		byte[] pcRoot; 
		int iRootLen;
		
		byte[]  pucCertP12;
		int iCertP12Len; 
		byte[]  pKeyPin = "11111111".getBytes();
		int iKeyPin_len = pKeyPin.length;
		int[] iKeyIndex = {0};
		byte[] ucCertBuffer = new byte[1024];
		int[] uiCertBufferLen = {ucCertBuffer.length};
		
		public static void main(String []args) {
			Demo demo = new Demo();
			demo.Smoking(1);
		}
		
		public static boolean compair(byte[] first, byte[] second){
			boolean result = false;
			result = Arrays.equals(first, second);
			return result;
		}
		
		public void Smoking(int round){
			for(int count=0; count<round; count ++){	
				
				System.out.println( "==========SMOKING_TEST_ROUND"+(count+1)+"_START==========");
				/******************KeyManager******************/
				KeyManager km = new KeyManager();
				Hash hs = new Hash();
				AsyAlgorithm aa = new AsyAlgorithm();
				JniApi ja = new JniApi();
				SymAlgorithm sa = new SymAlgorithm();
				
				//1
				ret = km.SJY_Get_Sm2Index(asy_index);
				System.out.println(ret + "SJY_Get_Sm2Index");
				
				//2
				ret = km.SJY_SM2_Index_GenKeyPair(asy_index[0]);
				System.out.println(ret + "SJY_SM2_Index_GenKeyPair");
				
				//4
				ret = km.SJY_JNI_Export_Pubkey(asy_index[0], pubKey, pubKey_len);
				System.out.println(ret + "SJY_JNI_Export_Pubkey");
				
				//34
				ret = hs.SJY_JNI_SM3_Hash(inputData, inputData_len, hashOut, hashOutLen);
				System.out.println(ret + "SJY_JNI_SM3_Hash");
				
				//1
				ret = aa.SJY_JNI_Index_Sign(asy_index[0], hashOut, hashOutLen[0], signOut, signOutLen);
				System.out.println(ret + "SJY_JNI_Index_Sign");
					
				//2
				ret = aa.SJY_JNI_External_Verify(pubKey, hashOut, hashOutLen[0], signOut, signOutLen[0]);
				System.out.println(ret + "SJY_JNI_External_Verify");
				
				//1 SM1_ECB
				ret = sa.SJY_JNI_External_Encrypt(sym_outside_key, sym_outside_key_len, SGD_SM1_ECB,
												  IV, inputData, inputData_len, sym_enData, sym_enData_len);
				System.out.println(ret + "SJY_JNI_External_Encrypt_SGD_SM1_ECB");
				
				//2 SM1_ECB
				ret = sa.SJY_JNI_External_Decrypt(sym_outside_key, sym_outside_key_len, SGD_SM1_ECB, 
												IV, sym_enData, sym_enData_len[0], sym_plainData, sym_plainData_len);
				System.out.println(ret + "SJY_JNI_External_Decrypt_SGD_SM1_ECB");
				
				result = compair(sym_plainData, inputData);
				if(result){
					System.out.println( "OUTSIDE_KEY_SGD_SM1_ECB plainData and inputData is equals!");
				}
				else{
					System.out.println( "OUTSIDE_KEY_SGD_SM1_ECB plainData and inputData is not equals!");
				}
				
				//1  SGD_SM1_CBC
				ret = sa.SJY_JNI_External_Encrypt(sym_outside_key, sym_outside_key_len, SGD_SM1_CBC,
												  IV, inputData, inputData_len, sym_enData, sym_enData_len);
				System.out.println(ret + "SJY_JNI_External_Encrypt_SGD_SM1_CBC");
				
				//2  SGD_SM1_CBC
				ret = sa.SJY_JNI_External_Decrypt(sym_outside_key, sym_outside_key_len, SGD_SM1_CBC, 
												IV, sym_enData, sym_enData_len[0], sym_plainData, sym_plainData_len);
				System.out.println(ret + "SJY_JNI_Index_Encrypt_SGD_SM1_CBC");
				
				result = compair(sym_plainData, inputData);
				if(result){
					System.out.println( "OUTSIDE_KEY_SGD_SM1_CBC plainData and decryptData is equals!");
				}
				else{
					System.out.println( "OUTSIDE_KEY_SGD_SM1_CBC plainData and decryptData is not equals!");
				}
				
				//1  SGD_SM4_ECB
				ret = sa.SJY_JNI_External_Encrypt(sym_outside_key, sym_outside_key_len, SGD_SM4_ECB,
												  IV, inputData, inputData_len, sym_enData, sym_enData_len);
				System.out.println(ret + "SJY_JNI_External_Encrypt_SGD_SM4_ECB");
				
				//2  SGD_SM4_ECB
				ret = sa.SJY_JNI_External_Decrypt(sym_outside_key, sym_outside_key_len, SGD_SM4_ECB, 
												IV, sym_enData, sym_enData_len[0], sym_plainData, sym_plainData_len);
				System.out.println(ret + "SJY_JNI_Index_Encrypt_SGD_SM4_ECB");
				
				result = compair(sym_plainData, inputData);
				if(result){
					System.out.println( "OUTSIDE_KEY_SGD_SM4_ECB plainData and decryptData is equals!");
				}
				else{
					System.out.println( "OUTSIDE_KEY_SGD_SM4_ECB plainData and decryptData is not equals!");
				}
				
				//1  SGD_SM4_CBC
				ret = sa.SJY_JNI_External_Encrypt(sym_outside_key, sym_outside_key_len, SGD_SM4_CBC,
												  IV, inputData, inputData_len, sym_enData, sym_enData_len);
				System.out.println(ret + "SJY_JNI_External_Encrypt_SGD_SM4_CBC");
				
				//2  SGD_SM4_CBC
				ret = sa.SJY_JNI_External_Decrypt(sym_outside_key, sym_outside_key_len, SGD_SM4_CBC, 
												IV, sym_enData, sym_enData_len[0], sym_plainData, sym_plainData_len);
				System.out.println(ret + "SJY_JNI_Index_Encrypt_SGD_SM4_CBC");
				
				result = compair(sym_plainData, inputData);
				if(result){
					System.out.println( "OUTSIDE_KEY_SGD_SM4_CBC plainData and decryptData is equals!");
				}
				else{
					System.out.println( "OUTSIDE_KEY_SGD_SM4_CBC plainData and decryptData is not equals!");
				}
				
				/**************************AsyAlgorithm****************************/
				
				//3
				ret = aa.SJY_JNI_SCE_External_Encrypt(asy_pub_outside_key, inputData, inputData_len, asy_outputData, asy_outputData_len);
				System.out.println(ret + "SJY_JNI_SCE_External_Encrypt");
				
				//4
				ret = aa.SJY_JNI_SCE_External_Decrypt(asy_pri_outside_key, asy_outputData, asy_outputData_len[0], asy_plainData, asy_plainData_len);
				System.out.println(ret + "SJY_JNI_SCE_External_Decrypt");
				
				result = compair(asy_plainData, inputData);
				if(result){
					System.out.println( "plainData and decryptData is equals! External_Encrypt and External_Decrypt ");
				}
				else{
					System.out.println( "plainData and decryptData is not equals! External_Encrypt and External_Decrypt ");
				}
				
				/*********SJY_JNI_Wrap_Key ( kekmode = 1 && keymode = 1 && keytype = 11)**********/
				
				//8
				ret = ja.SJY_SymKey_GetIndex(sym_kek_index);
				System.out.println(ret + "SJY_SymKey_GetIndex");
				
				//9
				ret = ja.SJY_Gen_Key_Index(sym_kek_index[0]);
				System.out.println(ret + "SJY_Gen_Key_Index");
				
				//5
				ret = km.SJY_JNI_Wrap_Key(1, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  1, 11, asy_index[0], asy_pri_outside_key_len, asy_pri_outside_key,   
										  asy_warpkey_len, asy_warpkey);
				System.out.println(ret + "SJY_JNI_Wrap_Key [kekmode = 1 && keymode = 1 && keytype = 11]");
				
				//1
				ret = km.SJY_Get_Sm2Index(asy_unwarpedkey_index);
				System.out.println(ret + "SJY_Get_Sm2Index");
				
				//6
				ret = km.SJY_JNI_UnWrap_Key(1, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										    1, 11, asy_warpkey_len[0], asy_warpkey, asy_unwarpedkey_index, 
										    asy_unwarpkey_len, asy_unwarpkey);
				System.out.println(ret + "SJY_JNI_UnWrap_Key [kekmode = 1 && keymode = 1 && keytype = 11]");
				
				/***********************SJY_JNI_Wrap_Key (kekmode = 0 && keymode = 0 && keytype = 11)**********************************/
				
				//5
				ret = km.SJY_JNI_Wrap_Key(0, 15, asy_kek_index[0], asy_outside_kek_len[0], asy_outside_kek,
										  0, 11, asy_index[0], asy_pri_len, asy_pri,  
										  asy_warpkey_len, asy_warpkey);
				System.out.println(ret + "SJY_JNI_Wrap_Key [kekmode = 0 && keymode = 0 && keytype = 11]");
				
				//6
				ret = km.SJY_JNI_UnWrap_Key(0, 15, asy_kek_index[0], asy_outside_kek_len[0], asy_outside_kek,
										    0, 11, asy_warpkey_len[0], asy_warpkey, asy_unwarpedkey_index, 
										    asy_unwarpkey_len, asy_unwarpkey);
				System.out.println(ret + "SJY_JNI_UnWrap_Key [kekmode = 0 && keymode = 0 && keytype = 11]");
				
		/***********************SJY_JNI_Wrap_Key (kekmode = 0 && keymode = 1 && keytype = 11)**********************************/
				
				//5
				ret = km.SJY_JNI_Wrap_Key(0, 15, asy_kek_index[0], asy_outside_kek_len[0], asy_outside_kek,
										  1, 11, asy_index[0], asy_pri_len, asy_pri,  
										  asy_warpkey_len, asy_warpkey);
				System.out.println(ret + "SJY_JNI_Wrap_Key [kekmode = 0 && keymode = 1 && keytype = 11]");
				
				//6
				ret = km.SJY_JNI_UnWrap_Key(0, 15, asy_kek_index[0], asy_outside_kek_len[0], asy_outside_kek,
										    1, 11, asy_warpkey_len[0], asy_warpkey, asy_unwarpedkey_index, 
										    asy_unwarpkey_len, asy_unwarpkey);
				System.out.println(ret + "SJY_JNI_UnWrap_Key [kekmode = 0 && keymode = 1 && keytype = 11]");
				
		/***********************SJY_JNI_Wrap_Key (kekmode = 1 && keymode = 0 && keytype = 11)**********************************/
				
				//5
				ret = km.SJY_JNI_Wrap_Key(1, 15, sym_kek_index[0], asy_outside_kek_len[0], asy_outside_kek,
										  0, 11, asy_index[0], asy_pri_len, asy_pri,  
										  asy_warpkey_len, asy_warpkey);
				System.out.println(ret + "SJY_JNI_Wrap_Key [kekmode = 1 && keymode = 0 && keytype = 11]");
				
				//6
				ret = km.SJY_JNI_UnWrap_Key(1, 15, asy_kek_index[0], asy_outside_kek_len[0], asy_outside_kek,
										    0, 11, asy_warpkey_len[0], asy_warpkey, asy_unwarpedkey_index, 
										    asy_unwarpkey_len, asy_unwarpkey);
				System.out.println(ret + "SJY_JNI_UnWrap_Key [kekmode = 1 && keymode = 0 && keytype = 11]");
				
				
				/****************hash******************/
				
				//1
				ret = hs.SJY_JNI_SM3_Hash(inputData, inputData_len, hashOut, hashOutLen);
				System.out.println(ret + "SJY_JNI_SM3_Hash");
				
				//2
				ret = hs.JNI_Hash_Init(cxt, pubKey);
				System.out.println(ret + "JNI_Hash_Init");
				
				//2
				ret = hs.JNI_Hash_Init(cxt, null);
				System.out.println(ret + "JNI_Hash_Init pk = null");
				
				//2
				ret = hs.JNI_Hash_Init(cxt, "".getBytes());
				System.out.println(ret + "JNI_Hash_Init pk = getBytes()");
				
				//3
				ret = hs.JNI_Hash_Update(cxt, inputData, inputData_len);
				System.out.println(ret + "JNI_Hash_Update");
				
				//4
				ret = hs.JNI_Hash_Final(cxt, hashOut, hashOutLen);
				System.out.println(ret + "JNI_Hash_Final");
				/**************************SymAlgorithm****************************/
				
				//8
				ret = ja.SJY_SymKey_GetIndex(sym_index);
				System.out.println(ret + "SJY_JNI_SymKey_GetIndex");
				
				//9
				ret = ja.SJY_Gen_Key_Index(sym_index[0]);
				System.out.println(ret + "SJY_JNI_Gen_Key_Index");
				
				
				// 3 mac(mac32)
				ret = sa.SJY_JNI_SM3_MAC(inputData, inputData_len, sym_index[0], MAC_32_len, MAC_32);
				System.out.println(ret + "SJY_JNI_SM3_MAC MAC_len == 32");
				
				/***********************SJY_JNI_Wrap_Key (kekmode = 1 && keymode = 1 && keytype = 8)**********************************/
				
				ret = ja.SJY_SymKey_GetIndex(sym_unwarpedkey_index);
				System.out.println(ret + "SJY_JNI_SymKey_GetIndex");
				
				//5
				ret = km.SJY_JNI_Wrap_Key(1, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  1, 8, sym_index[0], sym_outside_key_len, sym_outside_key,  sym_warpkey_len, sym_warpkey);
				System.out.println(ret + "SJY_JNI_Wrap_Key [kekmode = 1 && keymode = 1 && keytype = 8]");
				
				//6
				ret = km.SJY_JNI_UnWrap_Key(1, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  1, 8, sym_warpkey_len[0], sym_warpkey, sym_unwarpedkey_index, sym_unwarpkey_len, sym_unwarpkey);
				System.out.println(ret + "SJY_JNI_UnWrap_Key [kekmode = 1 && keymode = 1 && keytype = 8]");
				
				
				/***********************SJY_JNI_Wrap_Key (kekmode = 0 && keymode = 0 && keytype = 8)**********************************/
				//5
				ret = km.SJY_JNI_Wrap_Key(0, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  0, 8, sym_index[0], sym_outside_key_len, sym_outside_key,  sym_warpkey_len, sym_warpkey);
				System.out.println(ret +"SJY_JNI_Wrap_Key [kekmode = 0 && keymode = 0 && keytype = 8]");
				
				//6
				ret = km.SJY_JNI_UnWrap_Key(0, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  	0, 8, sym_warpkey_len[0], sym_warpkey, sym_unwarpedkey_index, sym_unwarpkey_len, sym_unwarpkey);
				System.out.println(ret +"SJY_JNI_UnWrap_Key [kekmode = 0 && keymode = 0 && keytype = 8]");
				
				/***********************SJY_JNI_Wrap_Key (kekmode = 1 && keymode = 0 && keytype = 8)**********************************/
				//5
				ret = km.SJY_JNI_Wrap_Key(1, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  0, 8, sym_index[0], sym_outside_key_len, sym_outside_key,  sym_warpkey_len, sym_warpkey);
				System.out.println(ret +"SJY_JNI_Wrap_Key [kekmode = 1 && keymode = 0 && keytype = 8]");
				
				//6
				ret = km.SJY_JNI_UnWrap_Key(1, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  	0, 8, sym_warpkey_len[0], sym_warpkey, sym_unwarpedkey_index, sym_unwarpkey_len, sym_unwarpkey);
				System.out.println(ret +"SJY_JNI_UnWrap_Key [kekmode = 1 && keymode = 0 && keytype = 8]");
				
				/***********************SJY_JNI_Wrap_Key (kekmode = 0 && keymode = 1 && keytype = 8)**********************************/
				//5
				ret = km.SJY_JNI_Wrap_Key(0, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  1, 8, sym_index[0], sym_outside_key_len, sym_outside_key,  sym_warpkey_len, sym_warpkey);
				System.out.println(ret +"SJY_JNI_Wrap_Key [kekmode = 0 && keymode = 1 && keytype = 8]");
				
				//6
				ret = km.SJY_JNI_UnWrap_Key(0, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  	1, 8, sym_warpkey_len[0], sym_warpkey, sym_unwarpedkey_index, sym_unwarpkey_len, sym_unwarpkey);
				System.out.println(ret +"SJY_JNI_UnWrap_Key [kekmode = 0 && keymode = 1 && keytype = 8]");
				
				/***********************SJY_JNI_Wrap_Key (kekmode = 1 && keymode = 1 && keytype = 15)**********************************/
				//5
				ret = km.SJY_JNI_Wrap_Key(1, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  1, 15, sym_index[0], sym_outside_key_len, sym_outside_key,  sym_warpkey_len, sym_warpkey);
				System.out.println(ret +"SJY_JNI_Wrap_Key [kekmode = 1 && keymode = 1 && keytype = 15]");
				
				//6
				ret = km.SJY_JNI_UnWrap_Key(1, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  1, 15, sym_warpkey_len[0], sym_warpkey, sym_unwarpedkey_index, sym_unwarpkey_len, sym_unwarpkey);
				System.out.println(ret +"SJY_JNI_UnWrap_Key [kekmode = 1 && keymode = 1 && keytype = 15]");
				
				/***********************SJY_JNI_Wrap_Key (kekmode = 0 && keymode = 0 && keytype = 15)**********************************/
				//5
				ret = km.SJY_JNI_Wrap_Key(0, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  0, 15, sym_index[0], sym_outside_key_len, sym_outside_key,  sym_warpkey_len, sym_warpkey);
				System.out.println(ret +"SJY_JNI_Wrap_Key [kekmode = 0 && keymode = 0 && keytype = 15]");
				
				//6
				ret = km.SJY_JNI_UnWrap_Key(0, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  	0, 15, sym_warpkey_len[0], sym_warpkey, sym_unwarpedkey_index, sym_unwarpkey_len, sym_unwarpkey);
				System.out.println(ret +"SJY_JNI_UnWrap_Key [kekmode = 0 && keymode = 0 && keytype = 15]");
				
				/***********************SJY_JNI_Wrap_Key (kekmode = 1 && keymode = 0 && keytype = 15)**********************************/
				//5
				ret = km.SJY_JNI_Wrap_Key(1, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  0, 15, sym_index[0], sym_outside_key_len, sym_outside_key,  sym_warpkey_len, sym_warpkey);
				System.out.println(ret +"SJY_JNI_Wrap_Key [kekmode = 1 && keymode = 0 && keytype = 15]");
				
				//6
				ret = km.SJY_JNI_UnWrap_Key(1, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  	0, 15, sym_warpkey_len[0], sym_warpkey, sym_unwarpedkey_index, sym_unwarpkey_len, sym_unwarpkey);
				System.out.println(ret +"SJY_JNI_UnWrap_Key [kekmode = 1 && keymode = 0 && keytype = 15]");
				
				/***********************SJY_JNI_Wrap_Key (kekmode = 0 && keymode = 1 && keytype = 15)**********************************/
				//5
				ret = km.SJY_JNI_Wrap_Key(0, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  1, 15, sym_index[0], sym_outside_key_len, sym_outside_key,  sym_warpkey_len, sym_warpkey);
				System.out.println(ret +"SJY_JNI_Wrap_Key [kekmode = 0 && keymode = 1 && keytype = 15]");
				
				//6
				ret = km.SJY_JNI_UnWrap_Key(0, 15, sym_kek_index[0], sym_outside_kek_len[0], sym_outside_kek,
										  	1, 15, sym_warpkey_len[0], sym_warpkey, sym_unwarpedkey_index, sym_unwarpkey_len, sym_unwarpkey);
				System.out.println(ret +"SJY_JNI_UnWrap_Key [kekmode = 0 && keymode = 1 && keytype = 15]");
				
				//10
				ret = ja.SJY_SymKey_Del(sym_index[0]);
				System.out.println(ret +"SJY_JNI_SymKey_Del");
				
				ret = ja.SJY_SymKey_Del(sym_unwarpedkey_index[0]);
				System.out.println(ret +"SJY_JNI_SymKey_Del");
				
				/****************************Cert***************************/
				Cert ce = new Cert();
				//1
				ret = ce.SJY_JNI_GenReqP10(asy_index[0], SubjectDn, 75, encyptCertType, reqP10, reqP10_len);
				System.out.println(ret +"SJY_JNI_GenReqP10");
				pCert = loadFile2Bytes("user.cer");
				uiCertLen = pCert.length;
				//2 
				ret = ce.SJY_JNI_ParseCert(pCert, uiCertLen, pCertNo, uiCertNoLen, pOid, uiOidLen, 
										   pBegintime, uiBegintimeLen, PEndtime, uiEndtimeLen, PSubjectDn, 
										   uiSubjectDnLen, PubKey, pubkeyLen);
				System.out.println(ret +"SJY_JNI_ParseCert");
				
				pcCert = loadFile2Bytes("user.cer");
				iCertLen = pcCert.length;
				pcRoot = loadFile2Bytes("root.cer");
				iRootLen = pcRoot.length;
				//3
				ret = ce.SJY_JNI_VerifyCert(pcCert, iCertLen, pcRoot, iRootLen);
				System.out.println(ret +"SJY_JNI_VerifyCert");
				
				pucCertP12 = loadFile2Bytes("test1.p12");
				iCertP12Len = pucCertP12.length;
				ret = ce.SJY_JNI_ParseP12Cert(pucCertP12, iCertP12Len, pKeyPin, iKeyPin_len, 
											  iKeyIndex, ucCertBuffer, uiCertBufferLen);
				System.out.println(ret +"SJY_JNI_ParseP12Cert");
				
				ret = km.SJY_JNI_AsyKey_Del(asy_index[0]);
				
				ret = km.SJY_JNI_AsyKey_Del(asy_unwarpedkey_index[0]);
				
				}
			}
		
			public static byte[] loadFile2Bytes(String Path) {
				Path = "/mnt/e/git/jiangrui/java-example/jni-demo/jni-demo/src/resources/" + Path;

				File file = new File(Path);
				long fileSize = file.length();
				byte []buffer = {0};
				if(fileSize > Integer.MAX_VALUE) {
					System.out.println("File is too big");
					return null;
				}
				try {
					FileInputStream fi = new FileInputStream(file);
					buffer = new byte[(int)fileSize];
					int offset = 0;
					int numRead = 0;
					try {
						while(offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >=0) {
							offset += numRead;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}finally {
						try {
							fi.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				return buffer;
			}
		}
