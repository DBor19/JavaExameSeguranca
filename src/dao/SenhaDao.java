package dao;

import connections.ConnectionFactory;
import entities.Senha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SenhaDao {

    private final Connection con;

    public SenhaDao() {
        this.con = ConnectionFactory.getConnection();
    }

    // Cadastrar uma senha no BD
    public void cadastrar(Senha senha) {
        String sql = "insert into senhas (id, chave_secreta) values (?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, senha.getId());
            stmt.setString(2, senha.getKey());
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Método para obter a senha no BD
    public Senha getSenha() {
        String sql = "select * from senhas";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Senha senha = new Senha();
            while (rs.next()) {
                senha.setId(rs.getInt("id"));
                senha.setKey(rs.getString("chave_secreta"));
            }
            stmt.close();
            con.close();
            return senha;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
