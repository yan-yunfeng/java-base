package encrypt;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 非对称加密，目前主流都是RSA
 *
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2020/8/4 19:57
 */
public class AsymmetricEncrypt {

    public static Map<Integer, String> rsaKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        String privateKeyString = new String(Base64.getEncoder().encode(privateKey.getEncoded()), StandardCharsets.UTF_8);
        //公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = new String(Base64.getEncoder().encode(publicKey.getEncoded()), StandardCharsets.UTF_8);
        // 将公钥和私钥保存到Map
        Map<Integer, String> keyMap = new HashMap<>(2);
        // 0表示公钥
        keyMap.put(0, publicKeyString);
        // 1表示私钥
        keyMap.put(1, privateKeyString);
        return keyMap;
    }

    /**
     * RSA公钥加密
     *
     * @param source    待加密字符串
     * @param publicKey 公钥
     */
    public static String rsaPublicKeyEncrypt(String source, String publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] decoded = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] bytes = cipher.doFinal(source.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
    }

    public static String rsaPrivateKeyDecrypt(String source, String privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] decoded = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(source));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * RSA私钥加密
     *
     * @param source     待加密字符串
     * @param privateKey 私钥
     */
    public static String rsaPrivateKeyEncrypt(String source, String privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] decoded = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, priKey);
        byte[] bytes = cipher.doFinal(source.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
    }

    public static String rsaPublicKeyDecrypt(String source, String publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        byte[] decoded = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(source));
        return new String(bytes, StandardCharsets.UTF_8);
    }

}
