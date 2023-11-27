package dao;

import entities.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MedicoDao {
    private final Connection con;
    
    public MedicoDao(Connection con) {
        this.con = con;
    }
    
    // Adicionar um médico ao banco de dados
    public void adiciona(Medico medico) {
        String sql = "insert into medicos (nome, cpf, especialidade) values (?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCpf());
            stmt.setString(3, medico.getEspecialidade());
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
 	// Remover um médico do banco de dados
    public void remove(Medico medico) {
        String sql = "delete from medicos where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, medico.getId());
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // Alterar informações de um médico no banco de dados
    public void altera(Medico medico, Integer id) {
        String sql = "update medicos set nome = ?, cpf = ?, especialidade = ? where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCpf());
            stmt.setString(3, medico.getEspecialidade());
            stmt.setInt(4, id);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
