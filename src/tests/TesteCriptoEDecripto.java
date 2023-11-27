package tests;

import util.CryptoUtil;

public class TesteCriptoEDecripto {
    public static void main(String[] args) throws Exception {
    	// Geração de Chave Criptográfica
        String chaveSecreta = "shuhsuhsuhoooepeppepw2394";
        // Senha Original
        String senhaOriginal = "EssaEUmaSenhaSegura123"; 
        // Criptografa a senha usando a chave gerada 
        String senhaCriptografada = CryptoUtil.encrypt(senhaOriginal, chaveSecreta);
        System.out.println("Senha: " + senhaOriginal);
        System.out.println("Key: " + chaveSecreta);
        System.out.println("Cripto: " + senhaCriptografada);
        
        // Descriptografa a senha usando a mesma chave
        String senhaDecriptografada = CryptoUtil.decrypt(senhaCriptografada, chaveSecreta);
        System.out.println("Decripto: " + senhaDecriptografada);
    }
}
