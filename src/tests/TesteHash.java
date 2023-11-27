package tests;

import util.HashingUtil;

public class TesteHash {
    public static void main(String[] args) {
    	// Definição da senha
        String senha = "EssaEUmaSenhaSegura123";
        // Aplicando o hash SHA-128 à senha
        String senhaHash = HashingUtil.hashSha128(senha);
        System.out.println(senha + " = " + senhaHash);
    }

}
