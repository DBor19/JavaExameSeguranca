package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtil {
	
	// Método para aplicar o algoritmo SHA-1 e retornar o hash (hexadecimal)
    public static String hashSha128(String password) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = algorithm.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
