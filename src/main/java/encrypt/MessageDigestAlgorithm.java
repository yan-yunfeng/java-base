package encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 常见的一些信息摘要的哈希算法，都可以认为是不可逆的加密
 * 最常用的是MD5
 * 但是推荐用SHA256，SHA384， SHA512， HmacSHA256， HmacSHA384， HmacSHA512
 *
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2020/8/5 20:08
 */
public class MessageDigestAlgorithm {

    /**
     * MD5 Message-Digest Algorithm
     * 用来产生一个16个字节的（128位）哈希值
     * 原始数据的细微改变也会导致哈希结果的巨大差异
     * 这里可以直接调用 {@link org.apache.commons.codec.digest.DigestUtils#md5Hex(byte[])}方法
     */
    public static String md5(String source) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        assert digest != null;
        byte[] bytes = digest.digest(source.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(bytes);
    }

    /**
     * SHA Secure Hash Algorithm
     * SHA1 默认产生20个字节（160位）的摘要
     * SHA2 包括SHA-224，SHA-256、SHA-384、SHA-512，后面的数字即可以得到的字符串的位数。
     * 这里可以直接调用 {@link org.apache.commons.codec.digest.DigestUtils}对应的sha方法
     */
    public static String sha(String source, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] bytes = digest.digest(source.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(bytes);
    }

    /**
     * HMAC Hash_based Message Authentication Code
     * 相对于md5和sha引入了秘钥
     * 常用的HMAC加密的算法有 HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512
     */
    public static String hmac(String source, String secretKey, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algorithm);
        Key key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), algorithm);
        mac.init(key);
        byte[] rawHmac = mac.doFinal(source.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.encodeBase64(rawHmac), StandardCharsets.UTF_8);
    }


}
