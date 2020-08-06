package encrypt;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

/**
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2020/8/5 16:35
 */
public class EncryptTest {

    @Test
    public void md5EncryptTest() throws NoSuchAlgorithmException {
        String source = "我有一头小毛驴我从来也不骑";
        String result = MessageDigestAlgorithm.md5(source);
        System.out.println(result);
        System.out.println(DigestUtils.md5Hex(source));
    }

    @Test
    public void shaEncryptTest() throws NoSuchAlgorithmException {
        String source = "我有一头小毛驴我从来也不骑";
        System.out.println(MessageDigestAlgorithm.sha(source,"SHA-1"));
        System.out.println(DigestUtils.sha1Hex(source));
        System.out.println(MessageDigestAlgorithm.sha(source,"SHA-224"));
//        System.out.println(DigestUtils.sha3_224Hex(source));
        System.out.println(MessageDigestAlgorithm.sha(source,"SHA-256"));
        System.out.println(DigestUtils.sha256Hex(source));
        System.out.println(MessageDigestAlgorithm.sha(source,"SHA-384"));
        System.out.println(DigestUtils.sha384Hex(source));
        System.out.println(MessageDigestAlgorithm.sha(source,"SHA-512"));
        System.out.println(DigestUtils.sha512Hex(source));
    }

    @Test
    public void hmacEncryptTest() throws InvalidKeyException, NoSuchAlgorithmException {
        String source = "我有一头小毛驴我从来也不骑";
        String secretKey = "12345678911";
        String hmacAlgorithm = "HmacSHA256";
        System.out.println(MessageDigestAlgorithm.hmac(source, secretKey, "HmacSHA1"));
        System.out.println(MessageDigestAlgorithm.hmac(source, secretKey, "HmacSHA256"));
        System.out.println(MessageDigestAlgorithm.hmac(source, secretKey, "HmacSHA384"));
        System.out.println(MessageDigestAlgorithm.hmac(source, secretKey, "HmacSHA512"));
    }

    @Test
    public void desEncryptDecryptTest() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        String source = "我有一头小毛驴我从来也不骑";
        String desKey = "87654321";
        String encryptResult = SymmetricEncrypt.desEncrypt(source,desKey);
        System.out.println(encryptResult);
        System.out.println(SymmetricEncrypt.desDecrypt(encryptResult, desKey));
    }

    @Test
    public void desedeEncryptDecryptTest() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        String source = "我有一头小毛驴我从来也不骑";
        String desKey = "12345678123456781234567812345678";
        String encryptResult = SymmetricEncrypt.desedeEncrypt(source,desKey);
        System.out.println(encryptResult);
        System.out.println(SymmetricEncrypt.desedeDecrypt(encryptResult, desKey));
    }

    @Test
    public void aesEncryptDecryptTest() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        String source = "我有一头小毛驴我从来也不骑";
        String desKey = "1234567812345678asdfghjk";
        String ivPs = "12345678asdfghjk";
        String encryptResult = SymmetricEncrypt.aesEncrypt(source,desKey, ivPs);
        System.out.println(encryptResult);
        System.out.println(SymmetricEncrypt.aesDecrypt(encryptResult, desKey, ivPs));
    }

    @Test
    public void rsaTest() throws Exception {
        Map<Integer,String> keyMap = AsymmetricEncrypt.rsaKeyPair();
        String publicKey = keyMap.get(0);
        String privateKey = keyMap.get(1);
        System.out.println("公钥："+publicKey);
        System.out.println("私钥："+privateKey);
        String source = "我有一头小毛驴我从来也不骑";
        String pubKeyEncryptResult = AsymmetricEncrypt.rsaPublicKeyEncrypt(source, publicKey);
        String priKeyEncryptResult = AsymmetricEncrypt.rsaPrivateKeyEncrypt(source, privateKey);
        System.out.println("公钥加密结果："+pubKeyEncryptResult);
        System.out.println("私钥解密结果："+AsymmetricEncrypt.rsaPrivateKeyDecrypt(pubKeyEncryptResult, privateKey));
        System.out.println("私钥加密结果："+priKeyEncryptResult);
        System.out.println("公钥解密结果："+AsymmetricEncrypt.rsaPublicKeyDecrypt(priKeyEncryptResult, publicKey));

    }

}
