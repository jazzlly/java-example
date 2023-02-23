#ifndef _SJY_API_H_
#define _SJY_API_H_


#ifdef __cplusplus
extern "C"
{
#endif


#ifdef SMAPIDLL_INT
#else
#ifdef WIN32
#define SMAPIDLL_INT _declspec(dllexport) int
#else
#define SMAPIDLL_INT int
#endif
#endif


//****************************************************************************************
//***
//***宏及结构声明
//***
//****************************************************************************************
#define SGD_SM1_ECB			0x00000101  //SM1算法ECB加密模式
#define SGD_SM1_CBC			0x00000102  //SM1算法CBC加密模式
#define SGD_SM4_ECB			0x00000401  //SM4算法ECB加密模式
#define SGD_SM4_CBC			0x00000402  //SM4算法CBC加密模式
#define SJY_ECCref_MAX_BITS			256         //SM2密钥大小(单位：bit)
#define SJY_ECCref_MAX_LEN			((SJY_ECCref_MAX_BITS+7) / 8)       //SM2密钥大小(单位：byte)
typedef struct SjyECCrefPublicKey_st           //SM2公钥数据结构
{
	unsigned int  bits;                     //SM2密钥大小(单位：bit) 256                 
	unsigned char x[SJY_ECCref_MAX_LEN];        //X坐标
	unsigned char y[SJY_ECCref_MAX_LEN];        //Y坐标 
} SjyECCrefPublicKey;

typedef struct SjyECCrefPrivateKey_st          //SM2私钥数据结构
{
	unsigned int  bits;                     //SM2密钥大小(单位：bit) 
	unsigned char D[SJY_ECCref_MAX_LEN];        //SM2私钥数据
} SjyECCrefPrivateKey;


typedef struct SjyECCCipher_st                 //SM2密文数据结构
{
	unsigned char x[SJY_ECCref_MAX_LEN];        //C1:X坐标
	unsigned char y[SJY_ECCref_MAX_LEN];        //C1:Y坐标 
	unsigned char C[SJY_ECCref_MAX_LEN];        //C3
	unsigned char M[SJY_ECCref_MAX_LEN];        //C2
} SjyECCCipher;


typedef struct SjyECCSignature_st              //SM2签名值数据结构
{
	unsigned char r[SJY_ECCref_MAX_LEN];        //R
	unsigned char s[SJY_ECCref_MAX_LEN];        //S
} SjyECCSignature;
//****************************************************************************************
//错误码
#define SJY_OK                              0x0					//success
#define SJY_BASE				    0x00000000             
#define SJY_MKERR                   (SJY_BASE +0x21)			// 无主密钥 
#define SJY_BADPARAM                (SJY_BASE +0x52)			// 输入参数出错
#define SJY_SYMERR		            (SJY_BASE +0x9b)			// 对称加解密出错
#define SJY_GENECC		            (SJY_BASE +0xb1)			// 生成密钥对出错
#define SJY_ECCENC		            (SJY_BASE +0xb2)			// ecc加密出错
#define SJY_ECCDEC					(SJY_BASE +0xb3)			// ecc解密出错
#define SJY_SIGNERRECC              (SJY_BASE +0xb4)			// ecc签名出错
#define SJY_VERERRECC				(SJY_BASE +0xb5)			// ecc验签出错
#define SJY_GENKEY                  (SJY_BASE +0xb6)			// 生成对称密钥失败
#define SJY_KEYEXIST				(SJY_BASE +0xb7)			// 密钥已存在
#define SJY_CERT_VERFIYERR          (SJY_BASE +0xb8)			// 验证证书失败
#define SJY_CERTP12_PARSE           (SJY_BASE +0xb9)			// P12证书解析失败
#define SJY_CERT_PARSE              (SJY_BASE +0xba)			// P10证书解析失败
#define SJY_ALG_ERROR               (SJY_BASE +0xbb)			// acm运算错误
#define SJY_BACKUP_ERROR            (SJY_BASE +0xbc)			// 备份失败
#define SJY_FILESIZE_ERROR          (SJY_BASE +0xbd)			// 文件大小错误
#define SJY_READFILE_ERROR          (SJY_BASE +0xbe)			// 读取文件失败

#define SJY_ERR_INITERR					(SJY_BASE + 0xbf)		// 服务端初始化失败
#define SJY_ERR_SESSIONKEYHANDLE		(SJY_BASE + 0xc1)		// 会话句柄错误 
#define SJY_ERR_KEYERR					(SJY_BASE + 0xc2)		// 密钥操作失败
#define SJY_ERR_NULLPIONT				(SJY_BASE + 0xc3)		// 空指针错误
#define SJY_ERR_NOTSUPPORT				(SJY_BASE + 0xc4)		// 不支持
#define SJY_ERR_NOBUFFER				       (SJY_BASE + 0xc5)		//内存空间不足
#define SJY_ERR_NEWCONNECT		       (SJY_BASE + 0xc6)		//新建连接失败
#define SJY_ERR_RELCONNECT				       (SJY_BASE + 0xc7)		//释放连接失败
#define SJY_ERR_COMMFAIL				       (SJY_BASE + 0xc8)		// 通信出错
#define SJY_ERR_OPENFILE				       (SJY_BASE + 0xc9)		//打开文件失败
#define SJY_ERR_KEYPAIRMATCH			       (SJY_BASE + 0xca)		//密钥对不匹配
#define SJY_ERR_WRITEFILE			       (SJY_BASE + 0xcb)		//写文件失败
#define SJY_BDSTATE_ERROR			(SJY_BASE + 0xd1)//设备状态异常
#define  SJY_RESTORE_ERROR            (SJY_BASE +0xcc)			// 恢复密钥失败

#define  SJY_ALG_TIMEOUT            0xA1000003		// 算法超时
//****************************************************************************************
//建立连接接口
SMAPIDLL_INT  SDT_Initialize_sjy(const char * ipaddr, const int port);

SMAPIDLL_INT  SDT_SetLogLevel_sjy(unsigned int loglevel);
SMAPIDLL_INT  SDT_SetIsSSL_sjy(unsigned int isssl);
SMAPIDLL_INT  SDT_InitialCfg_sjy(const char * cfgfile);
//****************************************************************************************
//***
//***密钥管理类接口
//***
//****************************************************************************************
/*************************SM2密钥管理类***********************/
//1、获取一个记录为空的SM2密钥索引号
SMAPIDLL_INT  SJY_Get_KeyIndex(
	    char *pcKeyType,                //输入：密钥类型，仅支持"SM2",必须以‘/0’结尾
	    unsigned int *puiKeyIndex       //输出：获得记录为空的非对称密钥索引号
    );
	    
//2、指定索引位置生成SM2密钥对
SMAPIDLL_INT SJY_SCE_Index_GenKeyPair(
        unsigned int uiKeyIndex			//输入：SM2密钥对索引号，取值范围0-49999
    );
	
//3、删除指定索引号的SM2密钥
SMAPIDLL_INT SJY_AsyKey_Del(
        unsigned int uiKeyIndex         //输入：密钥索引号，取值范围0-49999
    );

//4、导出指定索引号的SM2公钥    
SMAPIDLL_INT SJY_Get_ECC_Pubkey(  
        unsigned int uiKeyIndex,        //输入：SM2密钥索引号，取值范围0-49999
        SjyECCrefPublicKey *pPubkey        //输出：SM2公钥结构体首地址
    );
    
//5、明文导入SM2密钥对
SMAPIDLL_INT SJY_SCE_External_ImportKeyPair(
        unsigned int uiKeyIndex,        //输入：SM2密钥索引号，取值范围0-49999
        SjyECCrefPublicKey* pPubKey,       //输入：SM2公钥结构体首地址
        SjyECCrefPrivateKey* pPrivKey      //输入：SM2私钥结构体首地址
    );
    
//6、计算外部SM2私钥对应的公钥
SMAPIDLL_INT SJY_SCE_External_GenPKeyBySkey(
        SjyECCrefPrivateKey* pPrivKey,     //输入：SM2私钥结构体首地址
        SjyECCrefPublicKey* pPubKey        //输出：SM2公钥结构体首地址
    );
    
//7、产生外部ECC密钥对
SMAPIDLL_INT SJY_SCE_External_GenKeyPair(
        SjyECCrefPublicKey *pPubKey,	    //输出：ECC公钥结构
        SjyECCrefPrivateKey *pPriKey	    //输出：ECC私钥结构
    );
    

/*************************SM1/SM4密钥管理类***********************/
//8、获取一个未使用的对称密钥的索引号
SMAPIDLL_INT SJY_SymKey_GetIndex(
        unsigned int* puiKeyIndex       //输出：获得记录为空的对称密钥索引号
    );

//9、生成指定索引号的对称密钥
SMAPIDLL_INT SJY_Gen_Key_Index(
        unsigned int uiKeyIndex         //输入：密钥索引号，取值范围0-99999
	);
	
//10、删除指定索引号的对称密钥
SMAPIDLL_INT SJY_SymKey_Del(
        unsigned int uiKeyIndex         //输入：对称密钥索引号，取值范围0-99999
    );

//11、获取对称密钥个数
SMAPIDLL_INT SJY_Get_Key_Count(
	    unsigned int* puiCount          //输出：获得所有预置对称密钥的个数
    );
    
//12、生成指定索引号的对称密钥				
SMAPIDLL_INT SJY_Gen_Key( 
        unsigned int uiKeyIndex,        //输入：密钥索引号，取值范围，0-99999
        unsigned char ucKeyLen,         //输入：密钥长度，取值范围，16
        unsigned char ucKeyType         //输入：密钥类型，10(SM1/SM4)
    );

//13、产生外部对称密钥
SMAPIDLL_INT SJY_Gen_Key_External( 
        unsigned int uiKeyLen,          //输入：密钥长度，取值范围1-1024
        unsigned char *pucKey           //输出：产生的对称密钥
    );


/*************************密钥批量管理类***********************/	
//14、清除所有指定类型的密钥
SMAPIDLL_INT SJY_Del_Index( 
        unsigned int uiKeyType          //输入：密钥类型：12 删除对称密钥; 15 删除ECC密钥对
    );



//15、密钥封装
SMAPIDLL_INT SJY_Wrap_Key(
        unsigned int uiMechanism,       //输入：填充方式，参数未启用
        unsigned char ucKekMode,        //输入：保护密钥类型，取值范围，1:内部密钥 0:外部密钥        
        unsigned char ucKekType,        //输入：保护密钥算法类型，取值范围，15:"SM4"
        unsigned int uiKekIndex,        //输入：保护密钥索引号，ucKekMode为1时有效，取值范围，0-99999
        unsigned int uiKekLen,          //输入：保护密钥长度，ucKekMode为0时有效 取值范围 16
        unsigned char *pucKek,          //输入：保护密钥，ucKekMode为0时有效
        unsigned char ucKeyMode,        //输入：待封装密钥类型，取值范围，1:内部密钥 0:外部密钥
        unsigned char ucKeyType,        //输入：待封装密钥算法类型，取值范围，8:"SM1",11:"ECCPRI",15:"SM4"
        unsigned int uiKeyIndex,        //输入：待封装密钥索引号，ucKeyMode为1时有效，取值范围，对称：0-99999，非对称：0-49999
        unsigned int uiKeyLen,          //输入：待封装密钥长度，ucKeyMode为0时有效 取值范围 16或32
        unsigned char *pucKey,          //输入：待封装密钥，ucKeyMode为0时有效
        unsigned int uiIvLen,           //输入：IV的长度，参数未启用
        unsigned char *pucIv,           //输入：IV首地址，参数未启用
        unsigned int *puiWrapkeyLen,    //输入+输出：封装密钥长度 作为输入时判断ucKeyType=11时>=48，其他时>=32
        unsigned char *pucWrapkey       //输出：封装密钥首地址
    );


//16、密钥解封装
SMAPIDLL_INT SJY_UnWrap_Key(
        unsigned int uiMechanism,       //输入：填充方式，参数未启用
        unsigned char ucKekMode,        //输入：保护密钥类型，取值范围，1:内部密钥 0:外部密钥        
        unsigned char ucKekType,        //输入：保护密钥算法类型，取值范围，15:"SM4"
        unsigned int uiKekIndex,        //输入：保护密钥索引号，ucKekMode为1时有效，取值范围，0-99999
        unsigned int uiKekLen,          //输入：保护密钥长度，ucKekMode为0时有效 取值范围 16
        unsigned char *pucKek,          //输入：保护密钥，ucKekMode为0时有效
        unsigned char ucKeyMode,        //输入：待解封装密钥类型，取值范围，1:内部密钥 0:外部密钥
        unsigned char ucKeyType,        //输入：待解封装密钥算法类型，取值范围，8:"SM1",11:"ECCPRI",15:"SM4"
        unsigned int uiIvLen,           //输入：IV的长度，参数未启用
        unsigned char *pucIv,           //输入：IV首地址，参数未启用
        unsigned int uiWrapkeyLen,      //输入：封装密钥长度	取值范围,ucKeyType=11时 =48, 其他 =32
        unsigned char *pucWrapkey,      //输入：封装密钥首地址
        unsigned int *puiKeyIndex,      //输出：解封装后密钥存储索引，ucKeyMode为1时有效
        unsigned int *unwrapkeylen,     //输出：解封装后密钥长度，ucKeyMode为0时有效
        unsigned char *unwrapkey        //输出：解封装后密钥首地址，ucKeyMode为0时有效
    );


//17，备份密码机内所有的SM2密钥到本地文件
SMAPIDLL_INT Backup_ECCFile(
        char *pcFileName                //输入：本地文件名，最大支持1024字节，必须以'\0'结尾 不支持"/\:*?<|"特殊符号
     );
//18，恢复本地SM2密钥备份文件到密码机
SMAPIDLL_INT Restore_ECCFile(
        char *pcFileName                //输入：本地文件名，最大支持1024字节，必须以'\0'结尾 不支持"/\:*?<|"特殊符号
    );
//19，备份密码机内所有的SM1和SM4密钥到本地文件
SMAPIDLL_INT Backup_SymFile(
        char *pcFileName                //输入：本地文件名，最大支持1024字节，必须以'\0'结尾 不支持"/\:*?<|"特殊符号
    );
//20，恢复本地SM1和SM4密钥备份文件到密码机
SMAPIDLL_INT Restore_SymFile(
        char *pcFileName                //输入：本地文件名，最大支持1024字节，必须以'\0'结尾 不支持"/\:*?<|"特殊符号
    );
    
    
			
//****************************************************************************************
//***
//***密码运算类接口
//***
//****************************************************************************************
//21、产生指定长度随机数
SMAPIDLL_INT SJY_Get_Random( 
        unsigned int uiLen,             //输入：指定产生随机数的长度
        unsigned char *pucRandom        //输出：随机数
    );
			    
//22、内部SM2公钥加密
SMAPIDLL_INT SJY_SCE_Index_Encrypt(
        unsigned int uiKeyIndex,	    //输入：公钥索引号，取值范围，0-49999
        unsigned char *pucdata,	        //输入：数据
        unsigned int uiDataLen,		    //输入：数据字节长度，取值范围，1-1024
        unsigned char *pucEncData,	    //输出：加密后的数据，格式为C1||C3||C2
        unsigned int *puiEncDataLen	    //输入+输出：数据长度 >=uiDataLen+96
    ); 

//23、内部SM2私钥解密
SMAPIDLL_INT SJY_SCE_Index_Decrypt(
        unsigned int uiKeyIndex,	    //输入：私钥索引号 取值范围，0-49999
        unsigned char *pucEncData,	    //输入：数据
        unsigned int uiEncDataLen,	    //输入：数据字节长度，取值范围，97-1120
        unsigned char *pucData,		    //输出：解密后的数据
        unsigned int *puiDataLen	    //输入+输出：数据长度，作为输入时>=uiEncDataLen-96
    );

//24、内部SM2私钥签名
SMAPIDLL_INT SJY_SCE_Index_Sign(
        unsigned int uiKeyIndex,	    //输入：私钥索引号，取值范围0-49999
        unsigned char pucHash[32],	    //输入：签名数据
        unsigned char pucSign[64]	    //输出：签名值
    );

//25、内部SM2公钥验证签名
SMAPIDLL_INT SJY_SCE_Index_Verify(
        unsigned int uiKeyIndex,	    //输入：公钥索引号，取值范围0-49999
        unsigned char pucHash[32],	    //输入：签名数据
        unsigned char pucSign[64]	    //输入：签名值
    );
			
//26、内部密钥对称加密
SMAPIDLL_INT SJY_Index_Encrypt(
        unsigned int uiKeyIndex,        //输入：密钥索引号，取值范围0-99999
        unsigned int uiAlgId,			//输入：算法ID，取值范围，SGD_SM1_ECB、SGD_SM1_CBC、SGD_SM4_ECB、SGD_SM4_CBC
        unsigned char *pucIv,		    //输入：IV， uiAlgId=SGD_SM1_CBC或SGD_SM4_CBC时，IV不为NULL
        unsigned char *pucData,	        //输入：明文数据
        unsigned int uiDataLen,			//输入：明文数据字节长度 >0且是16整数倍
        unsigned char *pucEncData,		//输出：密文数据
        unsigned int *puiEncDataLen		//输入+输出：密文数据长度 作为输入>=uiDataLen
    );

//27、内部密钥对称解密
SMAPIDLL_INT SJY_Index_Decrypt(	
        unsigned int uiKeyIndex,        //输入：密钥索引号，取值范围0-99999
        unsigned int uiAlgId,			//输入：算法ID，取值范围，SGD_SM1_ECB、SGD_SM1_CBC、SGD_SM4_ECB、SGD_SM4_CBC
        unsigned char *pucIv,		    //输入：uiAlgId=SGD_SM1_CBC或SGD_SM4_CBC时，IV不为NULL
        unsigned char *pucEncData,		//输出：密文数据
        unsigned int uiEncDataLen,		//输入：密文数据长度 >0且是16整数倍
        unsigned char *pucData,	        //输入：明文数据
        unsigned int *puiDataLen		//输入+输出：明文数据字节长度 作为输入>=uiEncDataLen
    );


//28、外部密钥对称加密
SMAPIDLL_INT SJY_External_Encrypt(
        unsigned char *pucKey,		    //输入：密钥 	
        unsigned int uiKeyLen,		 	//输入：密钥长度，取值范围，16
        unsigned int uiAlgId,			//输入：算法ID，取值范围，SGD_SM1_ECB、SGD_SM1_CBC、SGD_SM4_ECB、SGD_SM4_CBC
        unsigned char *pucIv,		    //输入：IV uiAlgId=SGD_SM1_CBC或SGD_SM4_CBC时，IV不为NULL
        unsigned char *pucData,	        //输入：明文数据
        unsigned int uiDataLen,			//输入：明文数据字节长度 >0且是16整数倍
        unsigned char *pucEncData,		//输出：密文数据
        unsigned int *puiEncDataLen		//输入+输出：密文数据长度 作为输入>=uiDataLen
    );


//29、外部密钥对称解密
SMAPIDLL_INT SJY_External_Decrypt(	
        unsigned char *pucKey,		    //输入：密钥 	
        unsigned int uiKeyLen,		 	//输入：密钥长度，取值范围，16
        unsigned int uiAlgId,			//输入：算法ID，取值范围，SGD_SM1_ECB、SGD_SM1_CBC、SGD_SM4_ECB、SGD_SM4_CBC
        unsigned char *pucIv,		    //输入：IV uiAlgId=SGD_SM1_CBC或SGD_SM4_CBC时，IV不为NULL
        unsigned char *pucEncData,		//输出：密文数据
        unsigned int uiEncDataLen,		//输入：密文数据长度	>0且是16整数倍
        unsigned char *pucData,	        //输入：明文数据
        unsigned int *puiDataLen		//输入+输出：明文数据字节长度 作为输入>=uiEncDataLen
    );




//30、外部ECC公钥加密运算
SMAPIDLL_INT SJY_SCE_External_Encrypt(
        SjyECCrefPublicKey *pPubKey,	    //输入：ECC公钥结构首地址
        unsigned char *pucdata,	        //输入：数据
        unsigned int uiDataLen,		    //输入：数据字节长度，取值范围，1-1024
        unsigned char *pucEncData,	    //输出：加密后的数据，格式为C1||C3||C2
        unsigned int *puiEncDataLen	    //输入+输出：数据长度 作为输入时 >=uiDataLen+96
    );


//31、外部ECC私钥解密运算
SMAPIDLL_INT SJY_SCE_External_Decrypt(
        SjyECCrefPrivateKey *pPriKey,	    //输入：ECC私钥结构首地址
        unsigned char *pucEncData,	    //输入：数据
        unsigned int uiEncDataLen,	    //输入：数据字节长度，取值范围，97-1120
        unsigned char *pucData,		    //输出：解密后的数据
        unsigned int *puiDataLen	    //输入+输出：数据长度	作为输入时>=uiEncDataLen-96
    );




//32、外部ECC私钥签名运算
SMAPIDLL_INT SJY_SCE_External_Sign(
        SjyECCrefPrivateKey *pPriKey,	    //输入：ECC私钥结构
        unsigned char pucHash[32],	    //输入：签名数据
        unsigned char pucSign[64]	    //输出：签名值
    );


//33、外部ECC公钥验签运算
SMAPIDLL_INT SJY_SCE_External_Verify(
        SjyECCrefPublicKey *pPubKey,	    //输入：ECC公钥结构
        unsigned char pucHash[32],	    //输入：签名数据
        unsigned char pucSign[64]	    //输入：签名值
    );

//34、SM3杂凑
SMAPIDLL_INT SJY_SCE_SM3( 
        unsigned char* pucMsg,		    //输入：消息
        unsigned int uiMsgLen,          //输入：消息长度
        unsigned char pucHash[32]	    //输出：hash
    );


//35、分步杂凑算法
SMAPIDLL_INT Hash_Init(
        unsigned char *ctx,             //输入+输出：杂凑上下文，外部分配256字节大小的内存空间
        SjyECCrefPublicKey *pucPublicKey	//输入：ECC公钥结构，为NULL时计算杂凑，!NULL时计算SM2预处理
    );
//36、
SMAPIDLL_INT Hash_Update(
        unsigned char *ctx,             //输入+输出：杂凑上下文，需调用Hash_Init进行初始化
        unsigned char *pucData,         //输入：待杂凑数据
        unsigned int  uiDataLength      //输入：待杂凑数据长度 1-8192
    ); 
    
//37、
SMAPIDLL_INT Hash_Final(
        unsigned char *ctx,             //输入+输出：杂凑上下文，需调用Hash_Init进行初始化
        unsigned char *pucHash,         //输出：杂凑数据
        unsigned int  *puiHashLength    //输入+输出：杂凑数据长度 输入>=32
    );


//38、带密钥的sm3 mac算法
SMAPIDLL_INT SJY_SM3_MAC(
        unsigned char* pData,           //输入：数据
        unsigned int uiDatalen,         //输入：数据长度 >0
        unsigned int uiKeyIndex,	    //输入：密钥索引号 0-99999
        unsigned int* puiMacLen,	    //输入+输出：MAC值长度，取值范围，16、32
        unsigned char* pucMac           //输出：MAC值
    );

//****************************************************************************************
//***
//***证书管理类接口
//***
//****************************************************************************************
//39、生成P10证书请求
SMAPIDLL_INT SJY_GenReqP10(
        unsigned int uiKeyIndex,        //输入：密钥索引号，取值范围0-49999
        char* pcSubjectDn,              //输入：证书主题
        unsigned int uiSubjectDnLen,    //输入：主题长度，取值范围，1-256
        unsigned int uiCertUseType,     //输入：证书类型，0代表加密，1代表签名
        unsigned char *pucReqP10,       //输出：生成的p10请求
        unsigned int *puiReqP10Len      //输入+输出：生成的p10请求长度
    );

//40、解析证书
SMAPIDLL_INT SJY_ParseCert(
        unsigned char * pucCert,        //输入，用户证书DER格式
        unsigned int uiCertLen,         //输入，用户证书长度，取值范围，1-2048 
        unsigned char * pCertNo,        //输出，证书序列号     
        unsigned int *uiCertNoLen,	    //输入，证书序列号长度 
        unsigned char * pOid, 	        //输出，算法标识       
        unsigned int *uiOidLen,		    //输入，算法长度       
        unsigned char *pBegintime,      //输出，有效起始日  
        unsigned int *uiBegintimeLen,   //输入，日期长度      
        unsigned char *PEndtime, 	    //输出，有效结束日     
        unsigned int *uiEndtimeLen,		//输入，日期长度         
        unsigned char *PSubjectDn, 	    //输出，证书主题    
        unsigned int *uiSubjectDnLen,	//输入，证书主题长度  
        unsigned char *PubKey, 	        //输出，公钥           
        unsigned int *pubkeyLen			//输入，公钥长度         
    );                                 

//41、验证证书
SMAPIDLL_INT SJY_VerifyCert(
        unsigned char * pcCert,         //输入，DER格式用户证书
        unsigned int uiCertLen,         //输入，用户证书长度，取值范围，1-2048 
        unsigned char * pcRoot,         //输入，DER格式根证书	
        unsigned int uiRootLen           //输入，根证书长度，取值范围，1-2048 
    ); 

//42、解析P12证书
SMAPIDLL_INT SJY_ParseP12Cert(
        unsigned char *pucCertP12,      //输入，p12证书         
        unsigned int uiCertP12Len,      //输入，p12证书长度，取值范围，1-4096
        unsigned char *pucKeyPin,       //输入，私钥PIN码    
        unsigned int uiKeyPinLen,       //输入，私钥PIN码长度，取值范围，1-32 
        unsigned int *puiKeyIndex,      //输出，密钥索引    
        unsigned char *pucCertBuffer,   //输出，DER格式证书 
        unsigned int *puiCertBufferLen  //输入+输出，证书长度     
    ); 

#ifdef __cplusplus
}
#endif		
		
#endif
