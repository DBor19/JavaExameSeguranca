package tests;
import java.util.Scanner;

import static app.Main.logarPaciente;

public class TesteLogin {
    public static void main(String[] args) {
        
    	
    	boolean logado = false;
        
        // Recebendo a entrada do usuário
        Scanner sc = new Scanner(System.in);
        while (!logado) {
            System.out.print("Login: ");
            String login = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            logado = logarPaciente(login, senha);
            if (!logado) {
                System.out.println("Login ou senha incorretos. Tente novamente.");
            }
        }

        sc.close();
    }
}
