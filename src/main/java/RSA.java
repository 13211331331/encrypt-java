/**
 * Created by keneys on 2014/12/19.
 */

import test.Coder;

import javax.crypto.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


//对称加密   DES
public class RSA  extends Coder{



    //对字符串str加密
    public byte[] encrypt(RSAPublicKey publicKey,byte[] srcBytes){
        if (publicKey != null){
            try {
                //Cipher负责完成加密或解密工作
                Cipher cipher = Cipher.getInstance("RSA");

                //根据公钥，对Cipher对象进行初始化
                cipher.init(Cipher.ENCRYPT_MODE,publicKey);

                //加密
                byte[] resultBytes = cipher.doFinal(srcBytes);
                return resultBytes;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;

    }


    //对字符数组buff解密
    public byte[] decrypt(RSAPrivateKey rsaPrivateKey,byte[] encBytes){
        if (rsaPrivateKey != null){
            try {
                //Cipher负责完成加密或解密工作
                Cipher cipher = Cipher.getInstance("RSA");

                //根据公钥，对Cipher对象进行初始化
                cipher.init(Cipher.DECRYPT_MODE,rsaPrivateKey);

                //解密
                byte[] resultBytes = cipher.doFinal(encBytes);
                return resultBytes;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }



    public static void main(String[] args){
        RSA rsa = new RSA();

        String msg = "韩杰是个sb";

        System.out.println("明文是：" + msg);

        try {

            //KeyPairGenerator用来生产公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            //初始化密钥生成器，密钥大学为1024位
            keyPairGenerator.initialize(1024);

            //生成一个密钥对，保持到keyPair中
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            //得到私密
            RSAPrivateKey privateKey  = (RSAPrivateKey) keyPair.getPrivate();
            //得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();


            Key key = (Key) privateKey;

            String ss = encryptBASE64(key.getEncoded());

            System.out.println(ss);

            key = (Key) publicKey;
            String ss1 = encryptBASE64(key.getEncoded());
            System.out.println(ss);



            FileOutputStream fos1 = new FileOutputStream("f:/mypub.key");
            FileOutputStream fos2 = new FileOutputStream("f:/mypri.key");
            ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos1.writeObject(ss1);
            oos2.writeObject(ss);
            oos1.close();
            oos2.close();
            fos1.close();
            fos2.close();
            System.out.println("公匙生成成功!公匙文件为mypub.key");
            System.out.println("私匙生成成功!私匙文件为mypri.key");




            //用公钥加密
            byte[] srcBytes = msg.getBytes();
            byte[] resultBytes = rsa.encrypt(publicKey,srcBytes);

            System.out.println("密文是：" + new String(resultBytes));

            //用私密解密
            byte[] decBytes = rsa.decrypt(privateKey,resultBytes);
            System.out.println("解密后的结果是：" + new String(decBytes));


        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
