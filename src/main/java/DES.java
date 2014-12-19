/**
 * Created by keneys on 2014/12/19.
 */

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;


//对称加密   DES
public class DES {

    //KeyGenerator提供对此密钥生成器功能，支持各种算法
    private KeyGenerator keygen;

    //SecretKey负责保持对称密钥
    private SecretKey deskey;

    //Cipher负责完成加密或解密工作
    private Cipher c;

    //该字节数组赋值保持加密的结果
    private byte[] cipherByte;

    public DES(){
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        try {
            //实例化支持DES算法的密钥生成器（算法名称命名需安装规定，否则抛出异常）
            keygen = KeyGenerator.getInstance("DES");

            //生产密钥
            deskey = keygen.generateKey();

            //生成Cipher对象，知道其支持DES算法
            c = Cipher.getInstance("DES");

        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }catch (NoSuchPaddingException ex){
            ex.printStackTrace();
        }
    }


    //对字符串str加密
    public byte[] createEncryptor(String str){
        try {
            //根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
            c.init(Cipher.ENCRYPT_MODE,deskey);
            byte[] stc = str.getBytes();

            //加密，结果保持进cipherByte
            cipherByte = c.doFinal(stc);
        }catch (InvalidKeyException ex){
            ex.printStackTrace();
        }catch (BadPaddingException e){
            e.printStackTrace();
        }catch (IllegalBlockSizeException e){
            e.printStackTrace();
        }
        return cipherByte;
    }


    //对字符数组buff解密
    public byte[] createDescryptor(byte[] buff){
        try {
            //根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示解密模式
            c.init(Cipher.DECRYPT_MODE,deskey);
            //得到明文，存入cipherByte字符数组
            cipherByte = c.doFinal(buff);
        }catch (InvalidKeyException e){
            e.printStackTrace();
        }catch (BadPaddingException e){
            e.printStackTrace();
        }catch (IllegalBlockSizeException e){
            e.printStackTrace();
        }
        return cipherByte;
    }



    public static void main(String[] args){
        DES p = new DES();

        String msg = "韩杰是个sb";

        System.out.println("明文是：" + msg);

        byte[] enc = p.createEncryptor(msg);

        System.out.println("密文是：" + new String(enc));

        byte[] dec = p.createDescryptor(enc);

        System.out.println("解密后的结果是：" + new String(dec));

    }



}
