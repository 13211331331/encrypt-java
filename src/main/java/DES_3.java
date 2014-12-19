import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * Created by keneys on 2014/12/19.
 */
//对称加密   3DES
public class DES_3 {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        //KeyGenerator提供对此密钥生成器功能，支持各种算法
        KeyGenerator keygen;

        //SecretKey负责保持对称密钥
        SecretKey deskey;

        //Cipher负责完成加密或解密工作
        Cipher c;

        Security.addProvider(new com.sun.crypto.provider.SunJCE());

        //实例化支持DESede算法的密钥生成器（算法名称命名需安装规定，否则抛出异常）
        keygen = KeyGenerator.getInstance("DESede");

        //生产密钥
        deskey = keygen.generateKey();

        //生成Cipher对象，知道其支持DES算法
        c = Cipher.getInstance("DESede");





        String msg = "韩杰是个sb";

        System.out.println("明文是：" + msg);


        c.init(Cipher.ENCRYPT_MODE,deskey);

        byte[] src = msg.getBytes();

        //加密
        byte[] enc = c.doFinal(src);

        System.out.println("密文是：" + new String(enc));


        c.init(Cipher.DECRYPT_MODE,deskey);

        //解密
        byte[] dec = c.doFinal(enc);

        System.out.println("解密后的结果是：" + new String(dec));

    }



}
