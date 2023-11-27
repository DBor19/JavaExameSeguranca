package app;

import java.util.List;
import java.util.Scanner;

import dao.LipaseDao;
import dao.PacienteDao;
import dao.SenhaDao;
import dao.ValoresPadroesDao;
import entities.Lipase;
import entities.Paciente;
import entities.Senha;
import entities.ValoresPadroes;
import util.CryptoUtil;
import util.HashingUtil;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bem-vindo ao Sistema de Resultados de Exames de Lipase: ");

        // Cadastro de Usuário
        System.out.println("Cadastre-se para acessar os resultados:");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        // Cadastra o usuario no BD
        Paciente usuario = new Paciente(nome, cpf, login, senha);
        PacienteDao pacienteDao = new PacienteDao();
        pacienteDao.adiciona(usuario);
        System.out.println("Cadastro realizado com sucesso!");

        // Geração e criptografia da chave secreta do usuário
        String chaveSecreta = CryptoUtil.generateKey();
        System.out.println("Chave Secreta: " + chaveSecreta);
        String ChaveSecretaCripto = CryptoUtil.encrypt(chaveSecreta, senha);
        Senha senhaObj = new Senha(1, ChaveSecretaCripto);
        System.out.println("Chave Secreta Criptografada: " + senhaObj.getKey());

        // Cadastro da Chave Secreta Criptografada no BD
        SenhaDao senhaDaoCadastra = new SenhaDao();
        senhaDaoCadastra.cadastrar(senhaObj);

        // Simulação de resultado de exame
        int resultadoExame = 57;
        System.out.println("Resultado do Exame: " + resultadoExame);

        // Criptografia do resultado do exame e cadastro no BD
        String resultadoCriptografado = CryptoUtil.encrypt(Integer.toString(resultadoExame), chaveSecreta);
        System.out.println("Resultado do Exame Criptografado: " + resultadoCriptografado);
        Lipase lipase = new Lipase(1, resultadoCriptografado);
        LipaseDao lipaseDaoInsere = new LipaseDao();
        lipaseDaoInsere.adiciona(lipase);

        // Login do Usuário para visualizar o resultado do exame
        System.out.println("Faça o login para visualizar o resultado do exame: ");
        boolean logado = false;
        while (!logado) {
            System.out.print("Login: ");
            login = sc.nextLine();
            System.out.print("Senha: ");
            senha = sc.nextLine();
            logado = logarPaciente(login, senha);
            if (!logado) {
                System.out.println("Login ou senha incorretos. Tente novamente.");
            }
        }
        
        // Exibição do resultado descriptografado do exame
        System.out.print("Login realizado com sucesso!\n");
        System.out.println("Visualizando o resultado do exame...");

        // Busca e Descriptografia da chave secreta
        SenhaDao senhaDaoBusca = new SenhaDao();
        Senha chaveCriptografada = senhaDaoBusca.getSenha();
        String chaveDescriptografada = CryptoUtil.decrypt(chaveCriptografada.getKey(), senha);
        System.out.println("Chave Secreta Descriptografada: " + chaveDescriptografada);

        // Busca e Descriptografia do resultado do exame
        LipaseDao lipaseDaoBusca = new LipaseDao();
        Lipase resultado = lipaseDaoBusca.buscaPorId(1);
        String resultadoDescriptografado = CryptoUtil.decrypt(resultado.getuL(), chaveDescriptografada);
        System.out.println("O resultado do seu exame é: " + resultadoDescriptografado);
        System.out.println("Valores de referência:");
        List<ValoresPadroes> valoresReferencia = new ValoresPadroesDao().listar();

        for (ValoresPadroes valor : valoresReferencia) {
            System.out.println(valor.getDescricao() + ": " + valor.getLimiteInferior() + " - " + valor.getLimiteSuperior() + " " + valor.getUnidade());
        }
        sc.close();
        
        System.out.println("\nAtenção: para realizar um novo cadastro, é preciso deletar o BD"
        		+ "e criá-lo novamente...");
    }

    // Método para autenticar o login do paciente
    public static boolean logarPaciente(String login, String senha) {
        PacienteDao pacienteDao = new PacienteDao();
        Paciente paciente = pacienteDao.buscaPorLogin(login);
        if (paciente != null) {
            String senhaHash = HashingUtil.hashSha128(senha);
            return senhaHash.equals(paciente.getSenha());
        }
        return false;
    }
}

