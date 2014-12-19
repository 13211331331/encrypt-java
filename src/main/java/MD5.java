/**
 * Created by keneys on 2014/12/19.
 */

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


//对称加密   DES
public class MD5 {


    //对字符串str加密
    public byte[] encrypt(String str) {
        try {
            //根据MD5算法生成MessageDigest对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            byte[] srcBytes = str.getBytes();

            //更新摘要
            messageDigest.update(srcBytes);

            //完成hash计算
            byte[] resultBytes = messageDigest.digest();
            return resultBytes;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }


    public static void main(String[] args) {

        MD5 md5 = new MD5();

        String msg = "韩杰是个sb";

        System.out.println("明文是：" + msg);

        byte[] resultBytes = md5.encrypt(msg);

        System.out.println("密文是：" + new String(resultBytes));


    }


}
