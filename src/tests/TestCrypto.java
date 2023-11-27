package tests;

import util.CryptoUtil;

public class TestCrypto {
    public static void main(String[] args) throws Exception {
//        String key = CryptoUtil.generateKey();
        String key = "daushduahfuhddgufhaoghaoudhfouahgouhafouhgaouhdofuhaohsdauhsudhausd";
        String senha = "laikaboss";
        String senhaCrypto = CryptoUtil.encrypt(senha, key);
        System.out.println("Senha: " + senha);
        System.out.println("Key: " + key);
        System.out.println("Cripto: " + senhaCrypto);

        String senhaDecripto = CryptoUtil.decrypt(senhaCrypto, key);
        System.out.println("Decripto: " + senhaDecripto);
    }
}
