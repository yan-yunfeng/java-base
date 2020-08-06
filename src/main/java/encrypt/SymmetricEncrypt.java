package encrypt;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * 对称加密算法
 *
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2020/8/6 14:53
 */
public class SymmetricEncrypt {

    /**
     * DES对称加密，
     *
     * @param source 待加密字符串
     * @param desKey 秘钥默认长度为64位，可以是8个字符或8个倍数
     */
    public static String desEncrypt(String source, String desKey) throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        //DES需要有一个可信任的随机数源，这里有可能会因为windows和linux系统差异的问题，导致在windows下可以成功加解密，但是linux下不行的情况
        //可以用这两行代码替换下面的一行
        //KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        //SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        SecureRandom secureRandom = new SecureRandom();
        DESKeySpec desKeySpec = new DESKeySpec(desKey.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);
        byte[] bytes = cipher.doFinal(source.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String desDecrypt(String source, String desKey) throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        SecureRandom secureRandom = new SecureRandom();
        DESKeySpec desKeySpec = new DESKeySpec(desKey.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);
        byte[] bytes = Base64.getDecoder().decode(source.getBytes(StandardCharsets.UTF_8));
        return new String(cipher.doFinal(bytes), StandardCharsets.UTF_8);
    }

    /**
     * 3DES（Triple DES）介于DES和AES之间，它使用3条64位的密钥对数据进行三次加密。是DES的一个更安全的变形。
     * 它以DES为基本模块，通过组合分组方法设计出分组加密算法。比起最初的DES，3DES更为安全。
     * 这里DES和DESede可以相互解密加密，有待考证
     *
     * @param source 待加密字符串
     * @param desKey 密钥长度默认为192位，可以是24个字符或大于24且8是倍数。
     */
    public static String desedeEncrypt(String source, String desKey) throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        DESedeKeySpec desedeKeySpec = new DESedeKeySpec(desKey.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey secretKey = secretKeyFactory.generateSecret(desedeKeySpec);
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] bytes = cipher.doFinal(source.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String desedeDecrypt(String source, String desKey) throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        DESedeKeySpec desedeKeySpec = new DESedeKeySpec(desKey.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey secretKey = secretKeyFactory.generateSecret(desedeKeySpec);
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] bytes = Base64.getDecoder().decode(source.getBytes(StandardCharsets.UTF_8));
        return new String(cipher.doFinal(bytes), StandardCharsets.UTF_8);
    }

    /**
     * AES 加密
     *
     * @param source 待加密字符串
     * @param aesKey 秘钥，必须是8的倍数个字符串
     * @param ivPs   便宜向量，必须是16个字符串
     */
    public static String aesEncrypt(String source, String aesKey, String ivPs) throws NoSuchAlgorithmException, NoSuchPaddingException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(aesKey.getBytes(StandardCharsets.UTF_8));
        keyGenerator.init(128, secureRandom);
        SecretKey secretkey = keyGenerator.generateKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivPs.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, secretkey, iv);
        byte[] bytes = cipher.doFinal(source.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String aesDecrypt(String source, String aesKey, String ivPs) throws NoSuchAlgorithmException, NoSuchPaddingException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(aesKey.getBytes(StandardCharsets.UTF_8));
        keyGenerator.init(128, secureRandom);
        SecretKey secretkey = keyGenerator.generateKey();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivPs.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, secretkey, iv);
        byte[] bytes = Base64.getDecoder().decode(source.getBytes(StandardCharsets.UTF_8));
        return new String(cipher.doFinal(bytes), StandardCharsets.UTF_8);
    }


}
