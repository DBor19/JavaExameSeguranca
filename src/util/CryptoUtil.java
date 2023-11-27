package util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class CryptoUtil {

    private static final String ALGORITHM = "DES";
    
    // Método para criptografar um valor usando a chave fornecida
    public static String encrypt(String value, String password) throws Exception {
        byte[] keyBytes = adjustKey(password.getBytes(StandardCharsets.UTF_8)); // Ajuste para 8 byts
        // Cria uma chave secreta usando o algoritmo DES
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        // Inicializa o objeto Cipher para criptografia
        Cipher cipher = Cipher.getInstance(ALGORITHM); 
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // Criptografa o valor e converte para Base64
        byte[] encryptedByteValue = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedByteValue);
    }
    
    // Método para descriptografar um valor usando a chave fornecida
    public static String decrypt(String value, String key) throws Exception {
        // Ajusta a chave para 8 bytes
        byte[] keyBytes = adjustKey(key.getBytes(StandardCharsets.UTF_8));

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        byte[] decryptedByteValue = cipher.doFinal(Base64.getDecoder().decode(value));
        return new String(decryptedByteValue, StandardCharsets.UTF_8);
    }
    
    // Método para gerar uma chave secreta usando o algoritmo DES
    public static SecretKey generateSecretKey() throws Exception {
        return KeyGenerator.getInstance(ALGORITHM).generateKey();
    }
    
    // Método para converter um array de bytes em uma representação hexadecimal
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }
    
    // Método para gerar uma chave em formato hexadecimal
    public static String generateKey() throws Exception {
        SecretKey generateKeykey = CryptoUtil.generateSecretKey();
        byte[] keyBytes = generateKeykey.getEncoded();
        return CryptoUtil.bytesToHex(keyBytes);
    }
    // Método privado para ajustar a chave para 8 bytes
    private static byte[] adjustKey(byte[] key) {
        // Ajusta a chave para 8 bytes
        if (key.length < 8) {
            return Arrays.copyOf(key, 8);
        } else if (key.length > 8) {
            return Arrays.copyOfRange(key, 0, 8); // Trunca a chave se for maior que 8 bytes
        } else {
            return key;
        }
    }

}
