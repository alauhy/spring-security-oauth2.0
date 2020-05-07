package com.ciot.springsecuritydemo.util;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.io.CipherIOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

import javax.crypto.Cipher;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAEncrypt {
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;

    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;

        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.encodeBase64String(encryptedData);
    }

    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return Base64.encodeBase64String(signature.sign());
    }

    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    public static void main(String[] args) {
        try {
            KeyPair keyPair = getKeyPair();
            String privateKey = Base64.encodeBase64String(keyPair.getPrivate().getEncoded());
            String publicKey = Base64.encodeBase64String(keyPair.getPublic().getEncoded());
            System.out.println("Private Key: " + privateKey);
            System.out.println("Public Key: " + publicKey);
            String data = "aaa-bbb-ccc-ddd";
            String encryptData = encrypt(data, getPublicKey(publicKey));
            System.out.println("encrypted content: " + encryptData);

            String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
            System.out.println("decrypted content: " + decryptData);
            String sign = sign(data, getPrivateKey(privateKey));
            boolean result = verify(data, getPublicKey(publicKey), sign);
            System.out.println("verified result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Resource resource = new ClassPathResource("public.txt");
        String publicKey = null;
        try {
            publicKey = inputStream2String(resource.getInputStream());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(JwtHelper.decodeAndVerify("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3JkZXIiXSwidXNlcl9uYW1lIjoidXNlcl80Iiwic2NvcGUiOlsic2VsZWN0Il0sImV4cCI6MTU4NzM4ODYyOSwiYXV0aG9yaXRpZXMiOlsiVVNFUiJdLCJqdGkiOiJiZThlMTlkZi05ZWM3LTQyZWItODlmYS1jY2IxMmU3Mjk4MmYiLCJjbGllbnRfaWQiOiJjbGllbnRfMSJ9.INUBgmcLNLpvxjNR3VNzOcg3TvToCnuU_rR99q6RWKI_C-Ryl96EmavmHoJvSZ5QPN8J4EzfonyHVNcihT5yxXfC4G1ubl0qZYv0eyDXb2GKY40-x8b_9snJXlJZxQAjM2jl1XvjvJP_LXiNV6U_ZFMGjy-oqcQ6OOPVhdyLExwGgPuyaChOyDIv9egJzAwqEUuhHFLOe-2Aj0qFypasLEwNSFFAFl3jpIUf7MXG0itc_X4pZcie2CmSpix83VUZRQWWQ4a1-fUx9h2Z8v6fr0JSEVi5coYzW3u1VeSOtcLH4u8yN8iObelVob5qZeBtWyGfsWwsmrVP6JrF4FXg3w", new RsaVerifier(publicKey)));
    }
    private static String inputStream2String(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null){
            buffer.append(line);
        }
        return buffer.toString();
    }
}
