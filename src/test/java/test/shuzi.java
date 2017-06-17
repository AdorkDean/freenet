package test;

import java.math.BigDecimal;
import java.security.KeyFactory;  
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.PrivateKey;  
import java.security.PublicKey;  
import java.security.Signature;  
import java.security.interfaces.ECPrivateKey;  
import java.security.interfaces.ECPublicKey;  
import java.security.spec.PKCS8EncodedKeySpec;  
import java.security.spec.X509EncodedKeySpec;


import com.freenet.tools.FreeUtils;


public class shuzi {
	
	private static String src = FreeUtils.getRandomString(32);  
	
	public static void main(String args[]){
		String aa = "800";
		String bb = "100";
		BigDecimal a1 = new BigDecimal(aa);
		BigDecimal b1 = new BigDecimal(bb);
		System.out.println(a1.subtract(b1));
		
	}
	//30450220177E9737331D2661D1F5656B0391DDF5EA5785BF1617AFF08C4129431085C115022100F630E868886F502E23DF873BFAAA1487222B5E1B7A51720DC67315D780B42A7E
	//304502202D3D79776C29BFB9A0B4A931BC05FA55A169E90FF88B5924A9CC324CBAFA13ED022100DE1A2B021D890566AC1E616FF841E2C740520A22CBA2020915FF5C11ADEEB297
	
	
	
	public static void jdkECDSA(){  
        try {  
            //1.初始化密钥   
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");  
            keyPairGenerator.initialize(256);  
            KeyPair keyPair = keyPairGenerator.generateKeyPair();  
            ECPublicKey ecPublicKey = (ECPublicKey)keyPair.getPublic();  
            ECPrivateKey ecPrivateKey = (ECPrivateKey)keyPair.getPrivate();  
              
              
            //2.执行签名  
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());  
              
            KeyFactory keyFactory = KeyFactory.getInstance("EC");  
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);  
            Signature signature = Signature.getInstance("SHA1withECDSA");  
            signature.initSign(privateKey);  
            signature.update(src.getBytes());  
            byte[] res = signature.sign();  
           // System.out.println("签名："+HexBin.encode(res));  
              
            //3.验证签名  
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());  
            keyFactory = KeyFactory.getInstance("EC");  
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);  
            signature = Signature.getInstance("SHA1withECDSA");  
            signature.initVerify(publicKey);  
            signature.update(src.getBytes());  
            boolean bool = signature.verify(res);  
            System.out.println("验证："+bool);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
	
	

}
