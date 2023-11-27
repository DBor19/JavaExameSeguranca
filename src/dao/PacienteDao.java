package dao;

import connections.ConnectionFactory;
import entities.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {

    private final Connection con;

    public PacienteDao() {
        this.con = ConnectionFactory.getConnection();
    }
    
    // Adicionar Paciente ao BD
    public void adiciona(Paciente paciente) {
        String sql = "insert into pacientes (nome, cpf, login, senha_hash) values (?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getLogin());
            stmt.setString(4, paciente.getSenha());
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // Remover Paciente do BD
    public void remove(Paciente paciente) {
        String sql = "delete from pacientes where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, paciente.getId());
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // Alterar informações do Paciente do BD
    public void altera(Paciente paciente, Integer id) {
        String sql = "update pacientes set nome = ?, cpf = ? where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setInt(3, id);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Obter informações do Paciente pelo ID
    public Paciente getPacienteById(Integer id) {
        String sql = "select * from pacientes where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rset = stmt.executeQuery();
            Paciente paciente = new Paciente();
            while (rset.next()) {
                paciente.setNome(rset.getString("nome"));
                paciente.setCpf(rset.getString("cpf"));
            }
            rset.close();
            stmt.close();
            con.close();
            return paciente;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Listar todos os Pacientes do BD
    public List<Paciente> getPacientes() {
        String sql = "select * from pacientes";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            List<Paciente> pacientes = new ArrayList<>();
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                Paciente paciente = new Paciente();
                paciente.setNome(rset.getString("nome"));
                paciente.setCpf(rset.getString("cpf"));
                pacientes.add(paciente);
            }
            rset.close();
            stmt.close();
            con.close();
            return pacientes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    
    // Buscar um paciente pelo ID
    public Paciente buscaPorLogin(String login) {
        String sql = "select * from pacientes where login = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            Paciente paciente = new Paciente();
            while (rs.next()) {
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setLogin(rs.getString("login"));
                paciente.setSenha(rs.getString("senha_hash"));
            }
            stmt.close();
            rs.close();
            con.close();
            return paciente;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
