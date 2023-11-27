package dao;

import connections.ConnectionFactory;
import entities.Lipase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LipaseDao {
    private Connection con;

    public LipaseDao() {
        this.con = ConnectionFactory.getConnection();
    }

    public void adiciona(Lipase lipase) {
        String sql = "insert into Lipase (id, uL) values (?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, lipase.getId());
            stmt.setString(2, lipase.getuL());
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Integer id) {
        String sql = "delete from Lipase where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(Lipase lipase, Integer id) {
        String sql = "update Lipase set uL = ? where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, lipase.getuL());
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Lipase> lista() {
        String sql = "select * from Lipase";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Lipase> lipases = new ArrayList<>();
            while (rs.next()) {
                Lipase lipase = new Lipase();
                lipase.setId(rs.getInt("id"));
                lipase.setuL(rs.getString("uL"));
                lipases.add(lipase);
            }
            rs.close();
            stmt.close();
            con.close();
            return lipases;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Lipase buscaPorId(Integer id) {
        String sql = "select * from Lipase where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Lipase lipase = new Lipase();
            while (rs.next()) {
                lipase.setId(rs.getInt("id"));
                lipase.setuL(rs.getString("uL"));
            }
            stmt.close();
            rs.close();
            con.close();
            return lipase;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
