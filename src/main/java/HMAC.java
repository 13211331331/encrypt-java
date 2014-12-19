import org.omg.CORBA.PUBLIC_MEMBER;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by keneys on 2014/12/19.
 */
public class HMAC {

    public static void main(String[] args){
        String str = "含蓄得噶个sgfdsd";
        System.out.println("明文是：" + str);

        try {
            //用DES算法得到计算验证码的字符串
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            SecretKey key = keyGenerator.generateKey();
            byte[] keyByte = key.getEncoded();

            //生成mac对象
            SecretKeySpec SKS = new SecretKeySpec(keyByte,"HMACMD5");
            Mac mac = Mac.getInstance("HMACMD5");
            mac.init(SKS);


            //传入要及时验证码的字符串
            mac.update(str.getBytes("UTF8"));

            //计算验证码
            byte[] certifyCode = mac.doFinal();
            System.out.println("密文是:" + new String(certifyCode));

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
